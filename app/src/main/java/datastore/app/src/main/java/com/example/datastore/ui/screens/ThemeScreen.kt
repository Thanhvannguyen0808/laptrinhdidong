package com.example.datastore.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.datastore.viewmodel.ThemeViewModel

@Composable
fun ThemeScreen(
    viewModel: ThemeViewModel,
    onApplyClick: () -> Unit
) {

    val theme by viewModel.theme.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()

            .padding(horizontal = 32.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Settings",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )


        Spacer(Modifier.height(48.dp))

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Selector("LIGHT", theme, Modifier.weight(1f)) { viewModel.saveTheme("LIGHT") }
            Spacer(Modifier.width(12.dp))
            Selector("DARK", theme, Modifier.weight(1f)) { viewModel.saveTheme("DARK") }
            Spacer(Modifier.width(12.dp))
            Selector("PINK", theme, Modifier.weight(1f)) { viewModel.saveTheme("PINK") }
            Spacer(Modifier.width(12.dp))
            Selector("BLUE", theme, Modifier.weight(1f)) { viewModel.saveTheme("BLUE") }
        }


        Spacer(Modifier.height(64.dp))

        Button(
            onClick = onApplyClick,

            modifier = Modifier.width(200.dp).height(50.dp),

            contentPadding = PaddingValues(12.dp)
        ) {
            Text("Apply", style = MaterialTheme.typography.titleMedium)
        }
    }
}


@Composable
fun Selector(text: String, current: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val isSelected = text == current
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp),

        colors = if (isSelected)

            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        else

            ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),

        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text,

            style = MaterialTheme.typography.labelLarge,

            maxLines = 1
        )
    }
}