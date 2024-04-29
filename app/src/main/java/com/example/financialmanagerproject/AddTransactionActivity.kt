package com.example.financialmanagerproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.example.financialmanagerproject.databinding.ActivityAddTransactionBinding

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val description = binding.transactionDescriptionText.text.toString()
            val value = binding.transactionValueText.text.toString().toDouble()
            val radioGroup = findViewById<RadioGroup>(R.id.radioGroupType)
            val selectedOption = radioGroup.checkedRadioButtonId

            val type = when (selectedOption) {
                R.id.radioIncome -> TransactionType.INCOME
                R.id.radioExpense -> TransactionType.EXPENSE
                else -> TransactionType.INCOME
            }

            val resultIntent = Intent()
            resultIntent.putExtra("description", description)
            resultIntent.putExtra("value", value)
            resultIntent.putExtra("type", type.toString())
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}