package pt.iade.ei.gamestore.Controllers

import pt.iade.ei.gamestore.R

class GameDetailController {
    fun GetListOfGameDetails(gameId: Int, onResult: (List<GameDetailItem>) -> Unit) {
        val list = ArrayList<GameDetailItem>()

        if (gameId == 1) {
            list.add(
                GameDetailItem(
                    itemName = "Knowledge of the Gods (Nathan)",
                    itemDesc = "With the knowledge of the Gods (Nathan) you shall be able to fix, create or tinker with anything that could be considered technological",
                    itemPrice = 99.99,
                    itemImg = R.drawable.knowledge,
                    id = 1
                )
            )

            list.add(
                GameDetailItem(
                    itemName = "Help from the Gods (Nathan)",
                    itemDesc = "With help from the Gods (Nathan) anything that you possess that holds an eletrical pulse shall be fixed and perform 20% better than original",
                    itemPrice = 67.67,
                    itemImg = R.drawable.help,
                    id = 2
                )
            )

            list.add(
                GameDetailItem(
                    itemName = "The tool keeper (Bernardo)",
                    itemDesc = "Simply use this item and ask Bernardo the tool keeper, for any tool, he then moves to the room in the back and shall bring anything you wish for",
                    itemPrice = 57.83,
                    itemImg = R.drawable.tool_keeper,
                    id = 2
                )
            )
        }

        if (gameId == 2) {
            list.add(
                GameDetailItem(
                    itemName = "Cheese",
                    itemDesc = "Cheese",
                    itemPrice = 999999.999999,
                    itemImg = R.drawable.cheese,
                    id = 1
                )
            )

            list.add(
                GameDetailItem(
                    itemName = "Cheese",
                    itemDesc = "Cheese",
                    itemPrice = 999999.999999,
                    itemImg = R.drawable.cheese,
                    id = 1
                )
            )

            list.add(
                GameDetailItem(
                    itemName = "Cheese",
                    itemDesc = "Cheese",
                    itemPrice = 999999.999999,
                    itemImg = R.drawable.cheese,
                    id = 1
                )
            )
        }

        onResult(list)
    }
}