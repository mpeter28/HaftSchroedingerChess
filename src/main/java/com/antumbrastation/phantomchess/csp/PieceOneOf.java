package com.antumbrastation.phantomchess.csp;

import com.antumbrastation.phantomchess.board.PieceType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PieceOneOf implements StateRestraint {

    private int pieceNumber;
    private List<PieceType> acceptablePieceTypes;

    public PieceOneOf(int pieceNumber) {
        this.pieceNumber = pieceNumber;

        acceptablePieceTypes = new LinkedList<>();
    }

    public PieceOneOf addType(PieceType type) {
        acceptablePieceTypes.add(type);
        return this;
    }

    @Override
    public boolean satisfied(Map<Integer, PieceType> potentialHiddenState) {
        PieceType type = potentialHiddenState.get(pieceNumber);

        return null != type && acceptablePieceTypes.contains(type);
    }

    @Override
    public Set<Integer> boundPieces(Set<Integer> bound) {
        bound.add(pieceNumber);
        return bound;
    }

    public String toString() {
        return "{" + pieceNumber + " in " + acceptablePieceTypes.toString() + "}";
    }
}
