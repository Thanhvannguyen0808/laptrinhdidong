package com.example.homeworkt4_bai2.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp

@Composable
fun VerificationCodeRow(length: Int = 6, onCodeComplete: (String) -> Unit) {
    val digits = remember { List(length) { mutableStateOf("") } }
    val req = List(length) { FocusRequester() }

    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 0 until length) {
            OutlinedTextField(
                value = digits[i].value,
                onValueChange = { v ->
                    if (v.length <= 1) {
                        digits[i].value = v
                        if (v.isNotEmpty() && i < length - 1) req[i + 1].requestFocus()
                        val current = digits.joinToString("") { it.value }
                        if (current.length == length) onCodeComplete(current)
                    }
                },
                modifier = Modifier
                    .size(52.dp)
                    .focusRequester(req[i]),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}
