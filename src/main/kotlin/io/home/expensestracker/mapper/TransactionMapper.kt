package io.home.expensestracker.mapper

import io.home.expensestracker.model.Transaction
import io.home.expensestracker.model.TransactionType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class TransactionMapper {
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    companion object{
        private const val DATE_KEY = "Posted Transactions Date"
        private const val DESCRIPTION_1_KEY = "Description1"
        private const val DESCRIPTION_2_KEY = "Description2"
        private const val DESCRIPTION_3_KEY = "Description3"
        private const val DEBIT_AMOUNT_KEY = "Debit Amount"
        private const val CREDIT_AMOUNT_KEY = "Credit Amount"
        private const val TRANSACTION_TYPE_KEY = "Transaction Type"
        private val DEBIT_VALUES_ITEMS = listOf("Direct Debit","Debit","ATM", "Topup")
    }

    fun map(row: Map<String,String>):Transaction{
        log.info("$row")
        val type = if (DEBIT_VALUES_ITEMS.contains(row[TRANSACTION_TYPE_KEY])) TransactionType.DEBIT else TransactionType.CREDIT
        val amount = if (type == TransactionType.DEBIT) row[DEBIT_AMOUNT_KEY] else row[CREDIT_AMOUNT_KEY]
        return Transaction(
                description = row[DESCRIPTION_1_KEY] ?: error("No description found"),
                extendedInfo = row[DESCRIPTION_2_KEY] +" "+ row[DESCRIPTION_3_KEY],
                amount = BigDecimal(amount),
                transactionType = type,
                date = LocalDate.parse(row[DATE_KEY], DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        )
    }
}