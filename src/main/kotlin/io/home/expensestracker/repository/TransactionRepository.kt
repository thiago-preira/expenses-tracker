package io.home.expensestracker.repository;

import io.home.expensestracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface TransactionRepository: JpaRepository<Transaction,Long>{

    fun findByDateBetweenOrderByDateAsc(startDate: LocalDate, endDate: LocalDate): List<Transaction>
}
