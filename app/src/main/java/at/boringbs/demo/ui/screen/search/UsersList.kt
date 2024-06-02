package at.boringbs.demo.ui.screen.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import at.boringbs.demo.domain.UsersListItemUiData
import kotlinx.collections.immutable.ImmutableList

@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    users: ImmutableList<UsersListItemUiData>,
    onClick: (Int) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(users.size) {
            val item = users[it]
            UsersListItem(
                icon = item.icon,
                text = item.login?:"Unknown",
                onClick = { onClick(it) }
            )
        }
    }
}