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

    public void PlaceCard(Player p, int idx, int currPlayer, Error err) {
        if(idx >= p.getHand().size()) {
            return;
        }
        if (p.getHand().get(idx).getName().equals("Winterfell") ||
                p.getHand().get(idx).getName().equals("Firestorm") ||
                p.getHand().get(idx).getName().equals("Heart Hound")) {
            err.setErr(true);
            err.setMessage("Cannot place environment card on table.");
            return;
        }
        Minion card = (Minion) p.getHand().get(idx);
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
        int mana = card.getMana();
//        System.out.println(p.getMana() + " " + mana);
//        System.out.println(card.toString());
        if(mana > p.getMana()) {
            System.out.println("EROARE:" + p.getMana() + mana);
            err.setErr(true);
            err.setMessage("Not enough mana to place card on table.");
            return;
        }
        if(isFullRow(row)) {
            err.setErr(true);
            err.setMessage("Cannot place card on table since row is full.");
            return;
        }
        p.setMana(p.getMana()-mana);
        p.getHand().remove(idx);
        table[row].add(0, card);
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
