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
    protected final int cachedHashCode;

    Piece(final PieceType pieceType, final int position, final Alliance alliance, final boolean isFirstMove) {
        this.pieceType = pieceType;
        this.position = position;
        this.alliance = alliance;
        this.isFirstMove = isFirstMove;
        this.cachedHashCode = computeHashCode();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Piece otherPiece)) {
            return false;
        }
        return this.position == otherPiece.position && this.pieceType == otherPiece.pieceType &&
               this.alliance == otherPiece.alliance && this.isFirstMove == otherPiece.isFirstMove;
    }

    private int computeHashCode() {
        int prime = 31;
        int result = this.pieceType.hashCode();
        result = prime * result + this.alliance.hashCode();
        result = prime * result + this.position;
        result = prime * result + (this.isFirstMove ? 1 : 0);
        return result;
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

    protected boolean buildMove(Board board, List<Move> legalMoves, int candidateDestinationCoordinate) {
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
            return true;
        }
        return false;
    }

    public abstract Piece movePiece(final Move move);

    public int getPieceValue() {
        return this.pieceType.getPieceValue();
    }

    public enum PieceType {

        PAWN("P", 100) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R", 500) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        },
        KNIGHT("N", 300) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B", 300) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        QUEEN("Q", 900) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K", 10000) {
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
        private final int pieceValue;

        PieceType(final String pieceName, final int pieceValue) {
            this.pieceName = pieceName;
            this.pieceValue = pieceValue;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();

        public int getPieceValue() {
            return this.pieceValue;
        }
    }
}
