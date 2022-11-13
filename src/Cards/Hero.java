package Cards;

import Cards.Card;
import fileio.CardInput;

public class Hero extends Card {
    private static int health = 30;

    public Hero(CardInput card) {
        super(card);
    }

    public int getHealth() {
        return health;
    }

}
