package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Disciple extends Minion {
    public Disciple(CardInput input) {
        super(input);
        setRow(0);
    }

    public Disciple(Minion input) {
        super(input);
        setRow(0);
    }

//    public Disciple(Disciple d) {
//        Disciple disc = new Disciple();
//        disc.setName(d.getName());
//        disc.setMana(d.getMana());
//        disc.setDescription(d.getDescription());
//        disc.setColors(d.getColors());
//        disc.setHealth(d.getHealth());
//        disc.setAttackDamage(d.getAttackDamage());
//        disc.setRow(d.getRow());
//    }

    public Disciple() {

    }
}
