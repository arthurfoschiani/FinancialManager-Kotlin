package com.example.financialmanagerproject;

import java.io.Serializable

enum class TransactionType {
    RECEITA, DESPESA
}

data class Transaction (
    val title: String,
    val description: String,
    val value: Double,
    val type: TransactionType
) : Serializable