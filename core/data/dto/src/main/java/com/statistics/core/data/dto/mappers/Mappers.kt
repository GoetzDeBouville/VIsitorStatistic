package com.statistics.core.data.dto.mappers

import com.statistics.core.data.dto.FileDto
import com.statistics.core.data.dto.StatisticDto
import com.statistics.core.data.dto.UserDto
import com.statistics.domain.models.File
import com.statistics.domain.models.Statistic
import com.statistics.domain.models.User

fun FileDto.toDomain(): File =
    File(id = id, type = type, url = url)

fun StatisticDto.toDomain(): Statistic =
    Statistic(
        dates = dates,
        type = type,
        userId = userId
    )

fun UserDto.toDomain(): User =
    User(
        id = id,
        age = age,
        files = files.map {
            it.toDomain()
        },
        isOnline = isOnline,
        sex = sex,
        username = username
    )