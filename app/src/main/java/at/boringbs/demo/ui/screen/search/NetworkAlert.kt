package at.boringbs.demo.ui.screen.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NetworkAlert(
    modifier: Modifier = Modifier,
    networkOn: Boolean
) {
    AnimatedVisibility(
        visible = !networkOn,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(18.dp)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Internet Connection",
                style = MaterialTheme.typography.labelMedium.copy(color = Color.White)
            )
        }
    }
}