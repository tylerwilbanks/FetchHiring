package com.minutesock.fetchhiring.feat.home.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.minutesock.fetchhiring.R
import com.minutesock.fetchhiring.core.domain.HiringListItem
import com.minutesock.fetchhiring.core.uiutil.viewModelFactory
import com.minutesock.fetchhiring.feat.home.presentation.HomeState
import com.minutesock.fetchhiring.feat.home.presentation.HomeViewModel
import com.minutesock.fetchhiring.feat.home.presentation.ui.component.ComponentHiringListItem

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
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(R.drawable.fetch_rewards_logo),
                contentDescription = "Fetch rewards logo"
            )
        }

        item {
            AnimatedVisibility(
                modifier = Modifier.animateContentSize(),
                visible = state.fetchingItems
            ) {
                CircularProgressIndicator()
            }
        }

        items(state.items, key = { it.id }) { item: HiringListItem ->
            ComponentHiringListItem(item)
        }
    }
}