package com.antumbrastation.phantomchess.csp;

import com.antumbrastation.phantomchess.board.PieceType;

import java.util.Map;
import java.util.Set;

public interface StateRestraint {

    boolean satisfied(Map<Integer, PieceType> potentialHiddenState);

    Set<Integer> boundPieces(Set<Integer> bound);

}
