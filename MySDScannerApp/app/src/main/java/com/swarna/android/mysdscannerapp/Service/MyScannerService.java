package com.swarna.android.mysdscannerapp.Service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import com.swarna.android.mysdscannerapp.Activity.MainActivity;
import com.swarna.android.mysdscannerapp.AppData;
import com.swarna.android.mysdscannerapp.R;
import com.swarna.android.mysdscannerapp.SDCardScanManager;

/**
 * Created by Swarna Tripathi on 1/18/2017.
 */

public class MyScannerService extends IntentService
{
    private final int NOTIFICATION_ID = 1;
    SDCardScanManager sdCardScanManager;

    public MyScannerService()
    {
        super("MyScannerService");
    }

    public MyScannerService(String name)
    {
        super("MyScannerService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        doScanSDCard();
        if(AppData.stopSdCardScanning) stopScanning();

    }

    private void doScanSDCard()
    {
        Intent intent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notificationN = new Notification.Builder(this)
                .setContentTitle(getString(R.string.string_serviceRunning))
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_scan)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,notificationN);

        if(!AppData.stopSdCardScanning) startScan();
    }

    private void startScan()
    {
        sdCardScanManager = new SDCardScanManager();
        sdCardScanManager.start();
    }

    @Override
    public void onDestroy()
    {
        stopScanning();
        super.onDestroy();
    }

    public void stopScanning()
    {
        System.out.println("Stopped Service: " );
        if(sdCardScanManager != null)
        {
            sdCardScanManager.cancel();
            sdCardScanManager=null;
        }
        stopSelf();
    }

    public void cancelNotification(Context ctx, int notifyId)
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(ns);
        nMgr.cancel(notifyId);
    }
}
