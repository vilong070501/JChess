package com.engine.board.move;

import com.engine.board.Board;
import com.engine.pieces.Piece;

public final class AttackMove extends Move
{
    final Piece attackPiece;

    public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackPiece = attackPiece;
    }
}
