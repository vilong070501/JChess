package com.engine.board.tile;

import com.engine.board.tile.Tile;
import com.engine.pieces.Piece;

public final class OccupiedTile extends Tile {

    private final Piece currentPiece;

    OccupiedTile(int coordinate, Piece currentPiece) {
        super(coordinate);
        this.currentPiece = currentPiece;
    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.currentPiece;
    }

    public String toString() { return getPiece().getAlliance().isBlack() ? getPiece().toString().toLowerCase() :
            getPiece().toString().toUpperCase(); }
}
