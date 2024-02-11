package com.mube.billcalculation.ui.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mube.billcalculation.R
import com.mube.billcalculation.ui.models.OrderUiState

@Composable
fun OrderItem(item: OrderUiState.Item) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Row {
                Text(text = stringResource(id = R.string.quantity, item.quantity), fontSize = 16.sp, modifier = Modifier.padding(end = 4.dp))
                Text(text = item.name, fontSize = 16.sp)
            }
            Text(text = item.totalPriceFormatted, fontSize = 16.sp)
        }

    }
}

@Preview
@Composable
private fun OrderItemPreview() {
    OrderItem(
        item = OrderUiState.Item(
            productId = 100,
            name = "Nachos",
            quantity = 1,
            totalPriceFormatted = "$9.90"
        )
    )
}