package com.minutesock.fetchhiring.feat.home.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.minutesock.fetchhiring.core.uiutil.viewModelFactory
import com.minutesock.fetchhiring.feat.home.presentation.HomeState
import com.minutesock.fetchhiring.feat.home.presentation.HomeViewModel

@Composable
fun HomeHost(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel(factory = viewModelFactory { HomeViewModel() })
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        modifier = modifier,
        state = state
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    state: HomeState
) {
    Text(
        modifier = modifier,
        text = "Item count: ${state.items.size}"
    )
}