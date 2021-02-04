package io.home.expensestracker.web.controller

import io.home.expensestracker.service.TransactionService
import io.home.expensestracker.web.response.ExpensesChartResponse
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/v1/chart")
class ChartController(
    val transactionService: TransactionService
) {

    @GetMapping("/category")
    fun topTransactionsPerCategory(@RequestParam("startDate")
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   startDate: LocalDate,
                                   @RequestParam("endDate")
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   endDate: LocalDate): ResponseEntity<List<ExpensesChartResponse>>{
        val response = transactionService.categoryChart(startDate, endDate)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/group")
    fun topTransactionsPerGroup(@RequestParam("startDate")
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   startDate: LocalDate,
                                   @RequestParam("endDate")
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                   endDate: LocalDate): ResponseEntity<List<ExpensesChartResponse>>{
        val response = transactionService.groupChart(startDate, endDate)
        return ResponseEntity.ok(response)
    }
}