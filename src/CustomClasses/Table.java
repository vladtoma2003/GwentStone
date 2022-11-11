package CustomClasses;

import java.util.ArrayList;

public class Table {

    private ArrayList<ArrayList<Card>> table;
    private int currTurn;

    public Table(int curr_turn) {
        table = new ArrayList<ArrayList<Card>>();
        currTurn = curr_turn;
    }


    public void switchTurns() {
        if(currTurn == 1) {
            currTurn = 2;
        } else {
            currTurn = 1;
        }
    }


    public ArrayList<ArrayList<Card>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<Card>> table) {
        this.table = table;
    }

    public int getCurr_turn() {
        return currTurn;
    }

    public void setCurr_turn(int curr_turn) {
        this.currTurn = curr_turn;
    }

}
