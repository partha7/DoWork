package com.example.todo.ui.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.data.models.ChoresData
import com.example.todo.viewmodels.ChoresViewModel
import com.example.todo.databinding.FragmentAddItemBinding
import com.example.todo.viewmodels.SharedViewModel

class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding

    private val choresViewModel: ChoresViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddItemBinding.inflate(layoutInflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.add_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.add_item -> {
                        insertDataToDB()
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.addItemPriority.onItemSelectedListener = sharedViewModel.listener
    }

    private fun insertDataToDB() {
        val title = binding.addItemTitle.text.toString()
        val priority = binding.addItemPriority.selectedItem.toString()
        val description = binding.addItemDescription.text.toString()

        if (sharedViewModel.validateChoreData(title, description)){
            val chore = ChoresData(
                0,
                title,
                sharedViewModel.parsePriorityString(priority),
                description
            )
            choresViewModel.insertChore(chore)
            Toast.makeText(activity, "TODO list updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addItemFragment_to_listFragment)
        } else
            Toast.makeText(activity, "Please fill all the required data", Toast.LENGTH_SHORT).show()
    }

}