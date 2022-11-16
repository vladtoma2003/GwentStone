package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Goliath extends Minion {

    public Goliath(final CardInput input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(true);
    }

    public Goliath(final Minion input) {
        super(input);
        setRow(1);
        setFrozen(false);
        setTank(true);
    }


    public Goliath() {

    }
}
