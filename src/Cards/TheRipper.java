package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class TheRipper extends Minion {

    public TheRipper(CardInput input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(false);
    }

    @Override
    public void useAbility(Minion attacked) {
        attacked.setAttackDamage(attacked.getAttackDamage() - 3);
        if(attacked.getAttackDamage() < 0) {
            attacked.setAttackDamage(0);
        }
        this.setHasAttacked(true);
    }

    public TheRipper(Minion input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(false);
    }

//    public TheRipper(TheRipper r) {
//        TheRipper rep = new TheRipper();
//        rep.setName(r.getName());
//        rep.setMana(r.getMana());
//        rep.setDescription(r.getDescription());
//        rep.setColors(r.getColors());
//        rep.setHealth(r.getHealth());
//        rep.setAttackDamage(r.getAttackDamage());
//        rep.setRow(r.getRow());
//    }


    public TheRipper() {

    }

}
