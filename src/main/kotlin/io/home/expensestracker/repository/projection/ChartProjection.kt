package io.home.expensestracker.repository.projection

import java.math.BigDecimal

interface ChartProjection {
    val name:String
    val amount: BigDecimal
}