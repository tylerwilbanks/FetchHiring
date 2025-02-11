package com.minutesock.fetchhiring.core.domain

import com.minutesock.remote.api.HiringDTO

fun HiringDTO.toListItem(): HiringListItem {
    return HiringListItem(
        id = id,
        listId = listId,
        name = name
    )
}