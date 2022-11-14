package CustomClasses;

import Cards.Card;
import Cards.Environment;
import Cards.Minion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Table {

    private ArrayList<Minion>[] table;
    private int currTurn;
    private final int bound = 5;

    public Table(ArrayList<Minion>[] tabel) {
        tabel = table.clone();
    }

    public Table(int curr_turn) {
        table = new ArrayList[4];
        for(int i = 0; i < 4; ++i) {
            table[i] = new ArrayList<Minion>();
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

    public ArrayList<Card> getRow(int row) {
        ArrayList<Card> ret = new ArrayList<>(table[row]);
        return ret;
    }

    public void PrintTable() {
        ArrayList<Minion>[] masa = table.clone();
        for(var tabel : masa) {
            Collections.reverse(tabel); // solutie momentan, cand trebuie data pozitia 5-poz pt pozitia corecta
        }
        String string0 = masa[0].toString();
        String string1 = masa[1].toString();
        String string2 = masa[2].toString();
        String string3 = masa[3].toString();
        System.out.println(string0 + "\n" + string1 + "\n" + string2 + "\n" + string3);
    }
    public Minion getCardAtPosition (int x, int y) {
//        System.out.println("x=" + x + " y= " + y);
//        PrintTable();
        if(table == null) {
            return null;
        }
        if(x > 3) {
            return null;
        }
        int size = table[x].size();
        if(y >= size) {
            return null;
        }
        int pos = size - y - 1;
        Minion ret = new Minion(table[x].get(pos));
        return ret;
    }

    public void useEnvCard(Player player, int idx, int row, Error err) {
        Card card = player.getHand().get(idx);
//        Environment card = (Environment) player.getHand().get(idx);
        if(player.getPlayerIdx() == 1) {
            if(row > 1) {
                err.setErr(true);
                err.setMessage("Chosen row does not belong to the enemy.");
                return;
            }
        } else {
            if(row < 2) {
                err.setErr(true);
                err.setMessage("Chosen row does not belong to the enemy.");
                return;
            }
        }
        switch(card.getName()) {
            case "Firestorm":
                for(int i = 0; i < table[row].size(); ++i) {
                    Minion curr_card = (Minion)table[row].get(i);
                    curr_card.setHealth(curr_card.getHealth() - 1);
                }
                checkHealth();
                break;
            case "Winterfell":
                Minion c;
                for(int i = 0; i < table[row].size(); ++i) {
                    c = (Minion) table[row].get(i);
                    c.setFrozen(true);
                }
                break;
            case "Heart Hound":
                Minion stolenCard = (Minion)table[row].get(0);
                for(int i = 1; i < table[row].size(); ++i) {
                    Minion minion = (Minion) table[row].get(i);
                    if(stolenCard.getHealth() > minion.getHealth()) {
                        stolenCard = minion;
                    }
                }
                int newRow = 3-row;
                if(isFullRow(newRow)) {
                    err.setErr(true);
                    err.setMessage("Cannot steal enemy card since the player's row is full.");
                    return ;
                }
                table[row].remove(stolenCard);
                table[newRow].add(stolenCard);
                System.out.println(stolenCard.toString());
                break;
            default:
                err.setErr(true);
                err.setMessage("Chosen card is not of type environment.");
                return;
        }
        if(card.getMana() > player.getMana()) {
            err.setErr(true);
            err.setMessage("Not enough mana to use environment card.");
            return;
        }
        player.getHand().remove(card);
        player.setMana(player.getMana() - card.getMana());
    }

    public void checkHealth() {
        for(int i = 0; i < 4; ++i) {
            ArrayList<Minion> row = new ArrayList<>(table[i]);
            int j = 0;
            while(j < table[i].size()){
                Minion card = (Minion) table[i].get(j);
                if(card.getHealth() <= 0) {
                    row.remove(card);
                }
                ++j;
            }
            table[i] = row;
        }
    }

    public void setFrozenFalse() {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < table[i].size(); ++j) {
                Minion card = (Minion) table[i].get(j);
                card.setFrozen(false);
            }
        }
    }

    public ArrayList<Minion>[] getTable() {
        return table;
    }

    public void setTable(ArrayList<Minion>[] table) {
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
