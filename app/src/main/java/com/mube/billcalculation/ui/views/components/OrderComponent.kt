package com.mube.billcalculation.ui.views.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mube.billcalculation.R
import com.mube.billcalculation.ui.models.OrderUiState
import com.mube.billcalculation.ui.viewmodels.OrderEvent
import com.mube.billcalculation.ui.viewmodels.OrderState
import com.mube.billcalculation.ui.viewmodels.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OrderComponent(modifier: Modifier = Modifier, viewModel: OrderViewModel = hiltViewModel()) {
    val sheetState = rememberModalBottomSheetState()
    var showDiscountBottomSheet by remember { mutableStateOf(false) }

    val state = viewModel.state.collectAsState()

    Content(
        state = state.value,
        onDiscountClick = { showDiscountBottomSheet = true },
        onEvent = { viewModel.eventHandler(it) },
        modifier = modifier
    )

    if (showDiscountBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showDiscountBottomSheet = false
            },
            sheetState = sheetState
        ) {
            DiscountComponent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun LoadedContent(order: OrderUiState, onDiscountClick: () -> Unit, onEvent: (OrderEvent) -> Unit, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxSize()) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(order.items) { item ->
                val dismissState = rememberSwipeToDismissBoxState(
                    initialValue = SwipeToDismissBoxValue.Settled,
                    confirmValueChange = { dismissValue ->
                        if (dismissValue == SwipeToDismissBoxValue.StartToEnd || dismissValue == SwipeToDismissBoxValue.EndToStart) {
                            onEvent(OrderEvent.Delete(item.productId))

                        }
                        true
                    },
                    positionalThreshold = { swipeActivationFloat -> swipeActivationFloat / 3 }
                )
                SwipeToDismissBox(
                    modifier = Modifier.animateItemPlacement(),
                    state = dismissState,
                    backgroundContent = {
                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                SwipeToDismissBoxValue.StartToEnd -> Color.Red
                                SwipeToDismissBoxValue.EndToStart -> Color.Red
                                else -> Color.Transparent
                            }, label = ""
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color),
                            contentAlignment = Alignment.Center
                        ) {
                            Row {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Delete item",
                                    modifier = Modifier.padding(16.dp),
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    content = {
                        OrderItem(item = item)
                    }
                )
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

        Column(horizontalAlignment = Alignment.End) {
            Button(onClick = onDiscountClick, modifier = Modifier.padding(8.dp)) {
                Text(text = stringResource(id = R.string.discounts))
            }

            TotalsComponent(order = order)

            Button(
                onClick = { onEvent(OrderEvent.Finalize) }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.finalize_order))
            }
        }
    }
}

@Composable
private fun Content(state: OrderState, onDiscountClick: () -> Unit, onEvent: (OrderEvent) -> Unit, modifier: Modifier = Modifier) {
    when (state) {
        is OrderState.Loaded -> LoadedContent(order = state.content, onDiscountClick = onDiscountClick, onEvent = onEvent, modifier = modifier)
        else -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Loading(modifier = modifier)
            }
        }
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
        ),
        onDiscountClick = {},
        onEvent = {}
    )
}