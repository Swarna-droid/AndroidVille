package com.swarna.android.matrixapp.model;

/**
 * Created by Swarna Tripathi on 3/29/2017.
 */

public class ResultObject
{
    String message;
    String path="";
    int totalCost;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public int getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    @Override
    public String toString(){
        return "Message: "+this.getMessage()+"\nCost: "+this.getTotalCost()+"\nPath: "+this.getPath();
    }
}
