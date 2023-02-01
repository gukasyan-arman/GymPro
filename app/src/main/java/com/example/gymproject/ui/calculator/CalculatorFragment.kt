package com.example.gymproject.ui.calculator

import android.R.attr.x
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
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

        binding.calculatePr.isEnabled = false
        binding.calculatePr.alpha = 0.5F

        setClickableButton()

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

        if (binding.searchText.text!!.trim().toString().isEmpty()) {
            binding.searchText.error = "Please, input your working weight"
        }

        if (binding.reps.text!!.trim().toString().isNotEmpty() && binding.searchText.text!!.trim().toString().isNotEmpty()) {
            val r: Int = binding.reps.text.toString().toInt()
            val w: Int = binding.searchText.text.toString().toInt()
            val result = w / (1.0278 - (0.0278 * r))
            binding.yourPrTv.text = "Your PR is ${result.roundToInt()} kg"
        }
    }

    private fun setClickableButton() {
        binding.searchText.addTextChangedListener {
            if (it.toString().trim().isNotEmpty()) {
                if (binding.reps.text.toString().trim().isNotEmpty()) {
                    binding.calculatePr.isEnabled = true
                    binding.calculatePr.alpha = 1F
                }
            } else {
                if (binding.searchText.text.toString().trim().isEmpty() || binding.reps.text.toString().trim().isEmpty()) {
                    binding.calculatePr.isEnabled = false
                    binding.calculatePr.alpha = 0.5F
                }
            }
        }
        binding.reps.addTextChangedListener {
            if (it.toString().trim().isNotEmpty()) {
                if (binding.searchText.text.toString().trim().isNotEmpty()) {
                    binding.calculatePr.isEnabled = true
                    binding.calculatePr.alpha = 1F
                }
            } else {
                if (binding.searchText.text.toString().trim().isEmpty() || binding.reps.text.toString().trim().isEmpty()) {
                    binding.calculatePr.isEnabled = false
                    binding.calculatePr.alpha = 0.5F
                }
            }
        }

    }

}