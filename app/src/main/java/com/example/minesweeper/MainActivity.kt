package com.example.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.System.currentTimeMillis
import java.util.*

class MainActivity : AppCompatActivity() {
    var tileList: MutableList<MutableList<ImageView?>> = mutableListOf()
    val tarray = tileArray()
    var startTime : Long = 0L
    var endTime : Long = 0L
    var totalTime :Long = 0L
    var clickCount = 0
    var tileCount = 0
    var modeSwitch = 0
//    var flagCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tileList = mutableListOf(
            mutableListOf(tileR0C0,tileR0C1,tileR0C2,tileR0C3,tileR0C4,tileR0C5,tileR0C6,tileR0C7),
            mutableListOf(tileR1C0,tileR1C1,tileR1C2,tileR1C3,tileR1C4,tileR1C5,tileR1C6,tileR1C7),
            mutableListOf(tileR2C0,tileR2C1,tileR2C2,tileR2C3,tileR2C4,tileR2C5,tileR2C6,tileR2C7),
            mutableListOf(tileR3C0,tileR3C1,tileR3C2,tileR3C3,tileR3C4,tileR3C5,tileR3C6,tileR3C7),
            mutableListOf(tileR4C0,tileR4C1,tileR4C2,tileR4C3,tileR4C4,tileR4C5,tileR4C6,tileR4C7),
            mutableListOf(tileR5C0,tileR5C1,tileR5C2,tileR5C3,tileR5C4,tileR5C5,tileR5C6,tileR5C7),
            mutableListOf(tileR6C0,tileR6C1,tileR6C2,tileR6C3,tileR6C4,tileR6C5,tileR6C6,tileR6C7),
            mutableListOf(tileR7C0,tileR7C1,tileR7C2,tileR7C3,tileR7C4,tileR7C5,tileR7C6,tileR7C7)
        )
        txtGame.text = ""
        txtTime.text = ""
//        Log.d("test2","test")
//        tarray.bombRandom()
    }
    fun restart(view: View){
        tarray.start()
        clickCount = 0
        for(i in tileList){
            for(tile in i){
                tile!!.setImageResource(R.drawable.tile)
                tile.isClickable = true
                txtGame.text = ""
                txtTime.text = ""
            }
        }
    }
    fun tileClick(view: View){
        if(modeSwitch == 0 ) {
            tileCount = 0
            for (i in tarray.tiles) {
                for (tile in i) {
//            Log.d("test2" , i!!.id.toString())
//            Log.d("test2" , view.id.toString())
                    if (view == tileList[tile!!.row][tile!!.coloum]
                        && tile!!.flag == 0) {
//                    Log.d("test3", i!!.mark.toString())
                        if (clickCount == 0) {
//                    Log.d("test4" , i!!.mark.toString())
                            startTime = currentTimeMillis()
                            tarray.bombRandom(tile!!.row, tile!!.coloum)
//                        tarray.tiles[tile!!.row][tile!!.coloum]!!.mark = 0
                        }
                        if (tile!!.mark == 9) {
                            for (i in tarray.tiles) {
                                for (j in i) {
                                    if (j!!.mark == 9) {
                                        tileList[j!!.row][j!!.coloum]!!.setImageResource(j!!.imageIdGet())
                                    }
                                    tileList[j!!.row][j!!.coloum]!!.isClickable = false
                                }
                            }
                            txtGame.text = "Game Over"
                        }
                        changeImage(tile!!.row, tile!!.coloum)
                        view.isClickable = false
                        clickCount++
                    }
                    if (tile.open == 0 && tile.mark != 9) {
                        tileCount++
                    }
                }
            }
            if (tileCount == 56) {
                for (i in tileList) {
                    for (imageTile in i) {
                        imageTile!!.isClickable = false

                    }
                }
                endTime = currentTimeMillis()
                totalTime = (endTime - startTime) / 1000
                txtGame.text = "Game Clear"
                txtTime.text = totalTime.toString()
            }
//        for(i in tarray.tiles) {
//            Log.d("test" , i!!.mark.toString())
//        }
        }
        else{
            for(i in tarray.tiles){
                for(tile in i){
                    if (view == tileList[tile!!.row][tile!!.coloum]) {
                        if(tile.open == 1){
                            if(tile.flag == 0) {
                                tileList[tile!!.row][tile!!.coloum]!!.setImageResource(R.drawable.flagged)
                                tile.flag = 1
                            }else{
                                tileList[tile!!.row][tile!!.coloum]!!.setImageResource(R.drawable.tile)
                                tile.flag = 0
                            }
                        }
                    }
                }
            }
        }
    }
    fun changeImage(row:Int ,coloum:Int){
        val sideTiles = arrayListOf(
            arrayOf(0, -1),
            arrayOf(0, 1),
            arrayOf(1, 0),
//            arrayOf(1, -1),
//            arrayOf(1, 1),
            arrayOf(-1, 0),
//            arrayOf(-1, -1),
//            arrayOf(-1, 1)
        )
        tileList[row][coloum]!!.setImageResource(tarray.tiles[row][coloum]!!.imageIdGet())
        tarray.tiles[row][coloum]!!.open = 0
        if(tarray.tiles[row][coloum]!!.mark == 0) {
            for (sideTile in sideTiles) {
                val rowCheck = row + sideTile[0]
                val coloumCheck = coloum + sideTile[1]
                if (rowCheck in 0..7 && coloumCheck in 0..7
                    && tarray.tiles[rowCheck][coloumCheck]!!.mark == 0
                    && tarray.tiles[rowCheck][coloumCheck]!!.open == 1
//                && tileList[rowCheck][coloumCheck]!!.resources.equals(R.drawable.tile)
//                && tarray.tiles[indexCheck]!!.mark ==  10
                ) {
                    tileList[rowCheck][coloumCheck]!!.isClickable = false
                    tarray.tiles[rowCheck][coloumCheck]!!.open = 0
                    tileList[rowCheck][coloumCheck]!!.setImageResource(tarray.tiles[rowCheck][coloumCheck]!!.imageIdGet())
                    changeImage(rowCheck, coloumCheck)
                }
                if (rowCheck in 0..7 && coloumCheck in 0..7
                    && tarray.tiles[rowCheck][coloumCheck]!!.mark in 1..8
                    && tarray.tiles[rowCheck][coloumCheck]!!.open == 1
//                && tileList[rowCheck][coloumCheck]!!.resources.equals(R.drawable.tile)
//                && tarray.tiles[indexCheck]!!.mark ==  10
                ) {
                    tarray.tiles[rowCheck][coloumCheck]!!.open = 0
                    tileList[rowCheck][coloumCheck]!!.isClickable = false
                    tileList[rowCheck][coloumCheck]!!.setImageResource(tarray.tiles[rowCheck][coloumCheck]!!.imageIdGet())
                }
            }
        }
    }
    fun modeChange(view:View){
        if(modeSwitch == 0){
            modeSwitch = 1
            view.alpha = 0.5F
        }else{
            modeSwitch = 0
            view.alpha = 1F
        }
    }
}
