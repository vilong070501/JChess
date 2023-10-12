package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.pieces.Piece;

public final class PawnMove extends Move {

    public PawnMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }


    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof PawnMove && super.equals(object));
    }

    @Override
    public String toString() {
        return BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }
}
