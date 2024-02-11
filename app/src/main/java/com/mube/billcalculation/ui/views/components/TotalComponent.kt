package com.mube.billcalculation.ui.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mube.billcalculation.R
import com.mube.billcalculation.ui.models.OrderUiState

@Composable
fun TotalsComponent(order: OrderUiState) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        TotalItemComponent(label = stringResource(id = R.string.subtotal), value = order.subtotal)
        TotalItemComponent(label = stringResource(id = R.string.discounts), value = order.discounts)
        TotalItemComponent(label = stringResource(id = R.string.tax), value = order.tax)
        TotalItemComponent(label = stringResource(id = R.string.total), value = order.total)
    }
}

@Composable
private fun TotalItemComponent(label: String, value: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = value, fontSize = 16.sp)
    }
}