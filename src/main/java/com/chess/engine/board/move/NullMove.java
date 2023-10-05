package com.chess.engine.board.move;

import com.chess.engine.board.Board;

public final class NullMove extends Move {

    public NullMove() {
        super(null, null, -1);
    }

    @Override
    public Board execute() {
        throw new RuntimeException("Cannot execute null move!!!");
    }
}
