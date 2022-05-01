package com.example.chessgame

import kotlin.math.abs

class Roi(position: Case, color: String): Piece(position, color), Roque {
    var valeur = 100
    override var image = if (color == "white") R.drawable.w_king_png_512px else R.drawable.b_king_png_512px
    override fun bouger(newPos: Case,cases : MutableList<Case>): Boolean{
        // peut se déplacer
        if(newPos.piece == null || newPos.piece!!.color != color){
            val deltaCol = abs(position.col - newPos.col)
            val deltaRow = abs(position.row - newPos.row)
            return deltaCol == 1 && deltaRow == 1 || deltaCol + deltaRow == 1
        }
        else if (newPos.piece != null && newPos.piece!!.color == color){
            if(RoqueValide((position.row-1)*8 +position.col -1,(newPos.row-1)*8 +newPos.col -1, cases))return true
        }
        return false
    }




}
