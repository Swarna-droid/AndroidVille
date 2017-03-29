package com.swarna.android.matrixapp.model;

/**
 * Created by Swarna Tripathi on 3/29/2017.
 */

public class Cell
{
    int row;
    int col;
    int cost;

    public Cell(int row, int col, int cost){
        this.row = row;
        this.col = col;
        this.cost = cost;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString(){
        return "Row: "+this.getRow()+"\tCol: "+this.getCol()+"\tCost: "+this.getCost();
    }
}
