package Cards;

import fileio.CardInput;

public class Disciple extends Minion {
    public Disciple(CardInput input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

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
