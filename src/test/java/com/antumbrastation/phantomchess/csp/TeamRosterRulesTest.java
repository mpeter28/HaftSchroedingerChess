package com.antumbrastation.phantomchess.csp;

import com.antumbrastation.phantomchess.board.PieceType;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TeamRosterRulesTest {

    @Test
    public void isSatisfiedTest() {
        TeamRosterRules rosterRules = new TeamRosterRules();

        Map<Integer, PieceType> bindings = new HashMap<>();
        bindings.put(1, PieceType.Rook);
        bindings.put(2, PieceType.Rook);
        bindings.put(3, PieceType.Bishop);
        bindings.put(4, PieceType.Bishop);
        bindings.put(5, PieceType.Knight);
        bindings.put(6, PieceType.Knight);
        bindings.put(7, PieceType.King);
        bindings.put(8, PieceType.Queen);
        bindings.put(16, PieceType.Rook);
        bindings.put(17, PieceType.Rook);
        bindings.put(18, PieceType.Bishop);
        bindings.put(19, PieceType.Bishop);
        bindings.put(20, PieceType.Knight);
        bindings.put(21, PieceType.Knight);
        bindings.put(22, PieceType.King);
        bindings.put(23, PieceType.Queen);

        Assert.assertTrue(rosterRules.satisfied(bindings));
    }

    @Test
    public void tooManyKingsTest() {
        TeamRosterRules rosterRules = new TeamRosterRules();

        Map<Integer, PieceType> bindings = new HashMap<>();
        bindings.put(16, PieceType.King);
        bindings.put(18, PieceType.King);

        Assert.assertFalse(rosterRules.satisfied(bindings));
    }

    @Test
    public void tooManyBishopsTest() {
        TeamRosterRules rosterRules = new TeamRosterRules();

        Map<Integer, PieceType> bindings = new HashMap<>();
        bindings.put(5, PieceType.Bishop);
        bindings.put(6, PieceType.Bishop);
        bindings.put(7, PieceType.Bishop);

        Assert.assertFalse(rosterRules.satisfied(bindings));
    }

    @Test
    public void tooManyKnightsTest() {
        TeamRosterRules rosterRules = new TeamRosterRules();

        Map<Integer, PieceType> bindings = new HashMap<>();
        bindings.put(20, PieceType.Knight);
        bindings.put(21, PieceType.Knight);
        bindings.put(22, PieceType.Knight);

        Assert.assertFalse(rosterRules.satisfied(bindings));
    }

    @Test
    public void tooManyRooksTest() {
        TeamRosterRules rosterRules = new TeamRosterRules();

        Map<Integer, PieceType> bindings = new HashMap<>();
        bindings.put(5, PieceType.Rook);
        bindings.put(6, PieceType.Rook);
        bindings.put(7, PieceType.Rook);

        Assert.assertFalse(rosterRules.satisfied(bindings));
    }

    @Test
    public void tooManyQueensTest() {
        TeamRosterRules rosterRules = new TeamRosterRules();

        Map<Integer, PieceType> bindings = new HashMap<>();
        bindings.put(16, PieceType.Queen);
        bindings.put(18, PieceType.Queen);

        Assert.assertFalse(rosterRules.satisfied(bindings));
    }

}
