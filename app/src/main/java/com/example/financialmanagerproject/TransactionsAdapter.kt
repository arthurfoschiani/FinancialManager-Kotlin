package com.example.financialmanagerproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financialmanagerproject.databinding.ItemTransactionBinding

class TransactionsAdapter(
    private var transactions: ArrayList<Transaction>,
    private val onTransactionClick: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, onTransactionClick)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int = transactions.size

    class TransactionViewHolder(
        private val binding: ItemTransactionBinding,
        private val onTransactionClick: (Transaction) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.textViewTitle.text = transaction.title
            binding.textViewAmount.text = transaction.value.toString()
            binding.textViewType.text = transaction.type.name
            itemView.setOnClickListener {
                onTransactionClick(transaction)
            }
        }
    }
}
