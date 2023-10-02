package com.engine.player;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.move.Move;
import com.engine.pieces.King;
import com.engine.pieces.Piece;

import java.util.Collection;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("This is an invalid chess board! No King piece found!!");
    }

    public boolean isLegalMove(Move move) {
        return this.legalMoves.contains(move);
    }

    // TODO: Implements these methods below
    public boolean isInCheck() {
        return false;
    }

    public boolean isInCheckMate() {
        return false;
    }

    public boolean isInStaleMate() {
        return false;
    }

    public boolean isCastled() {
        return false;
    }

    protected abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    public MoveTransition makeMove(final Move move) {
        return null;
    }
}
