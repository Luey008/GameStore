package pt.iade.ei.gamestore.ui.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.gamestore.Controllers.GameDetailItem
import pt.iade.ei.gamestore.R
import pt.iade.ei.gamestore.ui.theme.GameStoreTheme

@Composable
fun GameDetailListItem(
    itemname: String,
    itemdesc: String,
    itemprice: Double,
    @DrawableRes itemimg : Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {

        Image(
            painter = painterResource(itemimg),
            contentDescription = "$itemname",
            modifier = Modifier
                .padding(end = 16.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "$itemname",
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp
                )

                Text("$itemdesc",
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true
                )
            }

            Column {
                Text(
                    text = "${itemprice}€",
                )
            }
        }
    }
}

@Composable
fun GameDetailListItem(
    item: GameDetailItem,
    onClick: () -> Unit
) {
    GameDetailListItem(
        itemname = item.itemName,
        itemdesc = item.itemDesc,
        itemprice = item.itemPrice,
        itemimg = item.itemImg,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun GameDetailListItemPreview() {
    val item = GameDetailItem(
        itemName = "Hows it going Nathan",
        itemDesc = "Hope you have a good day cause eveyone need a good day",
        itemPrice = 19.99,
        itemImg = R.drawable.dungeonsanddragonslogo
    )

    GameStoreTheme {
        GameDetailListItem(item, onClick = {})
    }
}