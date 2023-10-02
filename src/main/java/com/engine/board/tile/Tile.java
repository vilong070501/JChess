package com.engine.board.tile;

import com.engine.board.BoardUtils;
import com.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the abstract class which represent a tile.
 * It only contains the tile's coordinate and a list of empty tile which we can use
 * This is an example of an immutable class in java, because we cannot modify its attribute,
 * and the only way to create a tile is to call the function createTile()
 */
public abstract class Tile {

    protected final int coordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTile();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTile() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < BoardUtils.NUMBER_OF_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int coordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(coordinate, piece) : EMPTY_TILES.get(coordinate);
    }

    protected Tile(int coordinate) {
        this.coordinate = coordinate;
    }

    public abstract boolean isOccupied();
    public abstract Piece getPiece();

}
