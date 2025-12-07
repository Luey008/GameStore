package pt.iade.ei.gamestore.Controllers

import pt.iade.ei.gamestore.Models.*
import pt.iade.ei.gamestore.R

class GameController {
    fun GetListOfGames(onResult: (List<GameItem>) -> Unit) {
        val list = ArrayList<GameItem>()

        list.add(
            GameItem(
                gameName = "TechClub",
                gameDesc = "TechClub : A group by students for students, where you can indulge in your hobbies and enjoy learning with your peers",
                gameImg = R.drawable.techclub_logo,
                id = 1
            )
        )
        onResult(list)
    }
}