package com.minutesock.fetchhiring.feat.home.presentation

import com.minutesock.fetchhiring.core.domain.HiringListItem
import com.minutesock.fetchhiring.core.uiutil.TextRes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeState(
    val errorMessage: TextRes = TextRes.empty(),
    val fetchingItems: Boolean = false,
    val items: ImmutableList<HiringListItem> = persistentListOf()
)
