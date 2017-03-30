package com.swarna.android.matrixapp.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.swarna.android.matrixapp.R;

/**
 * Created by Swarna Tripathi on 3/29/2017.
 */

public class InputDialog extends DialogFragment implements View.OnClickListener
{
    public static final String TAG = "InputDialog";

    int rows;
    int columns;
    Context context;
    int[][] matrixValues;

    Button bt_submit;
    TableLayout tableLayout;

    int text;
    public InputDialog() {}

    public InputDialog(Context context,int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.dialog_input_entries, null);

        tableLayout = (TableLayout) view.findViewById(R.id.tl_entries);
        bt_submit = (Button) view.findViewById(R.id.bt_submit);

        bt_submit.setOnClickListener(this);

        //Creating the table views
        fillTable(rows,columns,tableLayout);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();

    }

    private void fillTable(final int n, final int m, TableLayout table) {
        table.removeAllViews();
        for (int i = 0; i < n; i++)
        {
            TableRow row = new TableRow(context);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));


            for (int j = 0; j < m; j++)
            {
                EditText edit = new EditText(context);
                edit.setLayoutParams(new TableRow.LayoutParams(100, 100));
                edit.setInputType(InputType.TYPE_CLASS_NUMBER);
                edit.setTextSize(12);

                row.addView(edit);
              //  edit.setText(Integer.toString(matrix[i][j]));
                matrixValues = new int [i][j];

                if(edit.getText().toString()!= null)
                {
                    text = Integer.parseInt(edit.getText().toString());
                }
            }
            table.addView(row);
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v == bt_submit)
        {
            System.out.println(" VALUES..." + matrixValues);

        }
    }
}
