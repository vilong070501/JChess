package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.CastleMove.KingSideCastleMove;
import com.chess.engine.board.move.CastleMove.QueenSideCastleMove;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves)
    {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
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

    @Override
    protected Collection<Move> calculateLKingCastles(final Collection<Move> playerLegals,
                                                     final Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        /* This must be the King's first move, and it is not in check */
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            /* White king side castle */
            /* There must be no pieces between the King and the rook in king side */
            if (!this.board.getTile(61).isOccupied() && !this.board.getTile(62).isOccupied()) {
                final Tile rookTile = this.board.getTile(63);
                /* The Rook's first move */
                if (rookTile.isOccupied() &&
                    rookTile.getPiece().isFirstMove() &&
                    rookTile.getPiece().getPieceType().isRook())
                {
                    if (Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(62, opponentLegals).isEmpty()
                    ) {
                        kingCastles.add(new KingSideCastleMove(
                                this.board, this.playerKing, 62,
                                (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 61)
                        );
                    }
                }
            }
            /* White queen side castle */
            if (!this.board.getTile(59).isOccupied() &&
                !this.board.getTile(58).isOccupied() &&
                !this.board.getTile(57).isOccupied()
            ) {
                final Tile rookTile = this.board.getTile(56);
                if (rookTile.isOccupied() &&
                    rookTile.getPiece().isFirstMove() &&
                    rookTile.getPiece().getPieceType().isRook()
                ) {
                    if (Player.calculateAttacksOnTile(59, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(58, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(57, opponentLegals).isEmpty()
                    ) {
                        kingCastles.add(new QueenSideCastleMove(
                                this.board, this.playerKing, 58,
                                (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 59)
                        );
                    }
                }
            }
        }

        return ImmutableList.copyOf(kingCastles);
    }
}
