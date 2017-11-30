package com.antumbrastation.schrodingerchess.board.moves;

import com.antumbrastation.schrodingerchess.board.ChessPiece;
import com.antumbrastation.schrodingerchess.board.PhantomChessState;
import com.antumbrastation.schrodingerchess.csp.StateRestraint;

public class PromotePawn extends BaseMove {

    private int pawnNumber;

    private int newRow;
    private int newColumn;

    public PromotePawn(StateRestraint newWaveFunction,
                     int pawnNumber, int newRow, int newColumn) {
        super(pawnNumber + " - " + newRow + "," + newColumn + "=?", newWaveFunction);

        this.pawnNumber = pawnNumber;
        this.newRow = newRow;
        this.newColumn = newColumn;
    }

    @Override
    public void resolve(PhantomChessState resolveOn) {
        super.resolve(resolveOn);

        for (ChessPiece piece : resolveOn.getWhitePieces()) {
            if (pawnNumber == piece.getPieceNumber()) {
                piece.setRow(newRow);
                piece.setColumn(newColumn);
                piece.setPawn(false);
            }
        }

        for (ChessPiece piece : resolveOn.getBlackPieces()) {
            if (pawnNumber == piece.getPieceNumber()) {
                piece.setRow(newRow);
                piece.setColumn(newColumn);
                piece.setPawn(false);
            }
        }
    }
}
