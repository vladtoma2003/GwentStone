package Cards;

import fileio.CardInput;

public class Miraj extends Minion {

    public Miraj(final CardInput input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(false);
    }

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
