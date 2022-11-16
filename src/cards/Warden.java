package cards;

import fileio.CardInput;

public class Warden extends Minion {
    public Warden(final CardInput input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(true);
    }

    public Warden(final Minion input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(true);
    }

    public Warden() {

    }
}
