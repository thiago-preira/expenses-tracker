package io.home.expensestracker.web.response

import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ExpensesChartResponse(
    val name: String,
    val value: BigDecimal
)