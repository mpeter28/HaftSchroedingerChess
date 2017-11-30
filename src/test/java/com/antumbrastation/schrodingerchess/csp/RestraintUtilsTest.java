package com.antumbrastation.schrodingerchess.csp;

import com.antumbrastation.schrodingerchess.board.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class RestraintUtilsTest {

    @Test
    public void satisfiableTest() {
        StateRestraint restraint = new AndConstraint(
                new PieceOneOf(13)
                    .addType(PieceType.Rook)
                    .addType(PieceType.Queen),
                new PieceOneOf(18)
                    .addType(PieceType.Bishop)
                    .addType(PieceType.King)
        );

        Assert.assertTrue(RestraintUtils.satisfiable(restraint));
    }

    @Test
    public void unsatisfiableTest() {
        StateRestraint restraint = new AndConstraint(
                new PieceOneOf(13)
                        .addType(PieceType.Rook)
                        .addType(PieceType.Queen),
                new PieceOneOf(13)
                        .addType(PieceType.Bishop)
                        .addType(PieceType.King)
        );

        Assert.assertFalse(RestraintUtils.satisfiable(restraint));
    }

}
