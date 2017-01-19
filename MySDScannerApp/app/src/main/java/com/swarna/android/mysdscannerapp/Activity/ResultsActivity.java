package com.swarna.android.mysdscannerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.swarna.android.mysdscannerapp.AppData;
import com.swarna.android.mysdscannerapp.Methods;
import com.swarna.android.mysdscannerapp.Model.FileInfo;
import com.swarna.android.mysdscannerapp.Model.FrequencyGenerator;
import com.swarna.android.mysdscannerapp.R;

import static com.swarna.android.mysdscannerapp.AppData.result;
import static com.swarna.android.mysdscannerapp.AppData.stopSdCardScanning;
import static com.swarna.android.mysdscannerapp.AppData.updatedMap;

/**
 * Created by Swarna Tripathi on 1/18/2017.
 */

public class ResultsActivity extends AppCompatActivity
{
    private TextView tv_result;
    private TextView tv_average;
    private TextView tv_frequency;
    private Toolbar toolbar;
    FileInfo fileInfo;
    String averageValue;

    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_average = (TextView) findViewById(R.id.tv_avg);
        tv_frequency = (TextView) findViewById(R.id.tv_frequency);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        toolbar.setTitle("Share Statistics");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        AppData.isResultReady = false;

        setSupportActionBar(toolbar);

        fileInfo = new FileInfo(updatedMap);
        averageValue = String.format("Average file size : %4f MB",fileInfo.calculateAvg(updatedMap) );

        tv_frequency.setText(Methods.itemsToString(FrequencyGenerator.itemFrequency,"\n"));

        System.out.println("RESULT"+ (Methods.itemsToString(result,"\n")));
        setResult();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "Average value :" + fileInfo.calculateAvg(updatedMap)+ "MB" +"\n"+ "File names and Sizes " + Methods.itemsToString(result,"\n");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"My SD Statistics");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Share Via.."));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setResult()
    {
        tv_average.setText(averageValue);
        tv_result.setText(Methods.itemsToString(result,"\n"));
    }

    @Override
    public void onBackPressed()
    {
        stopSdCardScanning = true;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
}
