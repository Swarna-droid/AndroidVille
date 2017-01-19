package com.swarna.android.mysdscannerapp;

import com.swarna.android.mysdscannerapp.Model.FrequencyGenerator;
import com.swarna.android.mysdscannerapp.Model.ItemFrequency;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.swarna.android.mysdscannerapp.AppData.result;
import static com.swarna.android.mysdscannerapp.AppData.updatedMap;

/**
 * Created by Swarna Tripathi on 1/18/2017.
 */

public class SDCardScanManager extends Thread {

    private boolean runLoop = true;
    private boolean isRunning = false;
    private final long SLEEP_TIME = 1000;
    private ItemFrequency itemFrequency;

    private List<File> fileList = new ArrayList<>();
    private List<Double> sortedArray = new ArrayList<>();
    private ArrayList<ItemFrequency> fileExtensions = new ArrayList<>();
    private Map<Double,String> fileInfoMap = new HashMap<>();

    @Override
    public void run()
    {
        isRunning = true;
        String secStore = System.getenv("SECONDARY_STORAGE");
        File f_secs = new File(secStore);

        while (runLoop)
        {
            fileList.clear();
            doScanFilesFromSDCard(f_secs);
            displayNameAndSize();
        }

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        isRunning = false;
    }

    public void cancel()
    {
        runLoop = false;
    }

    public boolean isRunning()
    {
        return isRunning;
    }

    private List<File> doScanFilesFromSDCard(File dir)
    {
        File listFile[] = dir.listFiles();
        List<Double> fileSizeList = new ArrayList<>();

        if (listFile != null && listFile.length > 0)
        {
            for (int i = 0; i < listFile.length; i++)
            {
                if (listFile[i].isDirectory())
                {
                    fileList.add(listFile[i]);
                    doScanFilesFromSDCard(listFile[i]);
                }
                else
                {
                    if (listFile[i].getName().endsWith(".png")
                            || listFile[i].getName().endsWith(".jpg")
                            || listFile[i].getName().endsWith(".jpeg")
                            || listFile[i].getName().endsWith(".gif")
                            || listFile[i].getName().endsWith(".pdf")
                            || listFile[i].getName().endsWith(".doc")
                            || listFile[i].getName().endsWith(".txt")
                            || listFile[i].getName().endsWith(".mp3")
                            || listFile[i].getName().endsWith(".MPG"))
                    {
                        if(!fileList.contains(listFile[i])) fileList.add(listFile[i]);
                    }
                }
            }
        }


        for(File f : fileList)
        {
            double sizeOfFile = Methods.getSize(f);
            if(sizeOfFile >0)
            {
                fileSizeList.add(sizeOfFile);

                getFirstNLargest(Methods.sortArray(fileSizeList),10);

                if(fileInfoMap.get(sizeOfFile) == null) fileInfoMap.put(sizeOfFile,f.getName());

                itemFrequency = new ItemFrequency(getFileExtensions(f));
                fileExtensions.add(itemFrequency);

            }
        }
        System.out.println("Freq ::: :" + Methods.itemsToString(FrequencyGenerator.printTopNItems(5,fileExtensions),"\n"));

        return fileList;
    }

    private String getFileExtensions(File f)
    {
        String fileName = f.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";

    }

    private List<Double> getFirstNLargest(List<Double> fileSize, int n)
    {
        int tillLength = fileSize.size()>=n ? n : fileSize.size();

        sortedArray.clear();

        for(int i=0; i<tillLength;i++)
        {
            if(!sortedArray.contains(fileSize.get(i)))sortedArray.add(fileSize.get(i));
        }
        return sortedArray;
    }

    private void displayNameAndSize()
    {
        result.clear();
        for(double d : sortedArray)
        {
            String format = "Name: %s\t\t\t\t\t\t\tSize: %f MB";

            String s = String.format(format,fileInfoMap.get(d),d );
            result.add(s);

            AppData.isResultReady = true;

            updatedMap.put(d,fileInfoMap.get(d));
        }
    }
}
