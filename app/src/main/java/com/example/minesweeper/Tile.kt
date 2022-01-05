package com.example.minesweeper

import android.widget.ImageView


val cardImageIds =  arrayOf(
    R.drawable.t0, R.drawable.t1, R.drawable.t2, R.drawable.t3,
    R.drawable.t4, R.drawable.t5, R.drawable.t6, R.drawable.t7,
    R.drawable.t8, R.drawable.bomb, R.drawable.tile, R.drawable.flagged
)
class Tile(var mark: Int, val row:Int ,val coloum:Int ,var open:Int,var flag:Int) {
    var imageId = cardImageIds[mark]

    fun imageIdGet():Int{
        return cardImageIds[mark]
    }
}