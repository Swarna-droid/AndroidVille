package com.swarna.android.matrixapp.model;

import java.util.Scanner;

/**
 * Created by Swarna Tripathi on 3/29/2017.
 */

public class PathGrid
{
    public static int maxRows = 3;
    public static int maxCols = 5;


    public static int[][] getPathGrid(int rows, int columns)
    {
        int [][] grid = null;

        try(Scanner scan = new Scanner(System.in))
        {
            //Take input from User

            //for 1 & 2
            //grid = new int[5][6];

            grid = new int[rows][columns];
          //  grid = new int[3][5];
            //example one
	/*grid[0][0] = 3;grid[0][1] = 4;grid[0][2] = 1;grid[0][3] = 2;grid[0][4] = 8;grid[0][5] = 6;
	grid[1][0] = 6;grid[1][1] = 1;grid[1][2] = 8;grid[1][3] = 2;grid[1][4] = 7;grid[1][5] = 4;
	grid[2][0] = 5;grid[2][1] = 9;grid[2][2] = 3;grid[2][3] = 9;grid[2][4] = 9;grid[2][5] = 5;
	grid[3][0] = 8;grid[3][1] = 4;grid[3][2] = 1;grid[3][3] = 3;grid[3][4] = 2;grid[3][5] = 6;
	grid[4][0] = 3;grid[4][1] = 7;grid[4][2] = 2;grid[4][3] = 8;grid[4][4] = 6;grid[4][5] = 4;*/

            //example two
/*	grid[0][0] = 3;grid[0][1] = 4;grid[0][2] = 1;grid[0][3] = 2;grid[0][4] = 8;grid[0][5] = 6;
	grid[1][0] = 6;grid[1][1] = 1;grid[1][2] = 8;grid[1][3] = 2;grid[1][4] = 7;grid[1][5] = 4;
	grid[2][0] = 5;grid[2][1] = 9;grid[2][2] = 3;grid[2][3] = 9;grid[2][4] = 9;grid[2][5] = 5;
	grid[3][0] = 8;grid[3][1] = 4;grid[3][2] = 1;grid[3][3] = 3;grid[3][4] = 2;grid[3][5] = 6;
	grid[4][0] = 3;grid[4][1] = 7;grid[4][2] = 2;grid[4][3] = 1;grid[4][4] = 2;grid[4][5] = 3;
*/
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
