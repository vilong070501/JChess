package com.engine.player;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.move.Move;
import com.engine.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends Player {
    public WhitePlayer(Board board,
                       Collection<Move> whiteStandardLegalMoves,
                       Collection<Move> blackStandardLegalMoves)
    {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    protected Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

}
