package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Goliath extends Minion{

    public Goliath(CardInput input) {
        super(input);
        setRow(1);
    }

    public Goliath(Minion input) {
        super(input);
        setRow(1);
    }

//    public Goliath(Goliath g) {
//        Goliath gol = new Goliath();
//        gol.setName(g.getName());
//        gol.setMana(g.getMana());
//        gol.setDescription(g.getDescription());
//        gol.setColors(g.getColors());
//        gol.setHealth(g.getHealth());
//        gol.setAttackDamage(g.getAttackDamage());
//        gol.setRow(g.getRow());
//    }

    public Goliath() {

    }
}
