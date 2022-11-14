package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

import java.util.ArrayList;

public class Berserker extends Minion {
    public Berserker(CardInput input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

    public Berserker(Minion input) {
        super(input);
        setRow(0);
        setFrozen(false);
        setTank(false);
    }

//    public Berserker(Berserker b) {
//        Berserker ber = new Berserker();
//        ber.setName(b.getName());
//        ber.setMana(b.getMana());
//        ber.setDescription(b.getDescription());
//        ber.setColors(b.getColors());
//        ber.setHealth(b.getHealth());
//        ber.setAttackDamage(b.getAttackDamage());
//        ber.setRow(b.getRow());
//    }

    public Berserker() {

    }
}
