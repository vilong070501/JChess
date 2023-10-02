package com.engine.board.move;

import com.engine.board.Board;
import com.engine.pieces.Piece;

public final class MajorMove extends Move {

    public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
