package com.swarna.android.matrixapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.swarna.android.matrixapp.AppData;
import com.swarna.android.matrixapp.GridViewCustomAdapter;
import com.swarna.android.matrixapp.R;

import static com.swarna.android.matrixapp.model.ServiceManagerImpl.searchLowestCostPath;

public class MainActivity extends Activity
{
    private GridView gv_cellsView;
    private EditText et_rows;
    private EditText et_columns;
    private Button bt_generate;
    private TextView tv_details;

    private int totalCells;

    GridViewCustomAdapter gridViewCustomAdapter;

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

        //Generating gridview with for taking custom input


    }

    public void generateMatrix(View view)
    {
        String rows = et_rows.getText().toString().trim();
        String column = et_columns.getText().toString().trim();
        AppData.rows = Integer.parseInt(rows);
        AppData.column = Integer.parseInt(column);

        searchLowestCostPath(0,0);
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



