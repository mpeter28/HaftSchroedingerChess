package com.antumbrastation.schrodingerchess.csp;

import com.antumbrastation.schrodingerchess.board.PieceType;

import java.util.*;

public class RestraintUtils {

    public static boolean satisfiable(StateRestraint restraint) {
        Map<Integer, PieceType> bindings = new HashMap<>();
        Set<Integer> requiredVariables = restraint.boundPieces(new HashSet<>());

        return dfs(restraint, bindings, new LinkedList<>(requiredVariables), 0);
    }

    public static boolean dfs(StateRestraint restraint,
                              Map<Integer, PieceType> bindings,
                              List<Integer> requiredVariables,
                              int bindingIndex) {
        if (bindingIndex >= requiredVariables.size())
            return restraint.satisfied(bindings);

        int pieceNumber = requiredVariables.get(bindingIndex);
        for (PieceType type : PieceType.values()) {
            bindings.put(pieceNumber, type);

            if (dfs(restraint, bindings, requiredVariables, bindingIndex + 1))
                return true;
        }

        return false;
    }

}
