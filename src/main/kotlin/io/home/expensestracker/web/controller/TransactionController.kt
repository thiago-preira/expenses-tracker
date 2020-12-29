package io.home.expensestracker.web.controller;

import io.home.expensestracker.config.FileUploadConfig
import io.home.expensestracker.extensions.summarizeCredits
import io.home.expensestracker.extensions.summarizeDebits
import io.home.expensestracker.model.Transaction
import io.home.expensestracker.service.TransactionService
import io.home.expensestracker.web.response.Message
import io.home.expensestracker.web.response.Summary
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.format.annotation.DateTimeFormat.ISO;

@RestController
@RequestMapping("/v1/transactions")
class TransactionController(
    val transactionService: TransactionService,
    val uploadConfig: FileUploadConfig
) {
    private val root: Path = Paths.get(uploadConfig.uploadPath)
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/upload")
    fun uploadExpenses(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        if (file.isEmpty) {
            return ResponseEntity.badRequest().body(Message("File is empty"))
        }
        log.info("Handling file ${file.originalFilename}")
        val resolvedPath = this.root.resolve(file.originalFilename)
        val savedFile = File(resolvedPath.toUri())
        return try {
            Files.copy(file.inputStream, resolvedPath)
            transactionService.handleFile(savedFile)
            ResponseEntity.ok(Message("File uploaded successfully"))
        } catch (e: Exception) {
            log.error("Failed to handle file ${file.originalFilename}", e)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Message("Failed to upload file ${file.originalFilename}"))
        } finally {
            savedFile.delete()
        }
    }

    @GetMapping
    fun listTransactions(
        @RequestParam("startDate")
        @DateTimeFormat(iso = ISO.DATE)
        startDate: LocalDate,
        @RequestParam("endDate")
        @DateTimeFormat(iso = ISO.DATE)
        endDate: LocalDate
    ): ResponseEntity<Summary> {

        val transactionsByDate = transactionService.listByDate(startDate, endDate)
        log.info("$transactionsByDate")
        val credits = transactionsByDate.summarizeCredits()
        val debits = transactionsByDate.summarizeDebits()

        return ResponseEntity.ok(
            Summary(
                credits.minus(debits),
                debits,
                credits,
                transactionsByDate
            )
        )
    }

    @PutMapping("/{transactionId}/category/{categoryId}")
    fun update(@PathVariable("transactionId") id: Long, @PathVariable("categoryId") categoryId: Long):
            ResponseEntity<Transaction> {
        return ResponseEntity.ok(transactionService.categorize(id, categoryId))
    }

}