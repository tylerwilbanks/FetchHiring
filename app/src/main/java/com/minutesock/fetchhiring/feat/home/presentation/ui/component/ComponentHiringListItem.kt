package com.minutesock.fetchhiring.feat.home.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.minutesock.fetchhiring.R
import com.minutesock.fetchhiring.core.domain.HiringListItem

@Composable
fun ComponentHiringListItem(item: HiringListItem) {
    Row(
        modifier = Modifier
            .width(300.dp)
            .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = RoundedCornerShape(25)
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(50.dp).padding(5.dp),
            painter = painterResource(R.drawable.fetch_rewards_icon),
            contentDescription = "Fetch rewards logo."
        )
        Column {
            Text("id: ${item.id}")
            VerticalDivider(Modifier.size(5.dp))
            Text("list id: ${item.listId}")
            VerticalDivider(Modifier.size(5.dp))
            Text("name: ${item.name}")
        }
        Image(
            modifier = Modifier.size(50.dp).padding(5.dp),
            painter = painterResource(R.drawable.fetch_rewards_icon),
            contentDescription = "Fetch rewards logo."
        )
    }
}