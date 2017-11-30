package com.antumbrastation.schrodingerchess;

import com.antumbrastation.schrodingerchess.board.ChessPiece;
import com.antumbrastation.schrodingerchess.board.PhantomChessMove;
import com.antumbrastation.schrodingerchess.board.PhantomChessState;
import com.antumbrastation.schrodingerchess.csp.TeamRosterRules;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CmdLine {

    public static void main(String[] args) {
        List<ChessPiece> whitePieces = new LinkedList<>();
        List<ChessPiece> blackPieces = new LinkedList<>();

        for (int column = 0; column < 8; column++) {
            ChessPiece pawn = new ChessPiece(8 + column, true, 1, column);
            ChessPiece piece = new ChessPiece(column, false, 0, column);
            whitePieces.add(pawn);
            whitePieces.add(piece);

            ChessPiece pawnBlack = new ChessPiece(16 + column, true, 6, column);
            ChessPiece pieceBlack = new ChessPiece(24 + column, false, 7, column);
            blackPieces.add(pawnBlack);
            blackPieces.add(pieceBlack);
        }

        PhantomChessState state = new PhantomChessState(
                new LinkedList<>(),
                new TeamRosterRules(),
                whitePieces,
                blackPieces
        );
        state.setWhiteToPlay(true);

        for (int turn = 0; turn < 30; turn++) {
            System.out.println(PrintBoard.printBoard(state));

            List<PhantomChessMove> moves = state.validMoves();
            System.out.print("Legal Moves :: ");
            moves.forEach(move -> System.out.print(move.toString() + "  "));

            System.out.println("\nWave Function Constraints :: " + state.getWaveFunction().toString());
            System.out.println();

            Scanner inputMove = new Scanner(System.in);
            boolean done = false;
            while (!done) {
                String chosenMove = inputMove.nextLine();
                for (PhantomChessMove move : moves) {
                    if (move.toString().equals(chosenMove)) {
                        move.resolve(state);

                        done = true;
                        break;
                    }
                }
            }
        }
    }
}
