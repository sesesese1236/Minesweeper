package com.example.minesweeper

import android.widget.ImageView
import java.util.*

class tileArray{
    var tiles : MutableList<MutableList<Tile?>> = mutableListOf()

//    var bomb: MutableList<MutableList<Int>> = mutableListOf(
//        mutableListOf(10,10,10,10,10,10,10,10),
//        mutableListOf(10,10,10,10,10,10,10,10),
//        mutableListOf(10,10,10,10,10,10,10,10),
//        mutableListOf(10,10,10,10,10,10,10,10),
//        mutableListOf(10,10,10,10,10,10,10,10),
//        mutableListOf(10,10,10,10,10,10,10,10),
//        mutableListOf(10,10,10,10,10,10,10,10),
//        mutableListOf(9,9,9,9,9,9,9,9)
//    )
    init{
        start()
    }
    fun start(){
        tiles = mutableListOf()
        for(i in 0..7){
            tiles.add(mutableListOf())
            for(j in 0..7) {
                tiles[i].add(Tile((10), (i),(j),(1),(0)))
            }
        }
    }
    fun bombRandom(exceptRow:Int,exceptColoum:Int){
//        bomb.shuffle()
//        for(j in tiles){
//            for(i in j) {
//                if (i!!.row == exceptRow || i!!.coloum == exceptColoum) {
//                    if (bomb[i!!.row][i!!.coloum] == 9) {
//                        bombExcept(i!!.row,i!!.coloum)
////                        bombRandom(exceptRow,exceptColoum)
//                    }
//                    i!!.mark == 0
//                } else {
//                    i!!.mark = bomb[i!!.row][i!!.coloum]
//                }
//            }
//        }
//        val bomb: Array<Int> = arrayOf(9,9,9,9,9,9,9,9)
        val col: Array<Int> = arrayOf(0,1,2,3,4,5,6,7)
        val row: Array<Int> = arrayOf(0,1,2,3,4,5,6,7)
        col.shuffle()
        row.shuffle()
        val sideTiles = arrayListOf(
            arrayOf(0, -1),
            arrayOf(0, 1),
            arrayOf(1, 0),
            arrayOf(1, -1),
            arrayOf(1, 1),
            arrayOf(-1, 0),
            arrayOf(-1, -1),
            arrayOf(-1, 1)
        )

        for (b in col.indices){
            if(row[b] == exceptRow && col[b] == exceptColoum
//                ||row[b] == exceptRow+sideTiles[0][0] && col[b] == exceptColoum+sideTiles[0][1]
//                ||row[b] == exceptRow+sideTiles[1][0] && col[b] == exceptColoum+sideTiles[1][1]
//                ||row[b] == exceptRow+sideTiles[2][0] && col[b] == exceptColoum+sideTiles[2][1]
//                ||row[b] == exceptRow+sideTiles[3][0] && col[b] == exceptColoum+sideTiles[3][1]
//                ||row[b] == exceptRow+sideTiles[4][0] && col[b] == exceptColoum+sideTiles[4][1]
//                ||row[b] == exceptRow+sideTiles[5][0] && col[b] == exceptColoum+sideTiles[5][1]
//                ||row[b] == exceptRow+sideTiles[6][0] && col[b] == exceptColoum+sideTiles[6][1]
//                ||row[b] == exceptRow+sideTiles[7][0] && col[b] == exceptColoum+sideTiles[7][1]
            ){
                bombExcept(row[b],col[b])
            }else{
                tiles[row[b]][col[b]]!!.mark = 9
            }
        }
        bombIndicate()
    }
    fun bombExcept(row:Int ,coloum:Int){
//        if(bomb[row][coloum] == 9){
//            if(row == 7 && coloum == 7){
//                bombExcept(0,0)
//            }else {
//                if(coloum == 7){
//                    bombExcept(row+1,0)
//                }else{
//                    bombExcept(row,coloum+1)
//                }
//            }
//        }else{
//            bomb[row][coloum] = 9
//        }
        if(row == 7 && coloum == 7){
            bombExcept(0,0)
        }else if(coloum == 7){
            bombExcept(row+1,0)
        }else{
            bombExcept(row,coloum+1)
        }
        tiles[row][coloum]!!.mark = 9
    }
    fun bombIndicate() {
//        val sideTiles = arrayOf(-1,1,-8,8,-9,-7,7,9)
//        for(tile in tiles){
//            var bombCount = 0
//            for(sideTile in sideTiles){
//                val indexCheck = tile!!.id+sideTile
//                if(indexCheck in 0..63 && tiles[indexCheck]!!.mark == 9 && tile!!.mark !=9){
//                    bombCount++
//                    tile!!.mark = bombCount
//                }
//            }
//            if(bombCount == 0 && tile!!.mark != 9){
//                tile!!.mark = bombCount
//            }
//        }
        val sideTiles = arrayListOf(
            arrayOf(0, -1),
            arrayOf(0, 1),
            arrayOf(1, 0),
            arrayOf(1, -1),
            arrayOf(1, 1),
            arrayOf(-1, 0),
            arrayOf(-1, -1),
            arrayOf(-1, 1)
        )
        for(arrayTile in tiles){
            for(tile in arrayTile) {
                var bombCount = 0
                for (sideTile in sideTiles) {
                    val rowCheck = tile!!.row + sideTile[0]
                    val coloumCheck = tile!!.coloum + sideTile[1]
                    if (rowCheck in 0..7 && coloumCheck in 0..7
                        && tiles[rowCheck][coloumCheck]!!.mark == 9 && tile!!.mark != 9
                    ) {
                        bombCount++
                        tile!!.mark = bombCount
                    }
                }
                if (bombCount == 0 && tile!!.mark != 9) {
                    tile!!.mark = bombCount
                }
            }
        }
    }
}