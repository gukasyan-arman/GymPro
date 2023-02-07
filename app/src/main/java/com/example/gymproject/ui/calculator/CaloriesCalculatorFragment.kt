package com.example.gymproject.ui.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.widget.addTextChangedListener
import com.example.gymproject.R
import com.example.gymproject.databinding.FragmentCaloriesCalculatorBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CaloriesCalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCaloriesCalculatorBinding
    private lateinit var navBar: BottomNavigationView
    private var result = 0.0
    private var lifeActivityPos = 0
    private var height = 0
    private var weight = 0
    private var age = 0
    private var isMale = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCaloriesCalculatorBinding.inflate(inflater, container, false)
        navBar = requireActivity().findViewById(R.id.bottomNavigationView)
        navBar.visibility = View.GONE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateCalories.isEnabled = false
        binding.calculateCalories.alpha = 0.5F

        setClickableButton()

        val spinner: Spinner = binding.activityDropdown
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.life_activity_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.activityDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                lifeActivityPos = position
            }

        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            isMale = R.id.maleBtn == checkedId
        }

        binding.calculateCalories.setOnClickListener {
            validateFields()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        navBar.visibility = View.VISIBLE
        binding.caloriesText.visibility = View.GONE
        binding.caloriesValue.visibility = View.GONE
    }

    private fun validateFields() {
        if (binding.ageInput.text.toString().trim().isEmpty()) {
            binding.ageInput.error = "Please, input your age"
        }
        if (binding.weightInput.text.toString().trim().isEmpty()) {
            binding.weightInput.error = "Please, input your weight"
        }
        if (binding.highInput.text.toString().trim().isEmpty()) {
            binding.highInput.error = "Please, input your height"
        }

        if (binding.ageInput.text.toString().trim().isNotEmpty() &&
            binding.highInput.text.toString().trim().isNotEmpty() &&
            binding.weightInput.text.toString().trim().isNotEmpty()) {

            height = binding.highInput.text.toString().toInt()
            weight = binding.weightInput.text.toString().toInt()
            age = binding.ageInput.text.toString().toInt()

            result = if (isMale) {
                66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * age)
            } else {
                655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age)
            }

            when (lifeActivityPos) {
                0 -> result *= 1.2
                1 -> result *= 1.375
                2 -> result *= 1.55
                3 -> result *= 1.7
                4 -> result *= 1.9
            }

        }

        binding.caloriesValue.text = result.toString()
        binding.caloriesText.visibility = View.VISIBLE
        binding.caloriesValue.visibility = View.VISIBLE

    }

    private fun setClickableButton() {
        binding.ageInput.addTextChangedListener {
            if (it.toString().trim().isNotEmpty()) {
                if (binding.highInput.text.toString().trim().isNotEmpty() &&
                    binding.weightInput.text.toString().trim().isNotEmpty()) {
                    binding.calculateCalories.isEnabled = true
                    binding.calculateCalories.alpha = 1F
                }
            } else {
                if (binding.ageInput.text.toString().trim().isEmpty() || binding.highInput.text.toString().trim().isEmpty() ||
                    binding.weightInput.text.toString().trim().isEmpty()) {
                    binding.calculateCalories.isEnabled = false
                    binding.calculateCalories.alpha = 0.5F
                }
            }
        }
        binding.highInput.addTextChangedListener {
            if (it.toString().trim().isNotEmpty()) {
                if (binding.ageInput.text.toString().trim().isNotEmpty() &&
                    binding.weightInput.text.toString().trim().isNotEmpty()) {
                    binding.calculateCalories.isEnabled = true
                    binding.calculateCalories.alpha = 1F
                }
            } else {
                if (binding.ageInput.text.toString().trim().isEmpty() || binding.highInput.text.toString().trim().isEmpty() ||
                    binding.weightInput.text.toString().trim().isEmpty()) {
                    binding.calculateCalories.isEnabled = false
                    binding.calculateCalories.alpha = 0.5F
                }
            }
        }
        binding.weightInput.addTextChangedListener {
            if (it.toString().trim().isNotEmpty()) {
                if (binding.highInput.text.toString().trim().isNotEmpty() &&
                    binding.ageInput.text.toString().trim().isNotEmpty()) {
                    binding.calculateCalories.isEnabled = true
                    binding.calculateCalories.alpha = 1F
                }
            } else {
                if (binding.ageInput.text.toString().trim().isEmpty() || binding.highInput.text.toString().trim().isEmpty() ||
                    binding.weightInput.text.toString().trim().isEmpty()) {
                    binding.calculateCalories.isEnabled = false
                    binding.calculateCalories.alpha = 0.5F
                }
            }
        }
    }
}