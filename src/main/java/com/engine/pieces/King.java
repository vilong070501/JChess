package com.engine.pieces;

import com.engine.Alliance;
import com.engine.board.Board;
import com.engine.board.BoardUtils;
import com.engine.board.move.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(int position, Alliance alliance) {
        super(PieceType.KING, position, alliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.position;

            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if (isFirstColumnExclusion(this.position, candidateCoordinateOffset) ||
                    isLastColumnExclusion(this.position, candidateCoordinateOffset)) {
                    continue;
                }

                buildMove(board, legalMoves, candidateDestinationCoordinate);
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] &&
                (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
    }

    private static boolean isLastColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.LAST_COLUMN[currentPosition] &&
                (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
}
