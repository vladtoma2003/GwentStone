package cards;

import fileio.CardInput;

public class CursedOne extends Minion {
    public CursedOne(final CardInput input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    @Override
    public void useAbility(final Minion attacked) {
        int tempHealth = attacked.getHealth();
        attacked.setHealth(attacked.getAttackDamage());
        attacked.setAttackDamage(tempHealth);
        this.setHasAttacked(true);
    }

    public CursedOne(final Minion input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    public CursedOne() {
    }
}
