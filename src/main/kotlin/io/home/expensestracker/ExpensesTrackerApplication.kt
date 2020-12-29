package io.home.expensestracker

import io.home.expensestracker.config.FileUploadConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(FileUploadConfig::class)
class ExpensesTrackerApplication

fun main(args: Array<String>) {
	runApplication<ExpensesTrackerApplication>(*args)
}
