package com.chess.engine.board.move;

import com.chess.engine.board.Board;

public final class NullMove extends Move {

    public NullMove() {
        super(null, -1);
    }


    @Override
    public int getCurrentCoordinate() {
        return -1;
    }

    @Override
    public int getDestinationCoordinate() {
        return -1;
    }

    @Override
    public String toString() {
        return "Null Move";
    }

    @Override
    public Board execute() {
        throw new RuntimeException("Cannot execute null move!!!");
    }

}
