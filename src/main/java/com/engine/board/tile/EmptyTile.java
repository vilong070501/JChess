package com.engine.board.tile;

import com.engine.board.tile.Tile;
import com.engine.pieces.Piece;

public final class EmptyTile extends Tile {

    EmptyTile(final int coordinate) {
        super(coordinate);
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    public String toString() { return "-"; }
}
