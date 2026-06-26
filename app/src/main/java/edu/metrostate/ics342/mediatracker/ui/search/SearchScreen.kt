package edu.metrostate.ics342.mediatracker.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import edu.metrostate.ics342.mediatracker.R
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.model.creatorCredit

// ── STUB — Students build this in Week 5 ─────────────────────────────────────
//
// Week 5 task: Build the Search screen.
//   1. Add a search bar (SearchBar or OutlinedTextField) at the top.
//   2. Add FilterChips for All / Books / Movies / Shows in a horizontally scrollable Row.
//   3. Display results in a LazyColumn (you'll learn why Column won't work here).
//   4. Wire to GET /media?query=...&type=...
//   5. Handle loading, empty, and error states.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onSearch: (String) -> Unit,
    onMediaClick: (Int) -> Unit,
    viewModel: SearchViewModel = viewModel()
) {
    val items     by viewModel.popularItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val tempField: String = ""
    val focusManager = LocalFocusManager.current

    val query by viewModel.query.collectAsState()
    val selectedType by viewModel.selectedType.collectAsState()

    val popularItems = FakeMediaRepository.mediaList.filter { media ->
        selectedType.isEmpty() || media.mediaType == selectedType
    }

    val selectedStatus by viewModel.filterState.collectAsState()
    //var selectedType by remember { mutableStateOf("all")}
    //var selectedStatus by rememberSaveable { mutableStateOf(LibraryStatus.WANT_TO) }
    //var selectedType   by rememberSaveable { mutableStateOf("all") }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.search_title)) })

        Row(modifier = Modifier
            .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(
                value = query,
                onValueChange = viewModel::onQueryChange,//{ displayName = it },
                singleLine = true,
                placeholder = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.search_box_hint)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    if (query.isNotBlank()) {
                        val q = query
                        viewModel.clearQuery()
                        onSearch(q)
                    }
                }),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(40.dp)
            )
        }
        MediaTypeFilterChips(
            selectedType = selectedType,
            onTypeSelect = viewModel::onTypeSelect,
            modifier = Modifier.padding(horizontal = 16.dp)
        )



        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Column
        }

        val filteredItems = items
            .filter { it.status == selectedStatus }
            .filter { selectedType == "all" || it.media.mediaType == selectedType }

        if (filteredItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(edu.metrostate.ics342.mediatracker.R.string.library_empty),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
            return@Column
        }

        Text(
            stringResource(edu.metrostate.ics342.mediatracker.R.string.search_popular_items_label),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style    = MaterialTheme.typography.labelMedium,
            color    = MaterialTheme.colorScheme.onSurfaceVariant
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredItems, key = { it.mediaId }) { item ->
                LibraryItemCard(
                    item           = item,
                    onClick        = { onMediaClick(item.mediaId) },
                    onRemove       = { viewModel.removeItem(item.mediaId) },
                    onStatusChange = { newStatus -> viewModel.updateStatus(item.mediaId, newStatus) }
                )
            }
        }
    }
}

@Composable
private fun LibraryItemCard(
    item: LibraryItem,
    onClick: () -> Unit,
    onRemove: () -> Unit,
    onStatusChange: (LibraryStatus) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var statusDialogVisible by remember { mutableStateOf(false) }

    if (statusDialogVisible) {
        AlertDialog(
            onDismissRequest = { statusDialogVisible = false },
            title = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.action_change_status)) },
            text = {
                Column {
                    LibraryStatus.values().forEach { s ->
                        TextButton(
                            onClick  = { onStatusChange(s); statusDialogVisible = false },
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(stringResource(s.labelRes)) }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { statusDialogVisible = false }) { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.settings_cancel_button)) }
            }
        )
    }

    Card(
        modifier  = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape     = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(64.dp, 90.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (item.media.coverUrl != null) {
                    AsyncImage(
                        model             = item.media.coverUrl,
                        contentDescription = item.media.title,
                        contentScale      = ContentScale.Crop,
                        modifier          = Modifier.fillMaxSize()
                    )
                } else {
                    Surface(color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxSize()) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(when (item.media.mediaType) {
                                "book" -> "📖"; "movie" -> "🎬"; "show" -> "📺"
                                else -> "?"
                            }, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.media.title, style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold, maxLines = 2)
                Spacer(Modifier.height(2.dp))
                Text(item.media.creatorCredit(LocalContext.current),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(6.dp))
                /*SuggestionChip(
                    onClick = { statusDialogVisible = true },
                    label   = { Text(stringResource(item.status.labelRes),
                        style = MaterialTheme.typography.labelSmall) }
                )*/
            }


        }
    }
}

@Preview
@Composable
fun searchScreenPreview() {
    SearchScreen(
        onSearch = {},
        onMediaClick = {},

    )
}