package com.minutesock.fetchhiring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.minutesock.fetchhiring.feat.home.presentation.ui.HomeHost
import com.minutesock.fetchhiring.ui.theme.FetchHiringTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchHiringTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.fetch_rewards_logo),
            contentDescription = "Fetch rewards logo"
        )
    }
}