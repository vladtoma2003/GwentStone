package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class CursedOne extends Minion {
    public CursedOne(CardInput input) {
        super(input);
        setRow(0);
    }
    public CursedOne(Minion input) {
        super(input);
        setRow(0);
    }

//    public CursedOne(CursedOne c) {
//        CursedOne cur = new CursedOne();
//        cur.setName(c.getName());
//        cur.setMana(c.getMana());
//        cur.setDescription(c.getDescription());
//        cur.setColors(c.getColors());
//        cur.setHealth(c.getHealth());
//        cur.setAttackDamage(c.getAttackDamage());
//        cur.setRow(c.getRow());
//    }

    public CursedOne() {
    }
}