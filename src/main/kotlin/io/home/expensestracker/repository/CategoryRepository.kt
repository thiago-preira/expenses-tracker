package io.home.expensestracker.repository

import io.home.expensestracker.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}