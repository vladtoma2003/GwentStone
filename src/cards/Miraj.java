package cards;

import fileio.CardInput;

public class Miraj extends Minion {

    public Miraj(final CardInput input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(false);
    }

    /**
     * Swaps his health with the given card's health
     *
     * @param attacekd
     */
    @Override
    public void useAbility(final Minion attacekd) {
        int newHealth = attacekd.getHealth();
        attacekd.setHealth(this.getHealth());
        this.setHealth(newHealth);
        this.setHasAttacked(true);
    }

    public Miraj(final Minion input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(false);
    }

    public Miraj() {

    }
}
