package cards;

import fileio.CardInput;

public class Berserker extends Minion {
    public Berserker(final CardInput input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    public Berserker(final Minion input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    public Berserker() {

    }
}
