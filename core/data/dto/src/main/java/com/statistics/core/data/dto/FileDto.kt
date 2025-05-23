package com.statistics.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileDto(
    @SerialName("id") val id: Int,
    @SerialName("type") val type: String,
    @SerialName("url") val url: String
)