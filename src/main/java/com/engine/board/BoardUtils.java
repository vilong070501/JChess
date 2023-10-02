package com.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] LAST_COLUMN = initColumn(7);
    public static final boolean[] SECOND_ROW = initRow(8); // The number of case of first white pawn
    public static final boolean[] SEVENTH_ROW = initRow(48); // The number of case of first black pawn

    public static final int NUMBER_OF_TILES = 64;
    public static final int NUMBER_OF_TILES_PER_ROW = 8;

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate BoardUnits !");
    }

    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[NUMBER_OF_TILES];
        do {
            column[columnNumber] = true;
            columnNumber += NUMBER_OF_TILES_PER_ROW;
        } while (columnNumber < NUMBER_OF_TILES);
        return column;
    }

    private static boolean[] initRow(int rowNumber) {
        final boolean[] rows = new boolean[NUMBER_OF_TILES];
        do {
            rows[rowNumber] = true;
            rowNumber++;
        } while (rowNumber % NUMBER_OF_TILES_PER_ROW != 0);
        return rows;
    }

    public static boolean isValidTileCoordinate(int candidateDestinationCoordinate) {
        return candidateDestinationCoordinate >= 0 && candidateDestinationCoordinate < NUMBER_OF_TILES;
    }
}
