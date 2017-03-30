package com.swarna.android.matrixapp.model;

import com.swarna.android.matrixapp.AppData;
import com.swarna.android.matrixapp.exception.MaxCostExceededException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swarna Tripathi on 3/29/2017.
 */

public class ServiceManagerImpl
{
    public static final int MAX_COST = 50;

    //public static PathGrid pg = new PathGrid();
    public static int[][] grid = PathGrid.getPathGrid(AppData.rows,AppData.column);

    public static void searchLowestCostPath(int rowIndex, int colIndex)
    {
        Cell first = getFirstCell(rowIndex, colIndex);
        ResultObject result = new ResultObject();
        int maxWalk = grid[0].length-1;
        System.out.println(grid.length+" grid[0].length "+grid[0].length );

        try{
            updateResults(first, result);
            Cell next = first;
            while( maxWalk > 0 ){

                next = findNextBestMove(next.getRow(), next.getCol());
                System.out.println("NEXT >> "+next.toString());
                updateResults(next, result);
                --maxWalk;
                //System.out.println("maxWalk "+maxWalk);
            }
            result.setMessage("Yes");

            System.out.println(result.toString());

        }catch(MaxCostExceededException max){
            System.out.println("Max Cost Exceeded 50. Exiting.");
            System.out.println(result.toString());
        }catch(Exception e){
            System.out.println("Exception occurred in searchLowestCostPath. "+e.getMessage());
            e.printStackTrace();
        }

    }
    public static boolean isWrapApplicable(int row, int col)
    {
        if(row < 0 || row > (grid.length-1) )
            return true;
        else
            return false;
    }
    public static Cell wrapFLRows(int row, int col){
        Cell wrapped = new Cell(0,0,0);

        if(row <= 0){
            row = (grid.length-1);
            wrapped.setRow(row);
            wrapped.setCol(col);
        }
        else if(row >= (grid.length-1)){
            row =0;
            wrapped.setCol(col);
            wrapped.setRow(row);
        }
        return wrapped;
    }


    public static Cell findNextBestMove(int row, int col)
    {
        Cell diagUp = new Cell((row-1), (col+1), -1);
        Cell str = new Cell((row), (col+1), -1);
        Cell diagDown = new Cell((row+1), (col+1), -1);

        List<Cell> neighbours = new ArrayList<Cell>(3);
        neighbours.add(diagUp);neighbours.add(str);neighbours.add(diagDown);

        List<Cell> updated = new ArrayList<Cell>(3);

        int index=0;
        for(Cell c : neighbours ){
            if(isWrapApplicable(c.getRow(), c.getCol())){
                Cell temp = wrapFLRows(c.getRow(), c.getCol());
                c.setCol(temp.getCol());
                c.setRow(temp.getRow());
                c.setCost(grid[temp.getRow()][temp.getCol()]);
                //updated.add(c);
            }
            else{
                c.setCost(grid[c.getRow()][c.getCol()]);
                //updated.add(c);
            }
        }

        return getMinCostCell(neighbours);
    }

    public static Cell getMinCostCell(List<Cell> cells){
        Cell c = cells.get(0);
        for(Cell t: cells){
            System.out.println(t.toString());
            if(t.getCost() < c.getCost())
                c = t;
        }
        return c;
    }

    public static boolean updateResults(Cell c, ResultObject result) throws MaxCostExceededException{

        if(result.getTotalCost()+grid[c.getRow()][c.getCol()]< 50){
            result.setMessage("Calculating");
            result.setPath(result.getPath()+" "+(c.getRow()+1));
            result.setTotalCost(result.getTotalCost()+grid[c.getRow()][c.getCol()]);
            AppData.message = result.getMessage();
            AppData.cost = result.getTotalCost();
            AppData.path = result.getPath();
            return true;
        }
        else{
            result.setMessage("No");
            throw new MaxCostExceededException("Max Cost will Exceed by 50");
        }
    }

    public static Cell getFirstCell(int row, int col){
        Cell first = null;
        int small = 0;

        //start
        small = grid[0][0];
        int rowIndex = 0;
        for(int i=1;i<grid.length;i++){
            if(grid[i][0] < small){
                small = grid[i][0];
                rowIndex = i;
            }
        }
        first = new Cell(rowIndex, 0, small);
        System.out.println(first.toString());
        return first;
    }

}
