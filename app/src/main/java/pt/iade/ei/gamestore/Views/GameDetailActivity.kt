package pt.iade.ei.gamestore.Views

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import pt.iade.ei.gamestore.Models.*
import pt.iade.ei.gamestore.Controllers.*
import pt.iade.ei.gamestore.R
import pt.iade.ei.gamestore.ui.Components.*
import android.widget.Toast

//funny
// This import is used for the sound effect logic.
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

class GameDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val name = intent.getStringExtra("gameName") ?: "We are placeholders"
        val desc = intent.getStringExtra("gameDesc") ?: "We hold out places"
        val img = intent.getIntExtra("gameImg", R.drawable.dungeonsanddragonslogo)
        val id = intent.getIntExtra("gameId", 0)

        val game = GameItem(name, desc, img, id)


        setContent {
            GameStoreTheme {

                val GameDetailList = remember { mutableStateListOf<GameDetailItem>() }

                GameDetailController().GetListOfGameDetails(id) { list ->
                    if (GameDetailList.isEmpty()) {
                        GameDetailList.addAll(list)
                    }
                }

                GameDetailView(game, GameDetailList)
            }
        }
    }
}

@Composable
fun GameDetailBottomSheetContent(
    item: GameDetailItem,
    onClose: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            text = item.itemName,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.DarkGray)
            ) {
                Image(
                    painter = painterResource(item.itemImg),
                    contentDescription = item.itemName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = item.itemDesc,
                fontSize = 18.sp,
                color = Color.Gray,
                lineHeight = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${item.itemPrice}€",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {
                    Toast.makeText(context,
                        "Acabou de comprar o item ${item.itemName} por ${item.itemPrice}€",
                        Toast.LENGTH_LONG).show()

                    onClose()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6750A4)
                )
            ) {
                Text("Buy with 1-click")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun GameDetailInDetail(game: GameItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 20.dp)
        ) {
            Image(
                painter = painterResource(id = game.gameImg),
                contentDescription = game.gameName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = game.gameDesc,
                fontSize = 19.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 5
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailView(
    game: GameItem,
    GameDetailList: List<GameDetailItem>
) {
    val context = LocalContext.current

    var showSheet by remember { mutableStateOf(false) }
    var selectedGameDetail by remember { mutableStateOf<GameDetailItem?>(null) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        (context as? Activity)?.finish()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = game.gameName,
                            maxLines = 1,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 24.sp,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite"
                            )
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            GameDetailInDetail(game)

            Text(
                text = "Purchasable Items",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 8.dp)
            )

            for (GameDetail in GameDetailList) {
                GameDetailListItem(item = GameDetail, onClick = {
                    selectedGameDetail = GameDetail
                    showSheet = true
                })
            }
        }

        if (showSheet && selectedGameDetail != null) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState
            ) {
                GameDetailBottomSheetContent(
                    item = selectedGameDetail!!,
                    onClose = { showSheet = false }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameDetailPreview() {
    GameStoreTheme {
        val game = GameItem("And forever we shall hold places", "This text is supposed to be long and im really not that creative so I am just spaming whatever words come to mind so dont mind what the hell is in here", R.drawable.dungeonsanddragonslogo, 1)

        val GameDetailList = listOf(
            GameDetailItem("ok so i really am running", "of ideas", 69.99, R.drawable.dungeonsanddragonslogo, 1),
            GameDetailItem("so lets take this time", "and really think about ourselves", 12.99, R.drawable.dungeonsanddragonslogo, 2)
        )
        GameDetailView(game, GameDetailList)
    }
}