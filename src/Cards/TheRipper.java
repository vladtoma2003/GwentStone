package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class TheRipper extends Minion {

    public TheRipper(CardInput input) {
        super(input);
        setRow(1);
    }

    public TheRipper(Minion input) {
        super(input);
        setRow(1);
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