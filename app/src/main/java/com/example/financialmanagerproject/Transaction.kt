package com.example.financialmanagerproject;

import java.io.Serializable

enum class TransactionType {
    INCOME, EXPENSE
}

data class Transaction(
    val description: String,
    val value: Double,
    val type: TransactionType
) : Serializable