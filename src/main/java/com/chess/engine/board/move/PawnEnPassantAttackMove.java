package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.board.Board.BoardBuilder;
import com.chess.engine.pieces.Piece;
public final class PawnEnPassantAttackMove extends PawnAttackMove {

    public PawnEnPassantAttackMove(final Board board,
                          final Piece movedPiece,
                          final int destinationCoordinate,
                          final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate, attackedPiece);
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof PawnEnPassantAttackMove && super.equals(object));
    }

    @Override
    public Board execute() {
        final BoardBuilder builder = new BoardBuilder();
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            if (!piece.equals(this.attackPiece)) {
                builder.setPiece(piece);
            }
        }
        builder.setPiece(this.movedPiece.movePiece(this))
                .setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance())
                .setTransitionMove(this);
        return builder.build();
    }
}
