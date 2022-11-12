package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Sentinel extends Minion {
    public Sentinel(CardInput input) {
        super(input);
        setRow(0);
    }

    public Sentinel(Minion input) {
        super(input);
        setRow(0);
    }

//    public Sentinel(Sentinel s) {
//        Sentinel sen = new Sentinel();
//        sen.setName(s.getName());
//        sen.setMana(s.getMana());
//        sen.setDescription(s.getDescription());
//        sen.setColors(s.getColors());
//        sen.setHealth(s.getHealth());
//        sen.setAttackDamage(s.getAttackDamage());
//        sen.setRow(s.getRow());
//    }

    public Sentinel() {

    }
}
