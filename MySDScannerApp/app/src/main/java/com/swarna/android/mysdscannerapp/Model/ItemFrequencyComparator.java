package com.swarna.android.mysdscannerapp.Model;

import java.util.Comparator;

/**
 * Created by Swarna Tripathi on 1/18/2017.
 */

public class ItemFrequencyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 != null && o2 != null){
            if(o1 instanceof ItemFrequency && o2 instanceof ItemFrequency)
            {
                ItemFrequency i1 = (ItemFrequency) o1;
                ItemFrequency i2 = (ItemFrequency) o2;
                if(i1.getFrequency()<i2.getFrequency())
                    return 1;
                if(i1.getFrequency()>i2.getFrequency())
                    return -1;
            }

        }
        return 0;
    }
}
