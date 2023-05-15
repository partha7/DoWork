package com.example.todo.ui.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.FragmentListBinding
import com.example.todo.viewmodels.ChoresViewModel


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val choresViewModel: ChoresViewModel by viewModels()

    private var choresListAdapter: ChoresListAdapter? = null

//    val activity: MainActivity = requireActivity() as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addItemFragment)
        }

        binding.listChores.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateItemFragment)
        }

        //set menu
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.list_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId) {
                    R.id.menu_search -> {
                        Toast.makeText(activity, "Search clicked", Toast.LENGTH_SHORT).show()
                        return true
                    }
                    R.id.menu_sort -> {
                        Toast.makeText(activity, "Sort clicked", Toast.LENGTH_SHORT).show()
                        return true
                    }
                    R.id.menu_delete_all -> {
                        Toast.makeText(activity, "Delete clicked", Toast.LENGTH_SHORT).show()
                        return true
                    }
                    else -> {
                        println("Kabhi aana nahi chahiye")
                        return false
                    }
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val rv = binding.todoListRv
        rv.layoutManager = LinearLayoutManager(activity)
        println("test111: getting data")
        choresListAdapter = activity?.applicationContext?.let { ChoresListAdapter(it) }
//        println("test111: init data ${choresViewModel.getAllChoresData}")
        choresViewModel.getAllChoresData.observe(viewLifecycleOwner, Observer {
            choresListAdapter?.setData(it)
        })

        rv.adapter = choresListAdapter

    }




}