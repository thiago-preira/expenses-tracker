package io.home.expensestracker.web.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.home.expensestracker.model.Transaction

import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Summary(
        val total: BigDecimal,
        val expenses: BigDecimal,
        val income: BigDecimal,
        val transactions: List<Transaction> ?= emptyList()
)