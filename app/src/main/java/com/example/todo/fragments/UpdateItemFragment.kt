package com.example.todo.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.todo.R
import com.example.todo.databinding.FragmentUpdateItemBinding


class UpdateItemFragment : Fragment() {

    private lateinit var binding: FragmentUpdateItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateItemBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.update_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.save -> {
                        Toast.makeText(activity, "Chore saved", Toast.LENGTH_SHORT)
                        true
                    }
                    R.id.delete -> {
                        Toast.makeText(activity, "Chore deleted", Toast.LENGTH_SHORT)
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}