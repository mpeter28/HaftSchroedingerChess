package com.antumbrastation.schrodingerchess.csp;

import com.antumbrastation.schrodingerchess.board.PieceType;

import java.util.Map;
import java.util.Set;

public class TeamRosterRules implements StateRestraint {

    @Override
    public boolean satisfied(Map<Integer, PieceType> potentialHiddenState) {
        return checkTeam(potentialHiddenState, true) && checkTeam(potentialHiddenState, false);
    }

    private boolean checkTeam(Map<Integer, PieceType> potentialHiddenState, boolean isWhite) {
        int kings = 0;
        int queens = 0;
        int rooks = 0;
        int knights = 0;
        int bishops = 0;

        for (Integer pieceNumber : potentialHiddenState.keySet()) {
            if (isWhite && pieceNumber < 16 || !isWhite && pieceNumber >= 16) {
                switch (potentialHiddenState.get(pieceNumber)) {
                    case Bishop:
                        bishops++;
                        break;
                    case King:
                        kings++;
                        break;
                    case Queen:
                        queens++;
                        break;
                    case Rook:
                        rooks++;
                        break;
                    case Knight:
                        knights++;
                        break;
                }
            }
        }

        return kings <= 1 && queens <= 1 && rooks <= 2 && knights <= 2 && bishops <= 2;
    }

    @Override
    public Set<Integer> boundPieces(Set<Integer> bound) {
        return bound;
    }

    public String toString() {
        return "{ROSTER RULES}";
    }
}
