package com.antumbrastation.schrodingerchess.board.moves;

import com.antumbrastation.schrodingerchess.board.ChessPiece;
import com.antumbrastation.schrodingerchess.board.PhantomChessState;
import com.antumbrastation.schrodingerchess.csp.StateRestraint;

public class CapturePiece extends BaseMove {

    private int pieceNumber;
    private int deadPieceNumber;

    private int newRow;
    private int newColumn;

    public CapturePiece(StateRestraint newWaveFunction,
                     int pieceNumber, int deadPieceNumber, int newRow, int newColumn) {
        super(pieceNumber + "x" + newRow + "," + newColumn, newWaveFunction);

        this.pieceNumber = pieceNumber;
        this.deadPieceNumber = deadPieceNumber;
        this.newRow = newRow;
        this.newColumn = newColumn;
    }

    @Override
    public void resolve(PhantomChessState resolveOn) {
        super.resolve(resolveOn);

        for (ChessPiece piece : resolveOn.getWhitePieces()) {
            if (pieceNumber == piece.getPieceNumber()) {
                piece.setRow(newRow);
                piece.setColumn(newColumn);
            }
        }

        for (ChessPiece piece : resolveOn.getBlackPieces()) {
            if (pieceNumber == piece.getPieceNumber()) {
                piece.setRow(newRow);
                piece.setColumn(newColumn);
            }
        }

        for (int index = 0; index < resolveOn.getWhitePieces().size(); index++) {
            if (deadPieceNumber == resolveOn.getWhitePieces().get(index).getPieceNumber()) {
                resolveOn.getWhitePieces().remove(index);
                index--;
            }
        }

        for (int index = 0; index < resolveOn.getBlackPieces().size(); index++) {
            if (deadPieceNumber == resolveOn.getBlackPieces().get(index).getPieceNumber()) {
                resolveOn.getBlackPieces().remove(index);
                index--;
            }
        }
    }
}
