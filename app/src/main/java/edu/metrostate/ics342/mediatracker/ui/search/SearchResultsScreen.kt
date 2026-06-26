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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.model.Media
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
fun SearchResultsScreen(
    initialQuery: String,
    onBack: () -> Unit,
    onMediaClick: (Int) -> Unit,
    viewModel: SearchResultsViewModel = viewModel()
) {
    var searchBarQuery by remember { mutableStateOf(initialQuery) }
    val results by viewModel.results.collectAsState()
    val selectedType by viewModel.selectedType.collectAsState()

    LaunchedEffect(initialQuery) {
        viewModel.search(initialQuery)
    }


    //var selectedStatus by rememberSaveable { mutableStateOf(LibraryStatus.WANT_TO) }
    //var selectedType   by rememberSaveable { mutableStateOf("all") }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier
            .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.action_back)
                )
            }
            OutlinedTextField(
                value = searchBarQuery,
                onValueChange = { },//{ displayName = it },
                singleLine = true,
                placeholder = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.search_box_hint)) },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.search(searchBarQuery)
                    }
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(40.dp)
            )
        }



        MediaTypeFilterChips(
            selectedType = selectedType,
            onTypeSelect = viewModel::onTypeSelect,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Text(
            text = stringResource(R.string.search_results_count, results.size),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(results, key = { it.id }) { item ->
                MediaResultCard(
                    media           = item,
                    onClick = {}

                )
            }
        }
    }
}
/*
@Composable
private fun MediaResultCard(
    item: Media
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var statusDialogVisible by remember { mutableStateOf(false) }


    Card(
        modifier  = Modifier
            .fillMaxWidth(),
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
                if (item.coverUrl != null) {
                    AsyncImage(
                        model             = item.coverUrl,
                        contentDescription = item.title,
                        contentScale      = ContentScale.Crop,
                        modifier          = Modifier.fillMaxSize()
                    )
                } else {
                    Surface(color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxSize()) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(when (item.mediaType) {
                                "book" -> "📖"; "movie" -> "🎬"; "show" -> "📺"
                                else -> "?"
                            }, style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.title, style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold, maxLines = 2)
                Spacer(Modifier.height(2.dp))
                Text(item.creatorCredit(LocalContext.current),
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
}*/

/*@Preview
@Composable
fun searchResultsScreenPreview() {
    SearchResultsScreen(
        onSearch = {},
        onMediaClick = {},

        )
}*/

val fakeSearchResults: List<Media> = List(60) { listOf(
    // Books
    Media(id = 1,  mediaType = "book",  title = "Dune",                                      author = "Frank Herbert",           publishedYear = 1965, averageRating = 4.8f, ratingCount = 1847, genres = listOf("Science Fiction", "Epic")),
    Media(id = 2,  mediaType = "book",  title = "Foundation",                                 author = "Isaac Asimov",            publishedYear = 1951, averageRating = 4.7f, ratingCount = 1623, genres = listOf("Science Fiction")),
    Media(id = 3,  mediaType = "book",  title = "The Hitchhiker's Guide to the Galaxy",       author = "Douglas Adams",           publishedYear = 1979, averageRating = 4.7f, ratingCount = 2104, genres = listOf("Science Fiction", "Comedy")),
    Media(id = 4,  mediaType = "book",  title = "Project Hail Mary",                          author = "Andy Weir",               publishedYear = 2021, averageRating = 4.9f, ratingCount = 1388, genres = listOf("Science Fiction", "Adventure")),
    Media(id = 5,  mediaType = "book",  title = "The Name of the Wind",                       author = "Patrick Rothfuss",        publishedYear = 2007, averageRating = 4.6f, ratingCount = 1201, genres = listOf("Fantasy", "Adventure")),
    Media(id = 6,  mediaType = "book",  title = "The Way of Kings",                           author = "Brandon Sanderson",       publishedYear = 2010, averageRating = 4.8f, ratingCount = 1743, genres = listOf("Fantasy", "Epic")),
    Media(id = 7,  mediaType = "book",  title = "Ender's Game",                               author = "Orson Scott Card",        publishedYear = 1985, averageRating = 4.6f, ratingCount = 1956, genres = listOf("Science Fiction", "Adventure")),
    Media(id = 8,  mediaType = "book",  title = "The Martian",                                author = "Andy Weir",               publishedYear = 2011, averageRating = 4.5f, ratingCount = 1412, genres = listOf("Science Fiction")),
    Media(id = 9,  mediaType = "book",  title = "Snow Crash",                                 author = "Neal Stephenson",         publishedYear = 1992, averageRating = 4.3f, ratingCount = 987,  genres = listOf("Science Fiction", "Cyberpunk")),
    Media(id = 10, mediaType = "book",  title = "Neuromancer",                                author = "William Gibson",          publishedYear = 1984, averageRating = 4.2f, ratingCount = 876,  genres = listOf("Science Fiction", "Cyberpunk")),
    Media(id = 11, mediaType = "book",  title = "Leviathan Wakes",                            author = "James S.A. Corey",        publishedYear = 2011, averageRating = 4.5f, ratingCount = 1134, genres = listOf("Science Fiction", "Space Opera")),
    Media(id = 12, mediaType = "book",  title = "Children of Time",                           author = "Adrian Tchaikovsky",      publishedYear = 2015, averageRating = 4.6f, ratingCount = 892,  genres = listOf("Science Fiction")),
    Media(id = 13, mediaType = "book",  title = "Recursion",                                  author = "Blake Crouch",            publishedYear = 2019, averageRating = 4.4f, ratingCount = 1023, genres = listOf("Science Fiction", "Thriller")),
    Media(id = 14, mediaType = "book",  title = "Dark Matter",                                author = "Blake Crouch",            publishedYear = 2016, averageRating = 4.3f, ratingCount = 1089, genres = listOf("Science Fiction", "Thriller")),
    Media(id = 15, mediaType = "book",  title = "All Systems Red",                            author = "Martha Wells",            publishedYear = 2017, averageRating = 4.5f, ratingCount = 743,  genres = listOf("Science Fiction")),
    Media(id = 16, mediaType = "book",  title = "A Long Way to a Small Angry Planet",         author = "Becky Chambers",          publishedYear = 2014, averageRating = 4.4f, ratingCount = 812,  genres = listOf("Science Fiction", "Adventure")),
    Media(id = 17, mediaType = "book",  title = "The Left Hand of Darkness",                  author = "Ursula K. Le Guin",       publishedYear = 1969, averageRating = 4.4f, ratingCount = 934,  genres = listOf("Science Fiction")),
    Media(id = 18, mediaType = "book",  title = "Hyperion",                                   author = "Dan Simmons",             publishedYear = 1989, averageRating = 4.6f, ratingCount = 1298, genres = listOf("Science Fiction", "Space Opera")),
    Media(id = 19, mediaType = "book",  title = "Old Man's War",                              author = "John Scalzi",             publishedYear = 2005, averageRating = 4.3f, ratingCount = 781,  genres = listOf("Science Fiction", "Military")),
    Media(id = 20, mediaType = "book",  title = "Never Let Me Go",                            author = "Kazuo Ishiguro",          publishedYear = 2005, averageRating = 4.1f, ratingCount = 678,  genres = listOf("Science Fiction", "Literary Fiction")),

    // Movies
    Media(id = 21, mediaType = "movie", title = "Arrival",                                    director = "Denis Villeneuve",      publishedYear = 2016, averageRating = 4.5f, ratingCount = 1534, genres = listOf("Science Fiction", "Drama")),
    Media(id = 22, mediaType = "movie", title = "Interstellar",                               director = "Christopher Nolan",     publishedYear = 2014, averageRating = 4.6f, ratingCount = 2341, genres = listOf("Science Fiction", "Adventure")),
    Media(id = 23, mediaType = "movie", title = "Everything Everywhere All at Once",          director = "Daniel Kwan",           publishedYear = 2022, averageRating = 4.8f, ratingCount = 1876, genres = listOf("Science Fiction", "Comedy", "Drama")),
    Media(id = 24, mediaType = "movie", title = "Blade Runner 2049",                          director = "Denis Villeneuve",      publishedYear = 2017, averageRating = 4.4f, ratingCount = 1432, genres = listOf("Science Fiction", "Neo-noir")),
    Media(id = 25, mediaType = "movie", title = "Inception",                                  director = "Christopher Nolan",     publishedYear = 2010, averageRating = 4.7f, ratingCount = 2789, genres = listOf("Science Fiction", "Action")),
    Media(id = 26, mediaType = "movie", title = "The Matrix",                                 director = "Lana Wachowski",        publishedYear = 1999, averageRating = 4.7f, ratingCount = 3012, genres = listOf("Science Fiction", "Action")),
    Media(id = 27, mediaType = "movie", title = "Moon",                                       director = "Duncan Jones",          publishedYear = 2009, averageRating = 4.3f, ratingCount = 678,  genres = listOf("Science Fiction", "Drama")),
    Media(id = 28, mediaType = "movie", title = "Ex Machina",                                 director = "Alex Garland",          publishedYear = 2014, averageRating = 4.4f, ratingCount = 1123, genres = listOf("Science Fiction", "Thriller")),
    Media(id = 29, mediaType = "movie", title = "Annihilation",                               director = "Alex Garland",          publishedYear = 2018, averageRating = 4.2f, ratingCount = 934,  genres = listOf("Science Fiction", "Horror")),
    Media(id = 30, mediaType = "movie", title = "Contact",                                    director = "Robert Zemeckis",       publishedYear = 1997, averageRating = 4.4f, ratingCount = 1045, genres = listOf("Science Fiction", "Drama")),
    Media(id = 31, mediaType = "movie", title = "Eternal Sunshine of the Spotless Mind",      director = "Michel Gondry",         publishedYear = 2004, averageRating = 4.5f, ratingCount = 1567, genres = listOf("Science Fiction", "Romance", "Drama")),
    Media(id = 32, mediaType = "movie", title = "Primer",                                     director = "Shane Carruth",         publishedYear = 2004, averageRating = 4.1f, ratingCount = 543,  genres = listOf("Science Fiction", "Thriller")),
    Media(id = 33, mediaType = "movie", title = "2001: A Space Odyssey",                      director = "Stanley Kubrick",       publishedYear = 1968, averageRating = 4.5f, ratingCount = 1789, genres = listOf("Science Fiction")),
    Media(id = 34, mediaType = "movie", title = "Her",                                        director = "Spike Jonze",           publishedYear = 2013, averageRating = 4.4f, ratingCount = 1234, genres = listOf("Science Fiction", "Romance", "Drama")),
    Media(id = 35, mediaType = "movie", title = "District 9",                                 director = "Neill Blomkamp",        publishedYear = 2009, averageRating = 4.2f, ratingCount = 987,  genres = listOf("Science Fiction", "Action")),
    Media(id = 36, mediaType = "movie", title = "The Prestige",                               director = "Christopher Nolan",     publishedYear = 2006, averageRating = 4.5f, ratingCount = 1678, genres = listOf("Mystery", "Thriller")),
    Media(id = 37, mediaType = "movie", title = "Coherence",                                  director = "James Ward Byrkit",     publishedYear = 2013, averageRating = 4.1f, ratingCount = 421,  genres = listOf("Science Fiction", "Thriller")),
    Media(id = 38, mediaType = "movie", title = "About Time",                                 director = "Richard Curtis",        publishedYear = 2013, averageRating = 4.3f, ratingCount = 876,  genres = listOf("Romance", "Science Fiction", "Drama")),
    Media(id = 39, mediaType = "movie", title = "Predestination",                             director = "Michael Spierig",       publishedYear = 2014, averageRating = 4.2f, ratingCount = 612,  genres = listOf("Science Fiction", "Thriller")),
    Media(id = 40, mediaType = "movie", title = "Looper",                                     director = "Rian Johnson",          publishedYear = 2012, averageRating = 4.0f, ratingCount = 934,  genres = listOf("Science Fiction", "Action", "Thriller")),

    // Shows
    Media(id = 41, mediaType = "show",  title = "Severance",              creator = "Dan Erickson",          network = "Apple TV+",  publishedYear = 2022, averageRating = 4.9f, ratingCount = 1432, genres = listOf("Thriller", "Science Fiction", "Drama")),
    Media(id = 42, mediaType = "show",  title = "The Bear",               creator = "Christopher Storer",    network = "FX on Hulu", publishedYear = 2022, averageRating = 4.8f, ratingCount = 1287, genres = listOf("Drama", "Comedy")),
    Media(id = 43, mediaType = "show",  title = "Andor",                  creator = "Tony Gilroy",           network = "Disney+",    publishedYear = 2022, averageRating = 4.7f, ratingCount = 1098, genres = listOf("Science Fiction", "Drama", "Action")),
    Media(id = 44, mediaType = "show",  title = "Dark",                   creator = "Baran bo Odar",         network = "Netflix",    publishedYear = 2017, averageRating = 4.8f, ratingCount = 1563, genres = listOf("Science Fiction", "Thriller", "Mystery")),
    Media(id = 45, mediaType = "show",  title = "Halt and Catch Fire",    creator = "Christopher Cantwell",  network = "AMC",        publishedYear = 2014, averageRating = 4.6f, ratingCount = 743,  genres = listOf("Drama")),
    Media(id = 46, mediaType = "show",  title = "Better Call Saul",       creator = "Vince Gilligan",        network = "AMC",        publishedYear = 2015, averageRating = 4.8f, ratingCount = 1876, genres = listOf("Crime", "Drama", "Thriller")),
    Media(id = 47, mediaType = "show",  title = "The Expanse",            creator = "Mark Fergus",           network = "Amazon",     publishedYear = 2015, averageRating = 4.7f, ratingCount = 1234, genres = listOf("Science Fiction", "Space Opera")),
    Media(id = 48, mediaType = "show",  title = "Succession",             creator = "Jesse Armstrong",       network = "HBO",        publishedYear = 2018, averageRating = 4.8f, ratingCount = 1567, genres = listOf("Drama", "Comedy")),
    Media(id = 49, mediaType = "show",  title = "Station Eleven",         creator = "Patrick Somerville",    network = "HBO Max",    publishedYear = 2021, averageRating = 4.5f, ratingCount = 678,  genres = listOf("Science Fiction", "Drama")),
    Media(id = 50, mediaType = "show",  title = "The Leftovers",          creator = "Damon Lindelof",        network = "HBO",        publishedYear = 2014, averageRating = 4.6f, ratingCount = 812,  genres = listOf("Drama", "Mystery")),
    Media(id = 51, mediaType = "show",  title = "Watchmen",               creator = "Damon Lindelof",        network = "HBO",        publishedYear = 2019, averageRating = 4.7f, ratingCount = 934,  genres = listOf("Superhero", "Drama", "Mystery")),
    Media(id = 52, mediaType = "show",  title = "Black Mirror",           creator = "Charlie Brooker",       network = "Netflix",    publishedYear = 2011, averageRating = 4.4f, ratingCount = 2103, genres = listOf("Science Fiction", "Anthology", "Thriller")),
    Media(id = 53, mediaType = "show",  title = "Westworld",              creator = "Jonathan Nolan",        network = "HBO",        publishedYear = 2016, averageRating = 4.3f, ratingCount = 1345, genres = listOf("Science Fiction", "Western", "Thriller")),
    Media(id = 54, mediaType = "show",  title = "Devs",                   creator = "Alex Garland",          network = "Hulu",       publishedYear = 2020, averageRating = 4.2f, ratingCount = 543,  genres = listOf("Science Fiction", "Thriller")),
    Media(id = 55, mediaType = "show",  title = "Maniac",                 creator = "Patrick Somerville",    network = "Netflix",    publishedYear = 2018, averageRating = 4.0f, ratingCount = 432,  genres = listOf("Science Fiction", "Comedy", "Drama")),
    Media(id = 56, mediaType = "show",  title = "The OA",                 creator = "Brit Marling",          network = "Netflix",    publishedYear = 2016, averageRating = 4.3f, ratingCount = 678,  genres = listOf("Science Fiction", "Mystery", "Drama")),
    Media(id = 57, mediaType = "show",  title = "Twin Peaks: The Return", creator = "David Lynch",           network = "Showtime",   publishedYear = 2017, averageRating = 4.5f, ratingCount = 789,  genres = listOf("Mystery", "Horror", "Drama")),
    Media(id = 58, mediaType = "show",  title = "Battlestar Galactica",   creator = "Ronald D. Moore",       network = "Syfy",       publishedYear = 2004, averageRating = 4.6f, ratingCount = 1123, genres = listOf("Science Fiction", "Drama")),
    Media(id = 59, mediaType = "show",  title = "Fringe",                 creator = "J.J. Abrams",           network = "Fox",        publishedYear = 2008, averageRating = 4.4f, ratingCount = 876,  genres = listOf("Science Fiction", "Thriller")),
    Media(id = 60, mediaType = "show",  title = "Mindhunter",             creator = "Joe Penhall",           network = "Netflix",    publishedYear = 2017, averageRating = 4.6f, ratingCount = 1034, genres = listOf("Crime", "Thriller", "Drama")),
) }.flatten()