package com.mube.billcalculation.ui.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mube.billcalculation.ui.models.MenuUiState
import com.mube.billcalculation.ui.viewmodels.MenuEvent
import com.mube.billcalculation.ui.viewmodels.MenuState
import com.mube.billcalculation.ui.viewmodels.MenuViewModel
import com.mube.products.domain.models.ProductId

@Composable
internal fun MenuComponent(
    modifier: Modifier = Modifier,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Content(
        onItemClick = {
            viewModel.eventHandler(MenuEvent.ProductClick(it))
        },
        menuState = state.value,
        modifier = modifier
    )
}

@Composable
private fun CategoryAndProduct(
    item: MenuUiState.Item,
    onItemClick: (ProductId) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = item.categoryName, modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
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
    Column (modifier = modifier){
        items.map {
            CategoryAndProduct(
                item = it,
                onItemClick = onItemClick,
            )
        }
    }

}

@Composable
private fun Content(
    onItemClick: (ProductId) -> Unit,
    menuState: MenuState,
    modifier: Modifier
) {
    when (menuState) {
        is MenuState.Loaded -> MenuContent(items = menuState.content.items, onItemClick = onItemClick, modifier = modifier)
        else -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Loading(modifier = modifier)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMenuComponent() {

    val items = listOf(
        MenuUiState.Item(
            categoryName = "Drinks",
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
        ),
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
        ),
        MenuUiState.Item(
            categoryName = "Food",
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