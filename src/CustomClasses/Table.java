package CustomClasses;

import Cards.Card;
import Cards.Minion;

import java.util.ArrayList;

public class Table {

    private ArrayList<Card>[] table;
    private int currTurn;
    private final int bound = 5;

    public Table(int curr_turn) {
        table = new ArrayList[4];
        for(int i = 0; i < 4; ++i) {
            table[i] = new ArrayList<Card>();
        }
        currTurn = curr_turn;
    }

    private boolean isFullRow(int row) {
        return bound <= table[row].size();
    }


    public void PlaceCard(Player p, int idx, int currPlayer) {
        Minion card = (Minion) p.getHand().get(idx);
        p.getHand().remove(idx);
        int mana = card.getMana();
        int row = 0;
        if(currPlayer == 0) {
            if(card.getRow() == 1) {
                row = 2;
            } else {
                row = 3;
            }
        } else {
            if(card.getRow() == 1) {
                row = 1;
            } else {
                row = 0;
            }
        }

        table[row].add(0, card);
        p.setMana(p.getMana()-mana);
    }


    public ArrayList<Card>[] getTable() {
        return table;
    }

    public void setTable(ArrayList<Card>[] table) {
        this.table = table;
    }

    public int getBound() {
        return bound;
    }

    public int getCurrTurn() {
        return currTurn;
    }

    public void setCurrTurn(int curr_turn) {
        this.currTurn = curr_turn;
    }

}
