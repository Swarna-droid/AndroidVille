package com.photon.challenge.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.photon.challenge.R;
import com.photon.challenge.ui.fragments.GridFragment;
import com.photon.challenge.ui.fragments.InputFragment;
import com.photon.challenge.ui.fragments.ResultFragment;
import com.photon.challenge.utilities.Utils;

public class MainActivity extends AppCompatActivity implements InputFragment.Listener, GridFragment.Callback {

    public int[][] matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            Utils.replaceFragment(InputFragment.getInstance(), R.id.content, getSupportFragmentManager(), false);

    }

    @Override
    public void onInputProvided(int row, int col) {
        Utils.replaceFragment(GridFragment.getInstance(row, col), R.id.content, getSupportFragmentManager(), true);
    }

    @Override
    public void showResult(int[][] matrix) {
        this.matrix = matrix;
        ResultFragment resultFragment = new ResultFragment();
        Utils.replaceFragment(resultFragment, R.id.content, getSupportFragmentManager(), true);
    }
}
