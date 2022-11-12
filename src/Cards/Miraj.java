package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Miraj extends Minion{

    public Miraj(CardInput input) {
        super(input);
        setRow(1);
    }
    public Miraj(Minion input) {
        super(input);
        setRow(1);
    }

//    public Miraj(Miraj m) {
//        Miraj mir = new Miraj();
//        mir.setName(m.getName());
//        mir.setMana(m.getMana());
//        mir.setDescription(m.getDescription());
//        mir.setColors(m.getColors());
//        mir.setHealth(m.getHealth());
//        mir.setAttackDamage(m.getAttackDamage());
//        mir.setRow(m.getRow());
//    }

    public Miraj() {

    }
}
