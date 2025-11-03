package com.example.homeworkt4_bai2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeworkt4_bai2.R
import com.example.homeworkt4_bai2.ui.component.GradientButton

@Composable
fun VerifyCodeScreen(navController: NavController, email: String) {
    var code by remember { mutableStateOf(List(6) { "" }) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(Modifier.height(40.dp))
        Image(painter = painterResource(id = R.drawable.logo_uth), contentDescription = null, modifier = Modifier.size(96.dp))
        Spacer(Modifier.height(12.dp))
        Text("SmartTasks", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(18.dp))
        Text("Verify Code", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text("Enter the code we just sent you on your registered Email", style = MaterialTheme.typography.bodySmall, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            code.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                        if (it.length <= 1) code = code.toMutableList().also { list -> list[index] = it }
                    },
                    singleLine = true,
                    modifier = Modifier.width(48.dp)
                )
            }
        }

        Spacer(Modifier.height(22.dp))
        GradientButton("Next", modifier = Modifier.fillMaxWidth()) {
            if (code.joinToString("").length == 6) {
                navController.navigate("reset/$email/${code.joinToString("")}")
            } else {
                println("Code chưa đủ 6 số")
            }
        }
    }
}
