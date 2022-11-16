package cards;

import fileio.CardInput;

public class TheRipper extends Minion {

    public TheRipper(final CardInput input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(false);
    }

    @Override
    public void useAbility(final Minion attacked) {
        attacked.setAttackDamage(attacked.getAttackDamage() - 2);
        if (attacked.getAttackDamage() < 0) {
            attacked.setAttackDamage(0);
        }
        this.setHasAttacked(true);
    }

    public TheRipper(final Minion input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(false);
    }

    public TheRipper() {

    }

}
