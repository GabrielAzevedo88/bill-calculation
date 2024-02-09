package com.mube.billcalculation.ui.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mube.billcalculation.ui.models.MenuUiState
import com.mube.billcalculation.ui.viewmodels.MenuState
import com.mube.billcalculation.ui.viewmodels.MenuViewModel
import com.mube.products.domain.models.ProductId

@Composable
internal fun MenuComponent(
    onItemClick: (ProductId) -> Unit,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Content(onItemClick = onItemClick, menuState = state.value)
}

@Composable
private fun CategoryAndProduct(
    item: MenuUiState.Item,
    onItemClick: (ProductId) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = item.categoryName, modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth()
        )

        LazyColumn {
            items(item.products) { product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            onItemClick(product.id)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = product.name)
                    Text(text = product.formattedPrice)
                }
            }
        }
    }

}

@Composable
private fun MenuContent(
    items: List<MenuUiState.Item>,
    onItemClick: (ProductId) -> Unit,
    modifier: Modifier = Modifier
) {
    items.map {
        CategoryAndProduct(
            item = it,
            onItemClick = onItemClick,
            modifier = modifier
        )
    }
}

@Composable
private fun Content(
    onItemClick: (ProductId) -> Unit,
    menuState: MenuState
) {
    when (menuState) {
        is MenuState.Loaded -> MenuContent(items = menuState.content.items, onItemClick = onItemClick)
        is MenuState.Loading -> Loading()
    }
}

@Preview
@Composable
private fun PreviewMenuComponent() {

    val items = listOf(
        MenuUiState.Item(
            categoryName = "Appetizers",
            products = listOf(
                MenuUiState.Product(
                    id = 1,
                    name = "Nachos",
                    formattedPrice = "$13.99"
                ),
                MenuUiState.Product(
                    id = 1,
                    name = "Calamari",
                    formattedPrice = "$11.99"
                ),
                MenuUiState.Product(
                    id = 1,
                    name = "Caesar Salad",
                    formattedPrice = "$10.99"
                )
            )
        )
    )

    MenuContent(items = items, onItemClick = {})

}