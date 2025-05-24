package com.statistics.data.impl.dto

import com.statistics.core.data.dto.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponseDto(
    @SerialName("users")
    val users: List<UserDto>
)