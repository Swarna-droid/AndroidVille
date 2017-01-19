package com.swarna.android.mysdscannerapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.swarna.android.mysdscannerapp.AppData;
import com.swarna.android.mysdscannerapp.R;
import com.swarna.android.mysdscannerapp.Service.MyScannerService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private ImageView iv_scan;
    private TextView tv_scanText;
    private ProgressBar pb_progressBar;
    private LinearLayout ll_progressBar;

    private Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            AppData.stopSdCardScanning = savedInstanceState.getBoolean("scanState");
        }

        System.out.println("SCAN STATE" + AppData.stopSdCardScanning);

        iv_scan = (ImageView) findViewById(R.id.iv_scan);
        tv_scanText = (TextView) findViewById(R.id.tv_scanText);
        pb_progressBar = (ProgressBar) findViewById(R.id.pb_progressBar);
        ll_progressBar = (LinearLayout) findViewById(R.id.ll_progressBar);

        //Setting up listeners
        iv_scan.setOnClickListener(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        worker = new Worker();
        worker.start();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (worker != null)
        {
            worker.cancel();
            worker = null;
        }

        Bundle bundle = new Bundle();
        bundle.putBoolean("scanState",AppData.stopSdCardScanning);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("scanState", AppData.stopSdCardScanning);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        AppData.stopSdCardScanning = savedInstanceState.getBoolean("scanState");
    }
    @Override
    public void onClick(View v)
    {
        if (v == iv_scan)
        {
            setUpProgressBar();
            switchScanningMode();
        }
    }

    @Override
    public void onBackPressed()
    {
       cancelScan();
    }

    private void switchScanningMode()
    {
        if (tv_scanText.getText().toString().matches("Start Scan"))
        {
            AppData.stopSdCardScanning = false;

            Intent intent = new Intent(MainActivity.this, MyScannerService.class);
            startService(intent);

            tv_scanText.setText("Stop Scan");  //Changing the text according to action taken.
        }
        else
        {
            cancelScan();
        }
    }

    private void cancelScan()
    {
        cancelProgressBar();
        tv_scanText.setText("Start Scan");
        AppData.stopSdCardScanning = true;
    }


    private void cancelProgressBar()
    {
        ll_progressBar.setVisibility(View.GONE);
        pb_progressBar.setVisibility(View.GONE);
    }

    private void setUpProgressBar()
    {
        ll_progressBar.setVisibility(View.VISIBLE);
        pb_progressBar.setVisibility(View.VISIBLE);
        pb_progressBar.setProgress(40);
        int maxProgress = 200;
        for(int i = 10; i < maxProgress; i++)
        {
            pb_progressBar.incrementProgressBy(5);
            pb_progressBar.setSecondaryProgress(10);
        }
    }

    private class Worker extends Thread
    {
        Handler handler;

        private boolean run = true;

        private final long SLEEP_TIME = 300;

        public Worker()
        {
            handler = new Handler();
        }

        private Runnable runnable_update = new Runnable()
        {
            @Override
            public void run()
            {
                if(AppData.isResultReady && !AppData.stopSdCardScanning)
                {
                    Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                    startActivity(intent);
                }
            }
        };

        @Override
        public void run()
        {
            while (run)
            {
                handler.post(runnable_update);
                try
                {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

        public void cancel()
        {
            run = false;
        }
    }


}

