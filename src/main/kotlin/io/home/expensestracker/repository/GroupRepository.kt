package io.home.expensestracker.repository

import io.home.expensestracker.model.Group
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository : JpaRepository<Group, Long> {
}