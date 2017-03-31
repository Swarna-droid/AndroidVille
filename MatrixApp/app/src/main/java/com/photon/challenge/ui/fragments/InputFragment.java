package com.photon.challenge.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.photon.challenge.R;

public class InputFragment extends Fragment {


    Listener listener;

    public static InputFragment getInstance() {
        return new InputFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frgament_input, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final EditText etCol = (EditText) view.findViewById(R.id.et_col);
        final EditText etRow = (EditText) view.findViewById(R.id.et_row);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCol.length() == 0 || etRow.length() == 0) {
                    Toast.makeText(getActivity(), "Please enter value", Toast.LENGTH_SHORT).show();
                }
                if (listener != null)
                    listener.onInputProvided(Integer.parseInt(etCol.getText().toString()), Integer.parseInt(etRow.getText().toString()));
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }

    public interface Listener {
        void onInputProvided(int row, int col);
    }
}
