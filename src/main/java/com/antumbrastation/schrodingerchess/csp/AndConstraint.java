package com.antumbrastation.schrodingerchess.csp;

import com.antumbrastation.schrodingerchess.board.PieceType;

import java.util.Map;
import java.util.Set;

public class AndConstraint implements StateRestraint {

    private StateRestraint restraintOne;
    private StateRestraint restraintTwo;

    public AndConstraint(StateRestraint restraintOne, StateRestraint restraintTwo) {
        this.restraintOne = restraintOne;
        this.restraintTwo = restraintTwo;
    }

    @Override
    public boolean satisfied(Map<Integer, PieceType> potentialHiddenState) {
        return restraintOne.satisfied(potentialHiddenState) && restraintTwo.satisfied(potentialHiddenState);
    }

    @Override
    public Set<Integer> boundPieces(Set<Integer> bound) {
        restraintOne.boundPieces(bound);
        restraintTwo.boundPieces(bound);
        return bound;
    }

    public String toString() {
        return restraintOne.toString() + " AND " + restraintTwo.toString();
    }
}
