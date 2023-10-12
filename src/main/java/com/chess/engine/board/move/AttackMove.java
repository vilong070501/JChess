package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

public class AttackMove extends Move
{
    final Piece attackPiece;

    public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackPiece = attackPiece;
    }

    @Override
    public Board execute() {
        return null;
    }

    @Override
    public int hashCode() {
        return this.attackPiece.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AttackMove otherAttackMove)) {
            return false;
        }
        return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
    }

    @Override
    public Piece getAttackedPiece() {
        return this.attackPiece;
    }

    @Override
    public boolean isAttack() {
        return true;
    }


}
