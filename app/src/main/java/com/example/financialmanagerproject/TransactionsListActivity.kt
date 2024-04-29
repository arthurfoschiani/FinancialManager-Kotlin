package com.example.financialmanagerproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financialmanagerproject.databinding.ActivityTransactionsListBinding

class TransactionsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionsListBinding
    private lateinit var transactionsAdapter: TransactionsAdapter
    private var transactionsList = ArrayList<Transaction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("transactions")) {
            transactionsList = intent.getSerializableExtra("transactions") as ArrayList<Transaction>
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        transactionsAdapter = TransactionsAdapter(transactionsList) { transaction ->
            val fragment = TransactionDetailFragment.newInstance(transaction)
            fragment.show(supportFragmentManager, fragment.tag)
        }
        binding.recyclerViewTransactions.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTransactions.adapter = transactionsAdapter
    }

    fun updateTransactions(newTransactions: List<Transaction>) {
        transactionsList.clear()
        transactionsList.addAll(newTransactions)
        transactionsAdapter.notifyDataSetChanged()
    }
}