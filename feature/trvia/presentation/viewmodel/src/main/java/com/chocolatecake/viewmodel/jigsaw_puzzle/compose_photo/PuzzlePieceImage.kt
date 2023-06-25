package com.chocolatecake.viewmodel.jigsaw_puzzle.compose_photo

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView

data class PuzzlePieceImage(
    private val context: Context,
    var xCoord: Int = 0,
    var yCoord: Int = 0,
    var pieceWidth: Int = 0,
    var pieceHeight: Int = 0,
    var canMove: Boolean = true
) : AppCompatImageView(context)