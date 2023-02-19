package com.example.gymproject.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.Exercise
import com.example.gymproject.R
import com.example.gymproject.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.ExerciseItemClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        binding.searchBtn.setOnClickListener {
            if (binding.searchText.text.toString().trim().isEmpty()) {
                Toast.makeText(requireContext(), "Enter the query", Toast.LENGTH_SHORT).show()
            } else {
                mainViewModel.searchQuery.value = binding.searchText.text.toString().trim()
                mainViewModel.getExercisesByBodyPart()
            }
        }

        mainViewModel.exercises.observe(viewLifecycleOwner) {list ->
            adapter.differ.submitList(list)
        }

        mainViewModel.loaderState.observe(viewLifecycleOwner) {
            if (it == false) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

    }
    override fun onItemClicked(exercise: Exercise) {
        val bundle = Bundle()
        bundle.putString("name", exercise.name)
        bundle.putString("target", exercise.target)
        bundle.putString("equipment", exercise.equipment)
        bundle.putString("bodyPart", exercise.bodyPart)
        bundle.putString("gifUrl", exercise.gifUrl)

        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundle
        )
    }

    private fun initAdapter() {
        adapter = MainAdapter(this)
        binding.exerciseRv.layoutManager = LinearLayoutManager(requireContext())
        binding.exerciseRv.adapter = adapter
    }
}