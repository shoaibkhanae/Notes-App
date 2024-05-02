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
import com.example.notesapp.databinding.FragmentUpdateBinding
import com.example.notesapp.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding
        get() = _binding!!

    private val shareViewModel: MainViewModel by activityViewModels()

    private var id: Int = 0
    private lateinit var title: String
    private lateinit var description: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = UpdateFragmentArgs.fromBundle(it).id
            title = UpdateFragmentArgs.fromBundle(it).title
            description = UpdateFragmentArgs.fromBundle(it).description
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            etUpdateTitle.setText(title)
            etUpdateDescription.setText(description)
            updateFragment = this@UpdateFragment
        }
    }

    fun updateNotes() {
        val title = binding.etUpdateTitle.text.toString()
        val description = binding.etUpdateDescription.text.toString()

        val item = Item(id, title, description)
        shareViewModel.update(item)

        findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        view?.let { Snackbar.make(it, "Task Updated.",Snackbar.LENGTH_SHORT).show() }
    }

    fun deleteNotes() {
        val item = Item(id, title, description)
        shareViewModel.delete(item)

        findNavController().navigate(R.id.action_updateFragment_to_homeFragment)

        view?.let {
            Snackbar.make(it,"Task Deleted.",Snackbar.LENGTH_LONG).apply {
                setAction("undo") {
                    shareViewModel.insert(item)
                }
            }.show()
        }
    }

    fun goBack() {
        findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}