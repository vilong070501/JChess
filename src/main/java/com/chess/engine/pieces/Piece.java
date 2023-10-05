package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.AttackMove;
import com.chess.engine.board.move.MajorMove;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.tile.Tile;

import java.util.Collection;
import java.util.List;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final int position;
    protected final Alliance alliance;
    protected final boolean isFirstMove;

    Piece(final PieceType pieceType, final int position, final Alliance alliance) {
        this.pieceType = pieceType;
        this.position = position;
        this.alliance = alliance;
        this.isFirstMove = false;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public Integer getPiecePosition() {
        return this.position;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    protected void buildMove(Board board, List<Move> legalMoves, int candidateDestinationCoordinate) {
        final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
        if (!candidateDestinationTile.isOccupied()) {
            legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
        } else {
            final Piece pieceAtDestination = candidateDestinationTile.getPiece();
            final Alliance pieceAlliance = pieceAtDestination.getAlliance();

            /* If the tile is occupied by an enemy, create an attacking move  */
            if (this.alliance != pieceAlliance) {
                legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
            }
        }
    }

    public abstract Piece movePiece(final Move move);

    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

        private final String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();
    }
}
