package com.example.gymproject.ui.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gymproject.R
import com.example.gymproject.databinding.FragmentBmiCalculatorBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.roundToInt

class BmiCalculatorFragment : Fragment() {

    private lateinit var binding: FragmentBmiCalculatorBinding
    private lateinit var navBar: BottomNavigationView
    private var result = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBmiCalculatorBinding.inflate(inflater, container, false)
        navBar = requireActivity().findViewById(R.id.bottomNavigationView)
        navBar.visibility = View.GONE
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
        if (binding.height.text!!.trim().toString().isEmpty()) {
            binding.height.error = "Please, input your height"
        }

        if (binding.weight.text!!.trim().toString().isEmpty()) {
            binding.weight.error = "Please, input your weight"
        }

        if (binding.height.text!!.trim().toString().isNotEmpty() && binding.weight.text!!.trim().toString().isNotEmpty()) {
            val h: Double = binding.height.text.toString().toDouble()
            val w: Int = binding.weight.text.toString().toInt()
            val h2 = h / 100
            result = w / (h2 * h2)
            result = (result * 100.0).roundToInt() / 100.0

            binding.yourPrTv.text = "Your BMI is $result"
        }

        if (result <= 16.00) {
            binding.diapozon.text = "16 and less"
            binding.description.text = "Severe Thinness"
        }

        else if (result > 16.00 && result <= 17.00) {
            binding.diapozon.text = "16 - 17"
            binding.description.text = "Moderate Thinness"
        }

        else if (result > 17.00 && result <= 18.50) {
            binding.diapozon.text = "17 - 18.5"
            binding.description.text = "Mild Thinness"
        }

        else if (result > 18.50 && result <= 25.00) {
            binding.diapozon.text = "18.5 - 25"
            binding.description.text = "Normal"
        }

        else if (result > 25.00 && result <= 30.00) {
            binding.diapozon.text = "25 - 30"
            binding.description.text = "Overweight"
        }

        else if (result > 30.00 && result <= 35.00) {
            binding.diapozon.text = "30 - 35"
            binding.description.text = "Obese Class I"
        }

        else if (result > 35.00 && result <= 40.00) {
            binding.diapozon.text = "35 - 40"
            binding.description.text = "Obese Class II"
        }

        else if (result > 40.00) {
            binding.diapozon.text = "40 and more"
            binding.description.text = "Obese Class III"
        }

        binding.diapozon.visibility = View.VISIBLE
        binding.description.visibility = View.VISIBLE

    }

    override fun onDestroy() {
        super.onDestroy()
        navBar.visibility = View.VISIBLE
    }
}