package com.swarna.android.mysdscannerapp.Model;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Swarna Tripathi on 1/17/2017.
 */

public class FileInfo implements Serializable
{
    private static final long SERIAL_ID = 1L;

    public Map<Double, String> fileInformation;

    public FileInfo(Map<Double, String> fileInformationMap)
    {
        this.fileInformation = fileInformationMap;
    }

    public double calculateAvg(Map<Double, String> updatedMap)
    {
        double avg = 0;
        for(double d : updatedMap.keySet())
        {
            avg = (avg+d)/updatedMap.size();
        }
        return avg;
    }

}
