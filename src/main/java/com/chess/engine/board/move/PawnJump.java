package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.board.Board.BoardBuilder;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

public final class PawnJump extends Move {

    public PawnJump(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof PawnJump && super.equals(object));
    }

    @Override
    public String toString() {
        return String.valueOf(this.destinationCoordinate);
    }

    @Override
    public Board execute() {
        final BoardBuilder builder= new BoardBuilder();
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        final Pawn movedPawn = (Pawn)this.movedPiece.movePiece((this));
        builder.setPiece(movedPawn)
               .setEnPassantPawn(movedPawn)
               .setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance())
               .setTransitionMove(this);
        return builder.build();
    }
}
