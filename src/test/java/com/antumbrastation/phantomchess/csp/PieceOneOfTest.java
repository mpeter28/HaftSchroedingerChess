package com.antumbrastation.phantomchess.csp;

import com.antumbrastation.phantomchess.board.PieceType;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PieceOneOfTest {

    @Test
    public void inRangeTest() {
        PieceOneOf oneOf = new PieceOneOf(13);
        oneOf.addType(PieceType.Rook);
        oneOf.addType(PieceType.Queen);

        Map<Integer, PieceType> bindings = new HashMap<>();
        bindings.put(13, PieceType.Rook);

        Assert.assertTrue(oneOf.satisfied(bindings));
    }

    @Test
    public void outOfRangeTest() {
        PieceOneOf oneOf = new PieceOneOf(13);
        oneOf.addType(PieceType.Rook);
        oneOf.addType(PieceType.Queen);

        Map<Integer, PieceType> bindings = new HashMap<>();
        bindings.put(13, PieceType.Knight);

        Assert.assertFalse(oneOf.satisfied(bindings));
    }

    @Test
    public void bindingListTest() {
        PieceOneOf oneOf = new PieceOneOf(13);

        HashSet<Integer> boundVariables = new HashSet<>();
        boundVariables.add(5);
        oneOf.boundPieces(boundVariables);

        Assert.assertTrue(boundVariables.contains(5));
        Assert.assertTrue(boundVariables.contains(13));
    }
}
