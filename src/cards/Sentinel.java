package cards;

import fileio.CardInput;

public class Sentinel extends Minion {
    public Sentinel(final CardInput input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    public Sentinel(final Minion input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    public Sentinel() {

    }
}
