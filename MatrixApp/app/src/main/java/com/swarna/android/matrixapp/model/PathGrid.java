package com.swarna.android.matrixapp.model;

import java.util.Scanner;

/**
 * Created by Swarna Tripathi on 3/29/2017.
 */

public class PathGrid
{
    public static int maxRows = 3;
    public static int maxCols = 5;


    public static int[][] getPathGrid(int rows , int columns)
    {
        int [][] grid = null;

        try(Scanner scan = new Scanner(System.in))
        {
            //Take input from User

            //for 1 & 2
            //grid = new int[5][6];

            grid = new int[rows][columns];
          //  grid = new int[3][5];

            grid[0][0] = 19;grid[0][1] = 10;grid[0][2] = 19;grid[0][3] = 10;grid[0][4] = 19;
            grid[1][0] = 21;grid[1][1] = 23;grid[1][2] = 20;grid[1][3] = 19;grid[1][4] = 12;
            grid[2][0] = 20;grid[2][1] = 12;grid[2][2] = 20;grid[2][3] = 11;grid[2][4] = 10;

            //result pass
            //cost 16
            // 1 2 3 4 4 5
        }catch(Exception e){
            System.out.println("Exception occurred in Grid "+e);
            e.printStackTrace();
        }

        return grid;
    }
}
