package pt.iade.ei.gamestore.Controllers

import pt.iade.ei.gamestore.Models.*
import pt.iade.ei.gamestore.R

class GameController {
    fun GetListOfGames(onResult: (List<GameItem>) -> Unit) {
        val list = ArrayList<GameItem>()

        list.add(
            GameItem(
                gameName = "test",
                gameDesc = "test",
                gameImg = R.drawable.dungeonsanddragonslogo,
                id = 1
            )
        )
        onResult(list)
    }
}