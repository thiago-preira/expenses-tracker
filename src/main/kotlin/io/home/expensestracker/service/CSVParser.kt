package io.home.expensestracker.service

import java.io.File

object CSVParser {

    private const val BOM = "\uFEFF"
    private const val REGEX = ","

    fun parse(file: File, processor: (Map<String, String>) -> Unit) {

        val header = file.useLines { it.firstOrNull()?.replace(BOM, "")?.split(",") }
                ?: throw Exception("This file does not contain a valid header")

        file.useLines { linesSequence ->
            linesSequence
                    .drop(1)
                    .map { it.trim().replace("\"","").split(REGEX) }
                    .map { header.map { item-> item.trim().replace("\"","") }.zip(it).toMap() }
                    .forEach(processor)
        }
    }

}