package com.mube.billcalculation.ui.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mube.billcalculation.R
import com.mube.billcalculation.ui.models.OrderUiState
import com.mube.billcalculation.ui.viewmodels.OrderState
import com.mube.billcalculation.ui.viewmodels.OrderViewModel

@Composable
internal fun OrderComponent(modifier: Modifier = Modifier, viewModel: OrderViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()

    Content(state = state.value, modifier = modifier)
}

@Composable
private fun OrderItem(item: OrderUiState.Item) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Row {
            Text(text = stringResource(id = R.string.quantity, item.quantity))
            Text(text = item.name)
        }
        Text(text = item.totalPriceFormatted)
    }
}

@Composable
private fun TotalItemComponent(label: String, value: String) {
    Row {
        Text(text = label)
        Text(text = value)
    }
}

@Composable
private fun TotalsComponent(order: OrderUiState) {
    Column {
        TotalItemComponent(label = "SubTotal", value = order.subtotal)
        TotalItemComponent(label = "Discounts", value = order.discounts)
        TotalItemComponent(label = "Tax", value = order.tax)
        TotalItemComponent(label = "Total", value = order.total)
    }
}

@Composable
private fun LoadedContent(order: OrderUiState, modifier: Modifier) {
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
        LazyColumn {
            items(order.items) { item ->
                OrderItem(item = item)
            }
        }

        TotalsComponent(order = order)
    }
}

@Composable
private fun Content(state: OrderState, modifier: Modifier) {
    when (state) {
        is OrderState.Loaded -> LoadedContent(order = state.content, modifier = modifier)
        else -> Loading(modifier = modifier)
    }
}