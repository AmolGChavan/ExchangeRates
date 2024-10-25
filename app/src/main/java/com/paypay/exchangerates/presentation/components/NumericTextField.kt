package com.paypay.exchangerates.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.paypay.exchangerates.presentation.Dimens

@Composable
fun NumericTextField(
    modifier: Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    onDoneChange: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = value,
        onValueChange = {
            if (it.isEmpty() || it.matches(Regex("^[0-9]*\\.?[0-9]*\$"))) {
                onValueChange(it)
            }
        },
        label = { Text(label) },
        placeholder = { Text("0.0") },
        textStyle = androidx.compose.ui.text.TextStyle(
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusRequester.freeFocus()
                onDoneChange()
            }
        ),
        modifier = modifier.focusRequester(focusRequester),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(Dimens.Dp5)
    )
}
