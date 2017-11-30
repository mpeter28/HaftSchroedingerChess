package com.antumbrastation.schrodingerchess.csp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class AndConstraintTest {

    @Test
    public void bothTrueTest() {
        AndConstraint constraint = new AndConstraint(new TrueConstraint(), new TrueConstraint());
        Assert.assertTrue(constraint.satisfied(new HashMap<>()));
    }

    @Test
    public void leftFalseTest() {
        AndConstraint constraint = new AndConstraint(new FalseConstraint(), new TrueConstraint());
        Assert.assertFalse(constraint.satisfied(new HashMap<>()));
    }

    @Test
    public void rightFalseTest() {
        AndConstraint constraint = new AndConstraint(new TrueConstraint(), new FalseConstraint());
        Assert.assertFalse(constraint.satisfied(new HashMap<>()));
    }

}
