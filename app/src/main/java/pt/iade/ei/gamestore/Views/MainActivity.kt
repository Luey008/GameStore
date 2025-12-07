package pt.iade.ei.gamestore.Views

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.*
import androidx.activity.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme
import pt.iade.ei.gamestore.R
import pt.iade.ei.gamestore.Controllers.GameController
import pt.iade.ei.gamestore.Models.GameItem
import pt.iade.ei.gamestore.ui.Components.GameListItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val gamesList = mutableStateListOf<GameItem>()

        setContent {
            GameStoreTheme {

                GameController().GetListOfGames { list ->
                    gamesList.addAll(list)
                }
                MainView(gamesList)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    gamesList: List<GameItem>
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "Notifications",
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Settings",
                                )
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                NavigationBar {
                    var selected by remember { mutableStateOf("") }
                    NavigationBarItem(
                        selected = selected == "featured",
                        onClick = {
                            selected = "featured"
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Featured"
                            )
                        },
                        label = { Text("Featured") }
                    )

                    NavigationBarItem(
                        selected = selected == "history",
                        onClick = {
                            selected = "history"
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.archive_24px),
                                contentDescription = "History"
                            )
                        },
                        label = { Text("History") }
                    )

                    NavigationBarItem(
                        selected = selected == "profile",
                        onClick = {
                            selected = "profile"
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile"
                            )
                        },
                        label = { Text("Profile") }
                    )

                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Lueys Game Store"
                )
            }
            for (game in gamesList) {
                GameListItem(item = game) {
                    val intent = Intent(context, GameDetailActivity::class.java)

                    intent.putExtra("gameName", game.gameName)
                    intent.putExtra("gameDesc", game.gameDesc)
                    intent.putExtra("gameImg", game.gameImg)
                    intent.putExtra("gameId", game.id)

                    context.startActivity(intent)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    GameStoreTheme {
        val gamesList = listOf(
            GameItem("We hold places", "And forever we shal hold", R.drawable.dungeonsanddragonslogo, 1)
        )
        MainView(gamesList)
    }
}