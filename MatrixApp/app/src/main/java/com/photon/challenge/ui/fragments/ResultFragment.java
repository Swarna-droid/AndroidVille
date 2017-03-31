package com.photon.challenge.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.photon.challenge.R;
import com.photon.challenge.ui.activities.MainActivity;
import com.photon.challenge.utilities.AsyncLowCast;


public class ResultFragment extends Fragment {
    //Variables declaration
    private MainActivity parent;
    private TextView tvMin, tvSurrounding, tvSum;
    private String pathString = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.result_fragment, null);
        tvMin = (TextView) linearLayout.findViewById(R.id.tv1);
        tvSurrounding = (TextView) linearLayout.findViewById(R.id.tv2);
        tvSum = (TextView) linearLayout.findViewById(R.id.tv3);

        AsyncLowCast asyncLowCast = new AsyncLowCast(parent.matrix) {
            @Override
            public void onResult(String string, String status, int value) {
                tvMin.setText(status);
                tvSurrounding.setText("[" + pathString + "]");
                tvSum.setText(value + "");
            }
        };
        asyncLowCast.execute();

        return linearLayout;
    }

}
