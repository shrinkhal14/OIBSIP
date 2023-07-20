package com.example.unitconvertor

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var etInput: EditText
    lateinit var spinnerFrom: Spinner
    lateinit var spinnerTo: Spinner
    lateinit var btnConvert: Button
    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInput = findViewById(R.id.etInput)
        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        btnConvert = findViewById(R.id.btnConvert)
        tvResult = findViewById(R.id.tvResult)

        val units = resources.getStringArray(R.array.length_units)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        btnConvert.setOnClickListener {
            convertUnits()
        }
    }

    private fun convertUnits() {
        val input = etInput.text.toString().toDouble()
        val fromUnit = spinnerFrom.selectedItem.toString()
        val toUnit = spinnerTo.selectedItem.toString()

        val result = when {
            fromUnit == "Meters" && toUnit == "Feet" -> metersToFeet(input)
            fromUnit == "Feet" && toUnit == "Meters" -> feetToMeters(input)
            else -> input // If the same units are selected, no conversion needed.
        }

        tvResult.text = "$input $fromUnit is equal to $result $toUnit"
    }

    private fun metersToFeet(meters: Double): Double {
        return meters * 3.28084
    }

    private fun feetToMeters(feet: Double): Double {
        return feet / 3.28084
    }
}
