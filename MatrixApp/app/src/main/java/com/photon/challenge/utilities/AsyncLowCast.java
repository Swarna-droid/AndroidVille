package com.photon.challenge.utilities;

import android.os.AsyncTask;

import com.photon.challenge.modals.DataBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
   * Asynctask to check the pathofLow cost*/
public abstract class AsyncLowCast extends AsyncTask<Void, Void, String> {

    String pathString = "";
    int[][] matrix;

    private int pathOfLowestCost = 0;
    private String status = "NO";
    private int rowLength = 0;
    private int colLength = 0;

    public AsyncLowCast(int[][] matrix) {
        this.matrix = matrix;
        this.rowLength = matrix.length;
        this.colLength = matrix[0].length;
    }

    @Override
    protected String doInBackground(Void... voids) {
        findSurroundingElements(matrix, 0, 0);
        return pathString;
    }

    @Override
    protected void onPostExecute(String s) {
        onResult("[" + pathString + "]", status, pathOfLowestCost);
    }

    /* this method used to find the surrounding elements*/
    private void findSurroundingElements(int[][] m, int indexX, int indexY) {
        Boolean flag = false;
        if (m == null) {
            throw new NullPointerException("The input matrix cannot be null");
        }
        DataBean dBean;
        List<DataBean> eleList = new ArrayList<>();

        for (int i1 = indexY + 1; (i1 <= (indexY + 1) && i1 < (colLength)); i1++) {
            for (int j1 = ((indexX - 1) == -1 ? 0 : (indexX - 1)); j1 <= Math.min(rowLength - 1, indexY + 1); j1++) {
                flag = true;
                dBean = new DataBean(m[j1][i1], j1, i1);
                eleList.add(dBean);
            }
        }
        if (flag) {
            findMinimumValueFromList(eleList, m);
        }
    }

    public abstract void onResult(String pathString, String status, int pathOfLowestCost);

    /*
     * This method used to find the lowest element in surrounding elements list*/
    private void findMinimumValueFromList(List<DataBean> eleList, int[][] m) {
        Collections.sort(eleList);
        pathOfLowestCost += eleList.get(0).getValue();
        if (pathString.length() == 0)
            pathString = pathString + eleList.get(0).getValue();
        else
            pathString = pathString + "," + eleList.get(0).getValue();
        System.out.println(eleList.get(0).getValue());
        if (eleList.get(0).getIndexX() == rowLength - 1) {
            status = "YES";
        }
        findSurroundingElements(m, eleList.get(0).getIndexX(), eleList.get(0).getIndexY());
    }
}
