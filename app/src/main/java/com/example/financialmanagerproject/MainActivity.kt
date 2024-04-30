package com.example.financialmanagerproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financialmanagerproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val transactionsList = ArrayList<Transaction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewTransactionsButton.setOnClickListener {
            val intent = Intent(this, TransactionsListActivity::class.java)
            intent.putExtra("transactions", transactionsList)
            startActivity(intent)
        }

        binding.addTransactionButton.setOnClickListener {
            startActivityForResult(Intent(this, AddTransactionActivity::class.java), 1)
        }

        updateUI()
    }

    private fun updateUI() {
        var totalIncome = 0.0
        var totalExpense = 0.0

        for (transaction in transactionsList) {
            if (transaction.type == TransactionType.RECEITA) {
                totalIncome += transaction.value
            } else if (transaction.type == TransactionType.DESPESA) {
                totalExpense += transaction.value
            }
        }

        val balance = totalIncome - totalExpense

        binding.totalIncomeValueTextView.text = getString(R.string.currency_format, totalIncome)
        binding.totalExpenseValueTextView.text = getString(R.string.currency_format, totalExpense)
        binding.balanceValueTextView.text = getString(R.string.currency_format, balance)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            data?.let {
                val title = it.getStringExtra("title") ?: ""
                val description = it.getStringExtra("description") ?: ""
                val value = it.getDoubleExtra("value", 0.0)
                val type = TransactionType.valueOf(it.getStringExtra("type") ?: "INCOME")
                val newTransaction = Transaction(title, description, value, type)
                transactionsList.add(newTransaction)
                updateUI()

            }
        }
    }
}