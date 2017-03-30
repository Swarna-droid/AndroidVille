package com.swarna.android.matrixapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.swarna.android.matrixapp.AppData;
import com.swarna.android.matrixapp.R;
import com.swarna.android.matrixapp.dialog.InputDialog;

public class MainActivity extends Activity
{
    private GridView gv_cellsView;
    private EditText et_rows;
    private EditText et_columns;
    private Button bt_generate;
    private TextView tv_details;
    private TableLayout tableLayout;

    int rows;
    int columns;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_rows = (EditText)findViewById(R.id.et_row);
        et_columns = (EditText) findViewById(R.id.et_column);
        bt_generate = (Button) findViewById(R.id.button3);
        tv_details = (TextView) findViewById(R.id.tv_details);
        gv_cellsView = (GridView) findViewById(R.id.gv_cells);
        tableLayout = (TableLayout) findViewById(R.id.tableLayout1);

        //Generating gridview with for taking custom input
    }

    public void generateMatrix(View view)
    {
        String rowsVal = et_rows.getText().toString().trim();
        String columnVal = et_columns.getText().toString().trim();

        rows = Integer.parseInt(rowsVal);
        columns = Integer.parseInt(columnVal);

        if(1>rows || rows >10 )
        {
            et_rows.setError(" Value for row  \n out of range \n  1<r<10");
        }
        if(5>columns || columns>100)
        {
            et_columns.setError(" Value for column  \n out of range \n  5<c<100");
        }
        else
        {
        new InputDialog(this,rows,columns).show(getFragmentManager(), InputDialog.TAG);
        }



        //  searchLowestCostPath(0,0);

        String details = "Message: " + AppData.message + "\n" + "Cost: " + AppData.cost + "\n" + "Path: " + AppData.path;

        tv_details.setText(details);

    }

    @Override
    protected void onResume()
    {
        super.onResume();

    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }
}



