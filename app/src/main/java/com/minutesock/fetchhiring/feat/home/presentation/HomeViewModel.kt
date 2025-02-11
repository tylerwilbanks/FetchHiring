package com.minutesock.fetchhiring.feat.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minutesock.fetchhiring.core.uiutil.TextRes
import com.minutesock.fetchhiring.core.util.ContinuousOption
import com.minutesock.fetchhiring.feat.home.data.HomeRepository
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository = HomeRepository()
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            homeRepository.fetchHiringItems().collect { continuousOption ->
                when (continuousOption) {
                    is ContinuousOption.Issue -> _state.update {
                        it.copy(
                            errorMessage = continuousOption.issue.textRes,
                            fetchingItems = false,
                            items = continuousOption.data ?: persistentListOf()
                        )
                    }
                    is ContinuousOption.Loading -> _state.update {
                        it.copy(
                            errorMessage = continuousOption.continuousStatus.textRes,
                            fetchingItems = true,
                            items = continuousOption.data
                        )
                    }
                    is ContinuousOption.Success -> _state.update {
                        it.copy(
                            errorMessage = TextRes.empty(),
                            fetchingItems = false,
                            items = continuousOption.data
                        )
                    }
                }
            }
        }
    }
}