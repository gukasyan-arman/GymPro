package com.example.gymproject.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gymproject.R
import com.example.gymproject.databinding.FragmentCalculatorBinding


class CalculatorsFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.prCalculatorBtn.setOnClickListener {
            findNavController().navigate(R.id.action_calculatorFragment_to_prCalculatorFragment)
        }

        binding.bmiCalculatorBtn .setOnClickListener {
            findNavController().navigate(R.id.action_calculatorFragment_to_bmiCalculatorFragment)
        }

        binding.caloriesBmiCalculator.setOnClickListener {
            findNavController().navigate(R.id.action_calculatorFragment_to_caloriesCalculatorFragment)
        }
    }

}