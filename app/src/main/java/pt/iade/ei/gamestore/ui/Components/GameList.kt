package pt.iade.ei.gamestore.ui.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gamestore.Models.GameItem
import pt.iade.ei.gamestore.R
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

@Composable
fun GameListItem(
    gamename: String,
    gamedesc: String,
    @DrawableRes gameimg: Int,
    onClick: () -> Unit
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(
                elevation = 8.dp,
            )
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = gameimg),
                contentDescription = gamename,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize(),
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.6f),
                    )
            ) {
                Text(
                    text = gamename,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Composable
fun GameListItem(
    item: GameItem,
    onClick: () -> Unit
) {
    GameListItem(
        gamename = item.gameName,
        gamedesc = item.gameDesc,
        gameimg = item.gameImg,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun GameListItemPreview() {
    val item = GameItem(
        gameName = "i really have no ideas",
        gameDesc = "so think about your life teacher",
        gameImg = R.drawable.dungeonsanddragonslogo
    )
    GameStoreTheme {
        GameListItem(item, onClick = {})
    }
}