package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.CastleMove;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player {
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves)
    {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }

    @Override
    protected Collection<Move> calculateLKingCastles(final Collection<Move> playerLegals,
                                                     final Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        /* This must be the King's first move, and it is not in check */
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            /* Black king side castle */
            /* There must be no pieces between the King and the rook in king side */
            if (!this.board.getTile(5).isOccupied() && !this.board.getTile(6).isOccupied()) {
                final Tile rookTile = this.board.getTile(7);
                /* The Rook's first move */
                if (rookTile.isOccupied() &&
                    rookTile.getPiece().isFirstMove() &&
                    rookTile.getPiece().getPieceType().isRook()) {
                    if (Player.calculateAttacksOnTile(5, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(6, opponentLegals).isEmpty()
                    ) {
                        kingCastles.add(new CastleMove.KingSideCastleMove(
                                this.board, this.playerKing, 6,
                                (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 5)
                        );
                    }
                }
            }
            /* Black queen side castle */
            if (!this.board.getTile(1).isOccupied() &&
                !this.board.getTile(2).isOccupied() &&
                !this.board.getTile(3).isOccupied()
            ) {
                final Tile rookTile = this.board.getTile(0);
                if (rookTile.isOccupied() &&
                    rookTile.getPiece().isFirstMove() &&
                    rookTile.getPiece().getPieceType().isRook()
                ) {
                    if (Player.calculateAttacksOnTile(1, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(2, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(3, opponentLegals).isEmpty()
                    ) {
                        kingCastles.add(new CastleMove.KingSideCastleMove(
                                this.board, this.playerKing, 2,
                                (Rook)rookTile.getPiece(), rookTile.getTileCoordinate(), 3)
                        );
                    }
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
