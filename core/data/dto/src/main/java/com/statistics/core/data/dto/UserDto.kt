package com.statistics.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id") val id: Int,
    @SerialName("age") val age: Int,
    @SerialName("files") val files: List<FileDto>,
    @SerialName("isOnline") val isOnline: Boolean,
    @SerialName("sex") val sex: String,
    @SerialName("username") val username: String
)