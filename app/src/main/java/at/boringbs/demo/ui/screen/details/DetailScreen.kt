package at.boringbs.demo.ui.screen.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import at.boringbs.demo.viewmodel.detail.DetailScreenState
import coil.compose.AsyncImage

@Composable
fun DetailScreen(
    state: DetailScreenState,
    login: String,
    onBack: () -> Unit
) {
    BackHandler(true) { onBack() }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        when(state) {
            is DetailScreenState.Data -> {
                val data = state.user
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 24.dp,
                                vertical = 56.dp
                            ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            AsyncImage(
                                model = data.icon,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            data.login?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }
                            data.name?.let {
                                Text(
                                    text = "($it)",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            data.bio?.let {
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Justify
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Company: ${data.company?.let { it } ?: "NONE"}",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Justify
                            )
                            Text(
                                text = "Hireable: ${data.hireable?.let { it } ?: "UNKNOWN"}",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
            is DetailScreenState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 64.dp, horizontal = 32.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = state.message,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            DetailScreenState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 64.dp, horizontal = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = onBack),
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = ""
            )
            Text(
                text = login,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f).padding(end = 40.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}