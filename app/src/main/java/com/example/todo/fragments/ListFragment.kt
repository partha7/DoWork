package com.example.todo.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

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

    }

}