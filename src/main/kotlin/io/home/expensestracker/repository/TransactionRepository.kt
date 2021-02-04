package io.home.expensestracker.repository;

import io.home.expensestracker.model.Transaction;
import io.home.expensestracker.repository.projection.ChartProjection
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface TransactionRepository: JpaRepository<Transaction,Long>{

    fun findByDateBetweenOrderByDateAsc(startDate: LocalDate, endDate: LocalDate): List<Transaction>

    @Query(value = """
        select 
        c.name as "name",sum(t.amount) as "amount"
        from transactions t inner join categories c on t.category_id  = c.id
        where "date" between :startDate and :endDate and t."type" ='DEBIT'
        group by c.name order by 2 desc ;
    """,nativeQuery = true)
    fun findDebitTransactionGroupedByCategory(startDate: LocalDate, endDate: LocalDate):List<ChartProjection>

    @Query(value = """
        select 
        g.name as "name",sum(t.amount) as "amount"
        from transactions t inner join categories c on t.category_id  = c.id
        inner join groups g on g.id = c.group_id 
        where "date" between :startDate and :endDate and t."type" ='DEBIT'
        group by g.name order by 2 desc ;
    """,nativeQuery = true)
    fun findDebitTransactionGroupedByGroup(startDate: LocalDate, endDate: LocalDate):List<ChartProjection>
}
