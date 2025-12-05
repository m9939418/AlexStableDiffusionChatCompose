package com.alex.yang.stablediffusionchatcompose.feature.chat.presentation.component

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.yang.stablediffusionchatcompose.ui.theme.AlexStableDiffusionChatComposeTheme

/**
 * Created by AlexYang on 2025/12/6.
 *
 *
 */
@Composable
fun BouncingDotsLoading(
    modifier: Modifier = Modifier,
    dotCount: Int = 4,
) {
    val durations = List(dotCount) { index ->
        600 + index * 90   // 每個點延遲 90ms
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(dotCount) { index ->
            val transition = rememberInfiniteTransition(label = "dots-$index")

            // 用 animateFloat 來做位移
            val offsetY by transition.animateFloat(
                initialValue = 0f,
                targetValue = -6f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = durations[index]
                        0f at 0
                        -6f at durations[index] / 4
                        0f at durations[index] / 2
                        0f at durations[index]
                    },
                    repeatMode = RepeatMode.Restart
                ),
                label = "dot-offset-$index"
            )

            Box(
                modifier = Modifier
                    .size(6.dp)
                    .offset(y = offsetY.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        shape = CircleShape
                    )
            )

            Spacer(Modifier.width(6.dp))
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun BouncingDotsLoadingPreview() {
    AlexStableDiffusionChatComposeTheme {
        BouncingDotsLoading()
    }
}