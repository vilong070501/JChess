package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.pieces.Piece;

public class MajorAttackMove extends AttackMove {
    public MajorAttackMove(final Board board,
                           final Piece movedPiece,
                           final int destinationCoordinate,
                           final Piece pieceAttacked) {
        super(board, movedPiece, destinationCoordinate, pieceAttacked);
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof MajorAttackMove && super.equals(object));
    }

    @Override
    public String toString() {
        return movedPiece.getPieceType() + BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
    }
}
