package com.antumbrastation.phantomchess.board;

import com.antumbrastation.phantomchess.board.moves.CapturePiece;
import com.antumbrastation.phantomchess.board.moves.MovePiece;
import com.antumbrastation.phantomchess.csp.AndConstraint;
import com.antumbrastation.phantomchess.csp.PieceOneOf;
import com.antumbrastation.phantomchess.csp.RestraintUtils;
import com.antumbrastation.phantomchess.csp.StateRestraint;

import java.util.LinkedList;
import java.util.List;

public class PhantomChessState {

    private List<String> gameRecord;
    private StateRestraint waveFunction;

    private boolean whiteToPlay;

    private List<ChessPiece> whitePieces;
    private List<ChessPiece> blackPieces;

    public PhantomChessState(List<String> gameRecord,
                             StateRestraint waveFunction,
                             List<ChessPiece> whitePieces,
                             List<ChessPiece> blackPieces) {
        this.gameRecord = gameRecord;
        this.waveFunction = waveFunction;
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
    }

    public List<PhantomChessMove> validMoves() {
        List<PhantomChessMove> validMoves = new LinkedList<>();

        List<ChessPiece> movingPieces = whitePieces;
        List<ChessPiece> notMovingPieces = blackPieces;
        if (!whiteToPlay) {
            List<ChessPiece> temp = notMovingPieces;
            notMovingPieces = movingPieces;
            movingPieces = temp;
        }

        // pawn stuff first
        for (ChessPiece movingPiece : movingPieces) {
            if (movingPiece.isPawn()) {
                pawnWalk(movingPiece, waveFunction, movingPieces, notMovingPieces, whiteToPlay ? 1 : -1, validMoves);
            }
        }

        // knight move
        for (ChessPiece movingPiece : movingPieces) {
            if (!movingPiece.isPawn()) {
                StateRestraint knightRestraint = new AndConstraint(
                        waveFunction,
                        new PieceOneOf(movingPiece.getPieceNumber())
                                .addType(PieceType.Knight)
                );

                if (RestraintUtils.satisfiable(knightRestraint)) {
                    jumpPath(movingPiece, knightRestraint, movingPieces, notMovingPieces, 1, 2, validMoves);
                    jumpPath(movingPiece, knightRestraint, movingPieces, notMovingPieces, 1, -2, validMoves);
                    jumpPath(movingPiece, knightRestraint, movingPieces, notMovingPieces, -1, 2, validMoves);
                    jumpPath(movingPiece, knightRestraint, movingPieces, notMovingPieces, -1, -2, validMoves);
                    jumpPath(movingPiece, knightRestraint, movingPieces, notMovingPieces, 2, 1, validMoves);
                    jumpPath(movingPiece, knightRestraint, movingPieces, notMovingPieces, -2, 1, validMoves);
                    jumpPath(movingPiece, knightRestraint, movingPieces, notMovingPieces, 2, -1, validMoves);
                    jumpPath(movingPiece, knightRestraint, movingPieces, notMovingPieces, -2, -1, validMoves);
                }
            }
        }

        // king move
        for (ChessPiece movingPiece : movingPieces) {
            if (!movingPiece.isPawn()) {
                StateRestraint rookKingRestraint = new AndConstraint(
                        waveFunction,
                        new PieceOneOf(movingPiece.getPieceNumber())
                                .addType(PieceType.Rook)
                                .addType(PieceType.Queen)
                                .addType(PieceType.King)
                );

                if (RestraintUtils.satisfiable(rookKingRestraint)) {
                    jumpPath(movingPiece, rookKingRestraint, movingPieces, notMovingPieces, 1, 0, validMoves);
                    jumpPath(movingPiece, rookKingRestraint, movingPieces, notMovingPieces, -1, 0, validMoves);
                    jumpPath(movingPiece, rookKingRestraint, movingPieces, notMovingPieces, 0, 1, validMoves);
                    jumpPath(movingPiece, rookKingRestraint, movingPieces, notMovingPieces, 0, -1, validMoves);
                }

                StateRestraint bishopKingRestraint = new AndConstraint(
                        waveFunction,
                        new PieceOneOf(movingPiece.getPieceNumber())
                                .addType(PieceType.Bishop)
                                .addType(PieceType.Queen)
                                .addType(PieceType.King)
                );

                if (RestraintUtils.satisfiable(bishopKingRestraint)) {
                    jumpPath(movingPiece, bishopKingRestraint, movingPieces, notMovingPieces, 1, 1, validMoves);
                    jumpPath(movingPiece, bishopKingRestraint, movingPieces, notMovingPieces, -1, -1, validMoves);
                    jumpPath(movingPiece, bishopKingRestraint, movingPieces, notMovingPieces, -1, 1, validMoves);
                    jumpPath(movingPiece, bishopKingRestraint, movingPieces, notMovingPieces, 1, -1, validMoves);
                }
            }
        }

        // rook move
        for (ChessPiece movingPiece : movingPieces) {
            if (!movingPiece.isPawn()) {
                StateRestraint rookRestraint = new AndConstraint(
                        waveFunction,
                        new PieceOneOf(movingPiece.getPieceNumber())
                                .addType(PieceType.Rook)
                                .addType(PieceType.Queen)
                );

                if (RestraintUtils.satisfiable(rookRestraint)) {
                    walkPath(movingPiece, rookRestraint, movingPieces, notMovingPieces, 1, 0, validMoves);
                    walkPath(movingPiece, rookRestraint, movingPieces, notMovingPieces, -1, 0, validMoves);
                    walkPath(movingPiece, rookRestraint, movingPieces, notMovingPieces, 0, -1, validMoves);
                    walkPath(movingPiece, rookRestraint, movingPieces, notMovingPieces, 0, -1, validMoves);
                }
            }
        }

        // diagonal move
        for (ChessPiece movingPiece : movingPieces) {
            if (!movingPiece.isPawn()) {
                StateRestraint diagonalRestraint = new AndConstraint(
                        waveFunction,
                        new PieceOneOf(movingPiece.getPieceNumber())
                                .addType(PieceType.Bishop)
                                .addType(PieceType.Queen)
                );

                if (RestraintUtils.satisfiable(diagonalRestraint)) {
                    walkPath(movingPiece, diagonalRestraint, movingPieces, notMovingPieces, 1, 1, validMoves);
                    walkPath(movingPiece, diagonalRestraint, movingPieces, notMovingPieces, -1, 1, validMoves);
                    walkPath(movingPiece, diagonalRestraint, movingPieces, notMovingPieces, 1, -1, validMoves);
                    walkPath(movingPiece, diagonalRestraint, movingPieces, notMovingPieces, -1, -1, validMoves);
                }
            }
        }

        return validMoves;
    }

    private void pawnWalk(ChessPiece movingPiece,
                          StateRestraint newRestraint,
                          List<ChessPiece> movingPieces, List<ChessPiece> enemyPieces,
                          int rowD,
                          List<PhantomChessMove> validMoves) {
        int row = movingPiece.getRow() + rowD;
        int column = movingPiece.getColumn();

        if (row >=8 || column >= 8 || row < 0 || column < 0)
            return;

        for (ChessPiece blocker : movingPieces) {
            if (blocker.getRow() == row && blocker.getColumn() == column)
                return;
        }

        for (ChessPiece enemy : enemyPieces) {
            if (enemy.getRow() == row && enemy.getColumn() == column) {
                return;
            }
        }

        validMoves.add(new MovePiece(newRestraint, movingPiece.getPieceNumber(), row, column));
    }

    private void jumpPath(ChessPiece movingPiece,
                          StateRestraint newRestraint,
                          List<ChessPiece> movingPieces, List<ChessPiece> enemyPieces,
                          int rowD, int columnD,
                          List<PhantomChessMove> validMoves) {
        int row = movingPiece.getRow() + rowD;
        int column = movingPiece.getColumn() + columnD;

        if (row >=8 || column >= 8 || row < 0 || column < 0)
            return;

        for (ChessPiece blocker : movingPieces) {
            if (blocker.getRow() == row && blocker.getColumn() == column)
                return;
        }

        for (ChessPiece enemy : enemyPieces) {
            if (enemy.getRow() == row && enemy.getColumn() == column) {
                validMoves.add(new CapturePiece(
                        newRestraint,
                        movingPiece.getPieceNumber(),
                        enemy.getPieceNumber(),
                        row,
                        column
                ));
                return;
            }
        }

        validMoves.add(new MovePiece(newRestraint, movingPiece.getPieceNumber(), row, column));
    }

    private void walkPath(ChessPiece movingPiece,
                          StateRestraint newRestraint,
                          List<ChessPiece> movingPieces, List<ChessPiece> enemyPieces,
                          int rowD, int columnD,
                          List<PhantomChessMove> validMoves) {
        int row = movingPiece.getRow() + rowD;
        int column = movingPiece.getColumn() + columnD;

        for (ChessPiece blocker : movingPieces) {
            if (blocker.getRow() == row && blocker.getColumn() == column)
                return;
        }

        for (ChessPiece enemy : enemyPieces) {
            if (enemy.getRow() == row && enemy.getColumn() == column)
                return;
        }

        while (true) {
            row += rowD;
            column += columnD;

            if (row >=8 || column >= 8 || row < 0 || column < 0)
                return;

            for (ChessPiece blocker : movingPieces) {
                if (blocker.getRow() == row && blocker.getColumn() == column)
                    return;
            }

            for (ChessPiece enemy : enemyPieces) {
                if (enemy.getRow() == row && enemy.getColumn() == column) {
                    validMoves.add(new CapturePiece(
                            newRestraint,
                            movingPiece.getPieceNumber(),
                            enemy.getPieceNumber(),
                            row,
                            column
                    ));
                    return;
                }
            }

            validMoves.add(new MovePiece(newRestraint, movingPiece.getPieceNumber(), row, column));
        }
    }

    public PhantomChessState duplicate() {
        return this;
    }

    public List<String> getGameRecord() {
        return gameRecord;
    }

    public void setGameRecord(List<String> gameRecord) {
        this.gameRecord = gameRecord;
    }

    public StateRestraint getWaveFunction() {
        return waveFunction;
    }

    public void setWaveFunction(StateRestraint waveFunction) {
        this.waveFunction = waveFunction;
    }

    public boolean isWhiteToPlay() {
        return whiteToPlay;
    }

    public void setWhiteToPlay(boolean whiteToPlay) {
        this.whiteToPlay = whiteToPlay;
    }

    public List<ChessPiece> getWhitePieces() {
        return whitePieces;
    }

    public void setWhitePieces(List<ChessPiece> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public List<ChessPiece> getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(List<ChessPiece> blackPieces) {
        this.blackPieces = blackPieces;
    }

}
