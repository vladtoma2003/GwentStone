package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Warden extends Minion{
    public Warden(CardInput input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(true);
    }

    public Warden(Minion input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(true);
    }

//    public Warden(Warden w) {
//        Warden war = new Warden();
//        war.setName(w.getName());
//        war.setMana(w.getMana());
//        war.setDescription(w.getDescription());
//        war.setColors(w.getColors());
//        war.setHealth(w.getHealth());
//        war.setAttackDamage(w.getAttackDamage());
//        war.setRow(w.getRow());
//    }

    public Warden() {

    }
}
