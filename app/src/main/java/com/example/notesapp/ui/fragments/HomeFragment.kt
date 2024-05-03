package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.adapter.NotesAdapter
import com.example.notesapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val shareViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeFragment = this
        setupUI()
    }

    fun createNotes() {
        findNavController().navigate(R.id.action_homeFragment_to_addFragment)
    }

    private fun setupUI() {
        val adapter = NotesAdapter()
        binding.recyclerview.adapter = adapter

        adapter.setOnItemClickListener(object : NotesAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val current = adapter.currentList[position]

                val action = HomeFragmentDirections
                    .actionHomeFragmentToUpdateFragment(current.id,current.title,current.description)
                findNavController().navigate(action)
            }
        })

        shareViewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}