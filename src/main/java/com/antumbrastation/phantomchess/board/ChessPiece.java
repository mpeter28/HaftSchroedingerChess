package com.antumbrastation.phantomchess.board;

public class ChessPiece {

    private int pieceNumber;
    private boolean isPawn;

    private int row;
    private int column;

    public ChessPiece(int pieceNumber, boolean isPawn, int row, int column) {
        this.pieceNumber = pieceNumber;
        this.isPawn = isPawn;
        this.row = row;
        this.column = column;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public boolean isPawn() {
        return isPawn;
    }

    public void setPawn(boolean pawn) {
        isPawn = pawn;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
