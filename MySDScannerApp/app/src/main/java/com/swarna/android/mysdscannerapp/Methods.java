package com.swarna.android.mysdscannerapp;

import java.io.File;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Swarna Tripathi on 1/18/2017.
 */

public class Methods
{
    public static String itemsToString(List<?> list, String separator) {
        StringBuilder sb = new StringBuilder();
        ListIterator iterator = list.listIterator();

        while(iterator.hasNext()) {
            Object item = iterator.next();
            sb.append(item);
            if(separator != null && iterator.hasNext()) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static double covertToMB(long size)
    {
        double kilobytes = (size / 1024);
        double megabytes = (kilobytes / 1024);

        return megabytes;
    }

    public static double getSize(File f)
    {
        long size = 0;
        if (f.isDirectory())
        {
            for (File file : f.listFiles())
            {
                size += getSize(file);
            }
        }
        else
        {
            size=f.length();
        }
        return covertToMB(size);
    }

    public static List<Double> sortArray(List<Double> array)
    {
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.size() - i - 1; j++) {
                if (array.get(j) < array.get(j + 1)) {
                    double temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
        return array;
    }
}
