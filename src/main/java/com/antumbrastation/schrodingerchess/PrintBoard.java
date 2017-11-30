package com.antumbrastation.schrodingerchess;

import com.antumbrastation.schrodingerchess.board.ChessPiece;
import com.antumbrastation.schrodingerchess.board.PhantomChessState;

public class PrintBoard {

    public static String printBoard(PhantomChessState state) {
        StringBuilder board = new StringBuilder();

        board.append("  +--+--+--+--+--+--+--+--+\n");

        for (int row = 0; row < 8; row++) {
            board.append(row).append(" ");

            for (int column = 0; column < 8; column++) {
                board.append(square(row, column, state));
            }

            board.append("|\n");
            board.append("  +--+--+--+--+--+--+--+--+\n");
        }

        board.append("\n   0  1  2  3  4  5  6  7\n");

        return board.toString();
    }

    public static String square(int row, int column, PhantomChessState state) {
        for (ChessPiece whitePiece : state.getWhitePieces()) {
            if (row == whitePiece.getRow() && column == whitePiece.getColumn()) {
                if (whitePiece.isPawn())
                    return "| " + (char) 27 + "[33m&" + (char) 27 + "[39m";
                else
                    return "| " + (char) 27 + "[33m" + whitePiece.getPieceNumber() + (char) 27 + "[39m";
            }
        }

        for (ChessPiece blackPiece : state.getBlackPieces()) {
            if (row == blackPiece.getRow() && column == blackPiece.getColumn()) {
                if (blackPiece.isPawn())
                    return "| " + (char) 27 + "[35m&" + (char) 27 + "[39m";
                else
                    return "|" + (char) 27 + "[35m" + blackPiece.getPieceNumber() + (char) 27 + "[39m";
            }
        }

        return "|  ";
    }
}
