package com.example.gymproject.ui.calculator

import android.R.attr.x
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gymproject.databinding.FragmentCalculatorBinding
import kotlin.math.roundToInt


class CalculatorFragment : Fragment() {

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

        binding.calculatePr.setOnClickListener {
            validateFields()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun validateFields() {
        if (binding.reps.text!!.trim().toString().isEmpty()) {
            binding.reps.error = "Please, input your reps"
        }

        if (binding.reps.text!!.trim().toString().toInt() <= 1) {
            binding.reps.error = "The number of repetitions must be greater than 1"
        }

        if (binding.workingWeight.text!!.trim().toString().isEmpty()) {
            binding.workingWeight.error = "Please, input your working weight"
        }

        if (binding.reps.text!!.trim().toString().isNotEmpty() && binding.workingWeight.text!!.trim().toString().isNotEmpty()) {
            val r: Int = binding.reps.text.toString().toInt()
            val w: Int = binding.workingWeight.text.toString().toInt()
            val result = w / (1.0278 - (0.0278 * r))
            binding.yourPrTv.text = "Your PR is ${result.roundToInt()} kg"
        }
    }
}