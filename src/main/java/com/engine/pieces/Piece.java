package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.move.AttackMove;
import com.engine.board.move.MajorMove;
import com.engine.board.move.Move;
import com.engine.board.tile.Tile;

import java.util.Collection;
import java.util.List;

public abstract class Piece {

    protected final int position;
    protected final Alliance alliance;
    protected final boolean isFirstMove;

    Piece(final int position, final Alliance alliance) {
        this.position = position;
        this.alliance = alliance;
        this.isFirstMove = false;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public Alliance getAlliance() {
        return this.alliance;
    }

    public Integer getPiecePosition() {
        return this.position;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

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

    public enum PieceType {

        PAWN("P"),
        ROOK("R"),
        KNIGHT("N"),
        BISHOP("B"),
        QUEEN("Q"),
        KING("K");

        private final String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
