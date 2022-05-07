package com.example.chessgame

import android.content.Context
import android.graphics.Canvas


class Board(var left: Float, var right: Float, var top: Float, var bottom: Float, private var view: DrawingView,private val context: Context ){
    val cimetiere =Cimetiere(mutableListOf())
    var partie = Partie()
    var cases = mutableListOf<Case>()



    fun initialisation() {
        // crée les cases du board et les pièces, met les pièces à leur poisitions initiales
        if(cases.size < 64){
            var compteur = 0
            val dx = (right-left) / 8
            val dy = (bottom - top) / 8
            var xi = left
            var yi = top
            for (i in 1..8) { //lignes
                for (j in 1..8) { //colonnes
                    var piece: Piece? = null
                    cases.add(Case(i, j, xi, yi, xi + dx, yi + dy, context))
                    when{
                        i == 7 -> piece = Pion(cases[compteur], "white")
                        //pions noirs
                        i == 2 -> piece = Pion(cases[compteur], "black")
                        // tours blanches
                        i ==8 && (j == 1 || j == 8) -> piece = Tour(cases[compteur], "white")
                        //tours noires
                        (i ==1 && (j == 1 || j == 8)) -> piece = Tour(cases[compteur], "black")
                        //cavaliers blancs
                         (i ==8 && (j == 2 || j == 7)) ->piece = Cavalier(cases[compteur], "white")
                        //cavaliers noirs
                        (i ==1 && (j == 2 || j == 7)) -> piece = Cavalier(cases[compteur], "black")
                        //fous blancs
                        (i ==8 && (j == 3 || j == 6)) -> piece = Fou(cases[compteur], "white")
                        //fous noirs
                        (i ==1 && (j == 3 || j == 6)) -> piece = Fou(cases[compteur], "black")
                        //reine blanche
                        (i ==8 && j == 4)-> piece = Reine(cases[compteur], "white")
                        //reine noire
                        (i ==1 && j == 4) -> piece = Reine(cases[compteur], "black")
                        //roi blanc
                        (i ==8 && j == 5)-> piece = Roi(cases[compteur], "white")
                        //roi noir
                        (i ==1 && j == 5)-> piece = Roi(cases[compteur], "black")
                    }
                    cases[compteur].piece = piece
                    compteur += 1
                    xi += dx
                }
                yi += dy
                xi = left
            }
        }
    }

    fun clear(){
        cases = mutableListOf()

        cimetiere.res = true
    }

    fun draw(canvas: Canvas) {
        for (case in cases){
            case.setRect()
            case.draw(canvas)
        }
    }

    fun selection(caseRef:Int , colorier: Boolean){
        cases[caseRef].focus = colorier
        return
    }

    fun bouger(from:Int, to:Int):Boolean{
        println("move")
        val moved = (cases[from].piece!!.bouger(cases[to], cases))
        if(cases[to].piece != null && moved){
            if( cases[to].piece is Roi && cases[from].piece !is Tour){
                view.kingDead(cases[to].piece!!.color)
            }
             if(cases[to].piece!!.color == cases[from].piece!!.color) { //si roque
                if(cases[to].piece!!.bouger(cases[from],cases)){
                    val toPos = cases[to].piece!!.position
                    val fromPos = cases[from].piece!!.position
                    cases[(fromPos.row - 1) * 8 + fromPos.col - 1].piece = cases[from].piece
                    cases[(toPos.row - 1) * 8 + toPos.col - 1].piece = cases[to].piece
                    cases[to].piece = null
                    cases[from].piece = null
                    return moved
                } else return false
            }
            else {
            mourir(cases[to].piece)
            cases[to].piece = cases[from].piece
            cases[from].piece = null
            if (cases[to].piece is Pion ){
                if ( to<8  && cases[to].piece!!.color=="white"){
                    QueenBecoming("white", to)}

                else if (to>55 && cases[to].piece!!.color=="black"  ){
                    QueenBecoming("black", to)
                }
            }
            }
        }

        else if(moved){
            cases[to].piece = cases[from].piece
            cases[from].piece = null
            if (cases[to].piece is Pion){
                if ( to<8  && cases[to].piece!!.color=="white"){
                    QueenBecoming("white", to)}

                else if (to>55 && cases[to].piece!!.color=="black"  ){
                    QueenBecoming("black", to)
                }
            }
        }
        return moved
    }
    
    private fun mourir(piece: Piece?){
        cimetiere.ajouterPiece(piece!!)
    }
    fun checkTour():Boolean{
        return partie.tour
    }

    fun changeTour(){
        partie.changeTour()
    }

    private fun QueenBecoming(queencolor: String, to: Int) {
        cases[to].piece = Reine(cases[to], queencolor)
    }


}

