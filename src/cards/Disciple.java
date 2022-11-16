package cards;

import fileio.CardInput;

public class Disciple extends Minion {
    public Disciple(final CardInput input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    /**
     * Gives a given card +2 health
     *
     * @param attacked
     */
    @Override
    public void useAbility(final Minion attacked) {
        attacked.setHealth(attacked.getHealth() + 2);
        this.setHasAttacked(true);
    }

    public Disciple(final Minion input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    public Disciple() {

    }
}
