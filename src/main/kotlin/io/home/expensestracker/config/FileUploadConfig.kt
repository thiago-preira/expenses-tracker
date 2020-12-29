package io.home.expensestracker.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "app")
data class FileUploadConfig(val uploadPath: String)