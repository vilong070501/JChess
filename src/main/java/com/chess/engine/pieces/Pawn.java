package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.move.MajorMove;
import com.chess.engine.board.move.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {7, 8, 9, 16};

    public Pawn(int position, Alliance alliance) {
        super(PieceType.PAWN, position, alliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {

            final int candidateDestinationCoordinate = this.position + (this.alliance.getDirection() * currentCandidateOffset);

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            /* Non-attacking move */
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isOccupied()) {
                //TODO: Deal with promotions
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            /* Jump-move */
            } else if (canJump(currentCandidateOffset)) {
                final int behindCandidateDestinationCoordinate = this.position + (this.alliance.getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isOccupied() &&
                    !board.getTile(candidateDestinationCoordinate).isOccupied()) {
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            /* Attacking-move */
            } else if (currentCandidateOffset == 7 &&
                    !((BoardUtils.LAST_COLUMN.get(this.position) && this.alliance.isWhite()) ||
                      (BoardUtils.FIRST_COLUMN.get(this.position) && this.alliance.isBlack()))) {
                if (board.getTile(candidateDestinationCoordinate).isOccupied()) {
                    final Piece pieceCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.alliance != pieceCandidate.getAlliance()) {
                        // Add an attack move
                    }
                }
            } else if (currentCandidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN.get(this.position) && this.alliance.isWhite()) ||
                      (BoardUtils.LAST_COLUMN.get(this.position) && this.alliance.isBlack()))) {
                if (board.getTile(candidateDestinationCoordinate).isOccupied()) {
                    final Piece pieceCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.alliance != pieceCandidate.getAlliance()) {
                        // Add an attack move
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private boolean canJump(int currentCandidateOffset) {
        return currentCandidateOffset == 16 && this.isFirstMove &&
                (BoardUtils.SECOND_ROW.get(this.position) && this.alliance.isBlack()) ||
                (BoardUtils.SEVENTH_ROW.get(this.position) && this.alliance.isWhite());
    }


    @Override
    public Pawn movePiece(final Move move) {
        return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getAlliance());
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}
