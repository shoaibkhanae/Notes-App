package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.data.local.Item
import com.example.notesapp.databinding.FragmentAddBinding
import com.example.notesapp.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding
        get() = _binding!!

    private val shareViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addFragment = this
    }

    fun submitNotes() {
        binding.apply {
            val title = etTitle.text.toString()
            val body = etDescription.text.toString()

            val item = Item(title = title, description = body)
            shareViewModel.insert(item)
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
            view?.let { Snackbar.make(it,"Notes Inserted.",Snackbar.LENGTH_SHORT).show() }
        }
    }

    fun goBack() {
        findNavController().navigate(R.id.action_addFragment_to_homeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}