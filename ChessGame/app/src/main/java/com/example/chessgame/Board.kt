package com.example.jeu_echec

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF



class Board(var boardHauteur: Float, var boardDebut: Float, var boardFin: Float, var width: Float, var view: DrawingView) {
    var r = RectF(boardDebut, boardHauteur, boardDebut+width, boardFin)
    val ligne = 8
    val colonne = 8
    val case = arrayOf<String>(
        "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
        "a7", "b7", "c7", "d7", "e7", "f7", 'g7', 'h7',
        'a6', 'b6', 'c6', 'd6', 'e6', 'f6', 'g6', 'h6',
        'a5', 'b5', 'c5', 'd5', 'e5', 'f5', 'g5', 'h5',
        'a4', 'b4', 'c4', 'd4', 'e4', 'f4', 'g4', 'h4',
        'a3', 'b3', 'c3', 'd3', 'e3', 'f3', 'g3', 'h3',
        'a2', 'b2', 'c2', 'd2', 'e2', 'f2', 'g2', 'h2',
        'a1', 'b1', 'c1', 'd1', 'e1', 'f1', "g1", 'h1'
    )

    var casePaint = Paint()
    fun draw(canvas: Canvas) {
        for (i in case.indices) {
            if (i % 2 == 0) {
                casePaint.color = Color.BLACK
            } else {
                casePaint.color = Color.WHITE
            }
            canvas.drawRect(r, casePaint)
        }
    }
}
