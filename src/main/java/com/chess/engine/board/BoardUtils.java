package com.chess.engine.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoardUtils {

    public static final List<Boolean> FIRST_COLUMN = initColumn(0);
    public static final List<Boolean> SECOND_COLUMN = initColumn(1);
    public static final List<Boolean> THIRD_COLUMN = initColumn(2);
    public static final List<Boolean> FOURTH_COLUMN = initColumn(3);
    public static final List<Boolean> FIFTH_COLUMN = initColumn(4);
    public static final List<Boolean> SIXTH_COLUMN = initColumn(5);
    public static final List<Boolean> SEVENTH_COLUMN = initColumn(6);
    public static final List<Boolean> LAST_COLUMN = initColumn(7);
    public static final List<Boolean> FIRST_ROW = initRow(0);
    public static final List<Boolean> SECOND_ROW = initRow(8);
    public static final List<Boolean> THIRD_ROW = initRow(16);
    public static final List<Boolean> FOURTH_ROW = initRow(24);
    public static final List<Boolean> FIFTH_ROW = initRow(32);
    public static final List<Boolean> SIXTH_ROW = initRow(40);
    public static final List<Boolean> SEVENTH_ROW = initRow(48);
    public static final List<Boolean> LAST_ROW = initRow(56);

    public static final int NUMBER_OF_TILES = 64;
    public static final int NUMBER_OF_TILES_PER_ROW = 8;

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate BoardUnits !");
    }

    private static List<Boolean> initColumn(int columnNumber) {
        final Boolean[] column = new Boolean[NUMBER_OF_TILES];
        Arrays.fill(column, false);
        do {
            column[columnNumber] = true;
            columnNumber += NUMBER_OF_TILES_PER_ROW;
        } while (columnNumber < NUMBER_OF_TILES);
        return List.of(column);
    }

    private static List<Boolean> initRow(int rowNumber) {
        final Boolean[] rows = new Boolean[NUMBER_OF_TILES];
        Arrays.fill(rows, false);
        do {
            rows[rowNumber] = true;
            rowNumber++;
        } while (rowNumber % NUMBER_OF_TILES_PER_ROW != 0);
        return List.of(rows);
    }

    public static boolean isValidTileCoordinate(int candidateDestinationCoordinate) {
        return candidateDestinationCoordinate >= 0 && candidateDestinationCoordinate < NUMBER_OF_TILES;
    }
}
