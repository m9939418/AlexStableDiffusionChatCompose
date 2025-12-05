package com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.yang.stablediffusionchatcompose.ui.theme.AlexStableDiffusionChatComposeTheme

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NikiAskBar(
    modifier: Modifier = Modifier,
    placeholder: String = "馬上問 Niki 生成趣味圖..",
    enabled: Boolean = true,
    sending: Boolean,
    onSend: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val textState: TextFieldState = rememberTextFieldState()

    val text = textState.text.toString()

    fun clearText() {
        textState.edit {
            replace(0, length, "")
        }
    }

    fun trySend() {
        val trimmed = text.trim()
        if (!enabled || sending || trimmed.isEmpty()) return
        onSend(trimmed)
        clearText()
        keyboardController?.hide()
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                state = textState,
                modifier = Modifier
                    .weight(1f)
                    .onPreviewKeyEvent { event ->
                        if (event.type == KeyEventType.KeyUp && event.key == Key.Enter) {
                            trySend()
                            true
                        } else {
                            false
                        }
                    },
                textStyle = MaterialTheme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                decorator = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                        innerTextField()
                    }
                },
            )

            Spacer(Modifier.width(8.dp))

            FloatingActionButton(
                modifier = Modifier.size(44.dp),
                containerColor = MaterialTheme.colorScheme.background,
                shape = CircleShape,
                onClick = { trySend() },
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode",
)
@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
)
@Composable
fun NikiAskBarPreview() {
    AlexStableDiffusionChatComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 60.dp),
        ) {
            NikiAskBar(
                enabled = true,
                sending = false,
                onSend = { },
            )
        }
    }
}