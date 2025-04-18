package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.board.Board.BoardBuilder;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public abstract class CastleMove extends Move {

    protected final Rook castleRook;
    protected final int castleRookStart;
    protected final int castleRookDestination;

    public CastleMove(final Board board,
                      final Piece movedPiece,
                      final int destinationCoordinate,
                      final Rook castleRook,
                      final int castleRookStart,
                      final int castleRookDestination) {
        super(board, movedPiece, destinationCoordinate);
        this.castleRook = castleRook;
        this.castleRookStart = castleRookStart;
        this.castleRookDestination = castleRookDestination;
    }

    public Rook getCastleRook() {
        return castleRook;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.castleRook.hashCode();
        result = prime * result + this.castleRookDestination;
        return result;
    }

    @Override
    public boolean equals(Object object) {
        return this == object ||
              (object instanceof CastleMove &&
               super.equals(object) &&
               this.castleRook.equals(((CastleMove) object).getCastleRook()));
    }

    @Override
    public boolean isCastlingMove() {
        return true;
    }

    @Override
    public Board execute() {
        final BoardBuilder builder= new BoardBuilder();
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        // TODO: set first move to false
        builder.setPiece(new Rook(this.castleRook.getPiecePosition(), this.castleRook.getAlliance()))
               .setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance())
                .setTransitionMove(this);
        return builder.build();
    }

    public static final class KingSideCastleMove extends CastleMove {
        public KingSideCastleMove(final Board board,
                                  final Piece movedPiece,
                                  final int destinationCoordinate,
                                  final Rook castleRook,
                                  final int castleRookStart,
                                  final int castleRookDestination) {
            super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
        }

        @Override
        public boolean equals(Object object) {
            return this == object ||
                    (object instanceof KingSideCastleMove &&
                            super.equals(object) &&
                            this.castleRook.equals(((KingSideCastleMove) object).getCastleRook()));
        }

        @Override
        public String toString() {
            return "O-O";
        }
    }

    public static final class QueenSideCastleMove extends CastleMove {
        public QueenSideCastleMove(final Board board,
                                   final Piece movedPiece,
                                   final int destinationCoordinate,
                                   final Rook castleRook,
                                   final int castleRookStart,
                                   final int castleRookDestination) {
            super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
        }

        @Override
        public boolean equals(Object object) {
            return this == object ||
                    (object instanceof QueenSideCastleMove &&
                            super.equals(object) &&
                            this.castleRook.equals(((QueenSideCastleMove) object).getCastleRook()));
        }

        @Override
        public String toString() {
            return "O-O-O";
        }
    }
}
