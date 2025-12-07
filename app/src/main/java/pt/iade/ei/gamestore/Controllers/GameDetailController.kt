package pt.iade.ei.gamestore.Controllers

import pt.iade.ei.gamestore.R

class GameDetailController {
    fun GetListOfGameDetails(gameId: Int, onResult: (List<GameDetailItem>) -> Unit) {
        val list = ArrayList<GameDetailItem>()

        if (gameId == 1) {
            list.add(
                GameDetailItem(
                    itemName = "test",
                    itemDesc = "test",
                    itemPrice = 79.99,
                    itemImg = R.drawable.dungeonsanddragonslogo,
                    id = 1
                )
            )

            list.add(
                GameDetailItem(
                    itemName = "test",
                    itemDesc = "test",
                    itemPrice = 1.99,
                    itemImg = R.drawable.dungeonsanddragonslogo,
                    id = 2
                )
            )
        }
        onResult(list)
    }
}