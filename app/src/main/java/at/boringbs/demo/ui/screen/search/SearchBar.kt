package at.boringbs.demo.ui.screen.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import at.boringbs.demo.ui.theme.Purple80

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    isLoading: Boolean,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 18.dp)
            .clip(shape = RoundedCornerShape(56.dp))
            .background(color = Purple80)

    ) {
        Spacer(modifier = Modifier.width(4.dp))
        TextField(
            modifier = Modifier.weight(1f),
            leadingIcon = {
                AnimatedVisibility(
                    visible = isLoading,
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                }
                AnimatedVisibility(
                    visible = !isLoading,
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    Icon(Icons.Rounded.Search, contentDescription = "")
                }
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = value.isNotEmpty(),
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    Icon(
                        modifier = Modifier.clickable(onClick = onClear),
                        imageVector = Icons.Rounded.Close,
                        contentDescription = ""
                    )
                }
            },
            placeholder = { Text(text = "Enter user name") },
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Purple80,
                unfocusedContainerColor = Purple80,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}
