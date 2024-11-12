package com.example.android.ui_lab

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android.ui_lab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set the button click listener
        binding.calculateButton.setOnClickListener {
            calculateResult()
        }
    }
    private fun calculateResult() {
        val num1Text = binding.number1.text.toString()
        val num2Text = binding.number2.text.toString()

        // Check if both numbers are provided
        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            binding.resultText.text = "Please enter both numbers."
            return
        }

        val num1 = num1Text.toDoubleOrNull()
        val num2 = num2Text.toDoubleOrNull()

        // Check if the inputs are valid numbers
        if (num1 == null || num2 == null) {
            binding.resultText.text = "Invalid number entered."
            return
        }

        val result = when (binding.operationGroup.checkedRadioButtonId) {
            R.id.add -> num1 + num2
            R.id.subtract -> num1 - num2
            R.id.multiply -> num1 * num2
            else -> {
                binding.resultText.text = "Please select an operation."
                return
            }
        }

        binding.resultText.text = "Result: $result"
    }
}