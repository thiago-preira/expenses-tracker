package io.home.expensestracker.service;

import io.home.expensestracker.mapper.TransactionMapper
import io.home.expensestracker.model.Transaction
import io.home.expensestracker.repository.CategoryRepository
import io.home.expensestracker.repository.TransactionRepository
import io.home.expensestracker.web.response.ExpensesChartResponse
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalDate
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
@Transactional
class TransactionService(
        val transactionRepository: TransactionRepository,
        val categoryRepository: CategoryRepository,
        val parser: CSVParser = CSVParser,
        val mapper: TransactionMapper
){

    fun handleFile(file: File) {
        parser.parse(file) { row ->
            val transaction = mapper.map(row)
            transactionRepository.save(transaction)
        }
    }

    fun listByDate(startDate: LocalDate, endDate: LocalDate): List<Transaction>{
        return transactionRepository.findByDateBetweenOrderByDateAsc(startDate, endDate)
    }

    fun categorize(transactionId: Long, categoryId: Long):Transaction {
        if(!transactionRepository.existsById(transactionId)){
            throw EntityNotFoundException("No entity found with id $transactionId")
        }
        if(!categoryRepository.existsById(categoryId)){
            throw EntityNotFoundException("No entity found with id $categoryId")
        }

        val transaction = transactionRepository.findById(transactionId).get()
        val category = categoryRepository.findById(categoryId).get()
        transaction.category = category

        return transactionRepository.save(transaction)
    }

    fun categoryChart(startDate: LocalDate, endDate: LocalDate): List<ExpensesChartResponse>{
        val group =
            transactionRepository.findDebitTransactionGroupedByCategory(startDate, endDate);
        return group.map { ExpensesChartResponse(it.name,it.amount) }
    }

    fun groupChart(startDate: LocalDate, endDate: LocalDate): List<ExpensesChartResponse>{
        val group =
            transactionRepository.findDebitTransactionGroupedByGroup(startDate, endDate);
        return group.map { ExpensesChartResponse(it.name,it.amount) }
    }

}