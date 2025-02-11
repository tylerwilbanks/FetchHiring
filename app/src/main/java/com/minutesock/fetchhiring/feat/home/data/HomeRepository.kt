package com.minutesock.fetchhiring.feat.home.data

import com.minutesock.fetchhiring.core.domain.FetchApplication
import com.minutesock.fetchhiring.core.domain.HiringListItem
import com.minutesock.fetchhiring.core.domain.toListItem
import com.minutesock.fetchhiring.core.uiutil.TextRes
import com.minutesock.fetchhiring.core.util.ContinuousOption
import com.minutesock.fetchhiring.core.util.ContinuousStatus
import com.minutesock.fetchhiring.core.util.GeneralIssue
import com.minutesock.remote.ApiResponse
import com.minutesock.remote.api.HiringApi
import com.minutesock.remote.api.HiringDTO
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository(
    private val hiringApi: HiringApi = FetchApplication.appModule.hiringApi,
    private val defaultDispatcher: CoroutineDispatcher = FetchApplication.appModule.defaultDispatcher
) {

    fun fetchHiringItems() = flow<ContinuousOption<ImmutableList<HiringListItem>, GeneralIssue>> {
        // if a database is implemented, here you could grab the items from the database to display
        // while fetching new items
        emit(ContinuousOption.Loading(persistentListOf(), ContinuousStatus.Indefinite()))
        val continuousOption = when (val response = hiringApi.fetchHiringList()) {
            is ApiResponse.Error -> ContinuousOption
                .Issue(
                    data = persistentListOf(),
                    issue = GeneralIssue(
                        textRes = TextRes.Raw(response.message)
                    )
                )
            is ApiResponse.Success -> ContinuousOption.Success(
                data = response
                    .data
                    .filterNot { it.name.isNullOrBlank() }
                    .groupBy { it.listId }
                    .flatMap { entry ->
                        entry.value
                            .sortedWith(
                                compareBy(
                                    { it.listId }, { it.name?.substringAfterLast(" ")?.toIntOrNull() ?: Int.MAX_VALUE }
                                )
                            ).map(HiringDTO::toListItem)
                    }
                    .toImmutableList()
            )
        }
        emit(continuousOption)
    }.flowOn(defaultDispatcher)
}