package io.home.expensestracker.extensions

import io.home.expensestracker.model.Transaction
import io.home.expensestracker.model.TransactionType
import java.math.BigDecimal

private fun List<Transaction>.summarize(type: TransactionType): BigDecimal{
    return this.asSequence().filter { it.transactionType == type }.map { it.amount }
            .fold(BigDecimal.ZERO) { acc:BigDecimal, amount -> acc.add(amount) }
}

fun List<Transaction>.summarizeDebits():BigDecimal{
    return summarize(TransactionType.DEBIT)
}

fun List<Transaction>.summarizeCredits():BigDecimal{
    return summarize(TransactionType.CREDIT)
}

fun Transaction.isDebit(): Boolean{
    return this.transactionType == TransactionType.DEBIT
}

fun Transaction.isCredit(): Boolean{
    return this.transactionType == TransactionType.CREDIT
}