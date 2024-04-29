package com.example.financialmanagerproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.financialmanagerproject.databinding.ActivityTransactionsListBinding
import com.example.financialmanagerproject.databinding.FragmentTransactionDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TransactionDetailFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentTransactionDetailBinding
    private var transaction: Transaction? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionDetailBinding.inflate(inflater)

        transaction = arguments?.getSerializable("transaction") as Transaction?

        binding.tvDescription.text = transaction?.description
        binding.tvAmount.text = transaction?.value.toString()
        binding.tvType.text = transaction?.type.toString()

        return binding.root
    }

    companion object {
        fun newInstance(transaction: Transaction): TransactionDetailFragment =
            TransactionDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("transaction", transaction)
                }
            }
    }
}