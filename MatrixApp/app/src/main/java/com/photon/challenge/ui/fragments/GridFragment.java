package com.photon.challenge.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.photon.challenge.R;

import java.util.ArrayList;


public class GridFragment extends Fragment {
    private static final String PARAM_ROW = "PARAM_ROW_KEY";
    private static final String PARAM_COL = "PARAM_COL_KEY";


    private GridView gridView;
    private ArrayList<Integer> valuesList;

    Callback callback;

    int rows = 0, columns = 0;
    private int sizeOfGrid = 0;

    public static GridFragment getInstance(int row, int col) {
        GridFragment gridFragment = new GridFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_ROW, row);
        bundle.putInt(PARAM_COL, col);
        gridFragment.setArguments(bundle);
        return gridFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rows = getArguments().getInt(PARAM_ROW);
        columns = getArguments().getInt(PARAM_COL);
        sizeOfGrid = rows * columns;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.grid_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setNumColumns(columns);
        gridView.setAdapter(new MyAdapter());
        valuesList = new ArrayList<>();
        Button btSubmit = (Button) view.findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAllEntered = true;

                for (int i = 0; i < sizeOfGrid; i++) {
                    View gridItem = gridView.getChildAt(i);
                    EditText etNum = (EditText) gridItem.findViewById(R.id.etNum);
                    if (etNum.length() == 0) {
                        isAllEntered = false;
                        Toast.makeText(getActivity(), "Please Enter all the values in Grid", Toast.LENGTH_LONG).show();
                        valuesList.clear();
                        break;
                    }
                    valuesList.add(Integer.parseInt(etNum.getText().toString()));
                }
                if (isAllEntered) {
                    int[][] matrix = new int[rows][columns];
                    int count = 0;
                    for (int a = 0; a < rows; a++) {
                        for (int b = 0; b < columns; b++) {
                            Log.d("COUNT::", ":::" + count);
                            matrix[a][b] = valuesList.get(count);
                            Log.d("matrix::", ":::" + matrix[a][b]);
                            count++;
                        }
                    }
                    Toast.makeText(getActivity(), "Shortest Path To is....", Toast.LENGTH_LONG).show();
                    navigateToResult(matrix);
                }
            }
        });
    }

    private void navigateToResult(int[][] matrix) {
        if (callback != null)
            callback.showResult(matrix);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (Callback) context;
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return sizeOfGrid;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_grid_item, viewGroup, false);
        }
    }


    public interface Callback {
        void showResult(int[][] matrix);
    }

}
