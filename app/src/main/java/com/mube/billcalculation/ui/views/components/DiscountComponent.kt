package com.mube.billcalculation.ui.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mube.billcalculation.R
import com.mube.billcalculation.ui.models.DiscountUiState
import com.mube.billcalculation.ui.viewmodels.DiscountState
import com.mube.billcalculation.ui.viewmodels.DiscountsEvent
import com.mube.billcalculation.ui.viewmodels.DiscountsViewModel

@Composable
fun DiscountComponent(viewModel: DiscountsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()

    Content(state = state.value, onEvent = {
        viewModel.eventsHandler(it)
    })

}

@Composable
private fun DiscountItems(items: List<DiscountUiState>, onEvent: (DiscountsEvent) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(items) { discount ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().clickable {
                    onEvent(DiscountsEvent.OnClick(discount.discountId))
                }
            ) {
                Text(text = discount.name)
                Checkbox(checked = discount.isSelected, onCheckedChange = { onEvent(DiscountsEvent.OnClick(discount.discountId)) })
            }
        }
    }
}

@Composable
private fun LoadedContent(items: List<DiscountUiState>, onEvent: (DiscountsEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
        DiscountItems(items = items, onEvent = onEvent)
        Button(
            onClick = { onEvent(DiscountsEvent.OnClear) }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp)
        ) {
            Text(text = stringResource(id = R.string.clear))
        }
    }
}

@Composable
private fun Content(state: DiscountState, onEvent: (DiscountsEvent) -> Unit, modifier: Modifier = Modifier) {
    when (state) {
        is DiscountState.Loaded -> LoadedContent(items = state.content, onEvent = onEvent)
        else -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Loading(modifier = modifier)
            }
        }
    }
}

@Preview
@Composable
private fun DiscountComponentPreview() {
    LoadedContent(
        items = listOf(
            DiscountUiState(
                discountId = 100,
                name = "$5.00",
                isSelected = false
            ),
            DiscountUiState(
                discountId = 200,
                name = "10%",
                isSelected = true
            ),
            DiscountUiState(
                discountId = 300,
                name = "20%",
                isSelected = false
            )
        ), onEvent = { })
}
