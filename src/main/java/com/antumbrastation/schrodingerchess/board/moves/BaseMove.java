package com.antumbrastation.schrodingerchess.board.moves;

import com.antumbrastation.schrodingerchess.board.PhantomChessMove;
import com.antumbrastation.schrodingerchess.board.PhantomChessState;
import com.antumbrastation.schrodingerchess.csp.StateRestraint;

public abstract class BaseMove implements PhantomChessMove {

    private String description;
    private StateRestraint newWaveFunction;

    public BaseMove(String description, StateRestraint newWaveFunction) {
        this.description = description;
        this.newWaveFunction = newWaveFunction;
    }

    @Override
    public void resolve(PhantomChessState resolveOn) {
        resolveOn.setWhiteToPlay(!resolveOn.isWhiteToPlay());

        resolveOn.getGameRecord().add(description);
        resolveOn.setWaveFunction(newWaveFunction);
    }

    @Override
    public String toString() {
        return description;
    }
}
