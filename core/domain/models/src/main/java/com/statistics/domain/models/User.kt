package com.statistics.domain.models

data class User(
    val id: Int,
    val age: Int,
    val files: List<File>,
    val isOnline: Boolean,
    val sex: String,
    val username: String
)