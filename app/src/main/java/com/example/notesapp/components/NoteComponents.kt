package com.example.notesapp.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label : String,
    maxLine : Int = 1,
    imeAction: ImeAction,
    onTextChange :(String)->Unit,
    onImeAction: () -> Unit = {}
){
    val keyBordController = LocalSoftwareKeyboardController.current

    TextField(value = text,
        onValueChange = onTextChange,
        maxLines = maxLine,
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyBordController?.hide()
            }),
        modifier = modifier
        )

}

@Composable
fun NoteButton(
    modifier:Modifier = Modifier,
    text : String,
    onClick: () -> Unit,
    enabled:Boolean = true
){
    Button(onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        modifier = modifier) {
        Text(text = text)
    }
    
}
