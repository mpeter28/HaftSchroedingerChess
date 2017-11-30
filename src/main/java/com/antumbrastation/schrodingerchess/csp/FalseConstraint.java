package com.antumbrastation.schrodingerchess.csp;

import com.antumbrastation.schrodingerchess.board.PieceType;

import java.util.Map;
import java.util.Set;

public class FalseConstraint implements StateRestraint {

    @Override
    public boolean satisfied(Map<Integer, PieceType> potentialHiddenState) {
        return false;
    }

    @Override
    public Set<Integer> boundPieces(Set<Integer> bound) {
        return bound;
    }

    public String toString() {
        return "{FALSE}";
    }
}
