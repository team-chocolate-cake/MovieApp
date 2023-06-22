package com.chocolatecake.entities

data class StatusEntity(
    val success: Boolean,
    val statusCode: Int,
    val statusMessage: String
)
