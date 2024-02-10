package com.mube.billcalculation.ui.views.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mube.billcalculation.R
import com.mube.billcalculation.ui.views.components.MenuComponent
import com.mube.billcalculation.ui.views.components.OrderComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillCalculationActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            Text(text = stringResource(id = R.string.app_name))
                        }
                    )
                }
            ) { innerPadding ->
                Row(
                    modifier = Modifier
                        .padding(innerPadding),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    MenuComponent(modifier = Modifier.weight(1f))
                    OrderComponent(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}