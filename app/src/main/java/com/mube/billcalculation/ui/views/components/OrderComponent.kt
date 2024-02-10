package com.mube.billcalculation.ui.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mube.billcalculation.R
import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.ui.models.OrderUiState
import com.mube.billcalculation.ui.viewmodels.OrderState
import com.mube.billcalculation.ui.viewmodels.OrderViewModel
import com.mube.billcalculation.utils.toCurrency

@Composable
internal fun OrderComponent(modifier: Modifier = Modifier, viewModel: OrderViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()

    Content(state = state.value, modifier = modifier)
}

@Composable
private fun OrderItem(item: OrderUiState.Item) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Row {
            Text(text = stringResource(id = R.string.quantity, item.quantity))
            Text(text = item.name)
        }
        Text(text = item.totalPriceFormatted)
    }
}

@Composable
private fun TotalItemComponent(label: String, value: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}

@Composable
private fun TotalsComponent(order: OrderUiState) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        TotalItemComponent(label = stringResource(id = R.string.subtotal), value = order.subtotal)
        TotalItemComponent(label = stringResource(id = R.string.discounts), value = order.discounts)
        TotalItemComponent(label = stringResource(id = R.string.tax), value = order.tax)
        TotalItemComponent(label = stringResource(id = R.string.total), value = order.total)
    }
}

@Composable
private fun LoadedContent(order: OrderUiState, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(order.items) { item ->
                OrderItem(item = item)
            }
        }

        if (order.items.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.empty_order),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(34.dp)
                )
            }
        }

        TotalsComponent(order = order)
    }
}

@Composable
private fun Content(state: OrderState, modifier: Modifier = Modifier) {
    when (state) {
        is OrderState.Loaded -> LoadedContent(order = state.content, modifier = modifier)
        else -> Loading(modifier = modifier)
    }
}

@Preview
@Composable
private fun OrderComponentPreview() {
    LoadedContent(
        order = OrderUiState(
            items = listOf(
                OrderUiState.Item(
                    productId = 100,
                    name = "Cider",
                    quantity = 1,
                    totalPriceFormatted = "$6.00"
                ),
                OrderUiState.Item(
                    productId = 100,
                    name = "Hotdog",
                    quantity = 1,
                    totalPriceFormatted = "$3.99"
                ),
                OrderUiState.Item(
                    productId = 100,
                    name = "Orange Juice",
                    quantity = 1,
                    totalPriceFormatted = "$3.00"
                )
            ),
            subtotal = "$12.99",
            discounts = "1.43",
            tax = "1.32",
            total = "$12.88"
        )
    )
}