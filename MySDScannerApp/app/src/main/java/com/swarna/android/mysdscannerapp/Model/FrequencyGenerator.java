package com.swarna.android.mysdscannerapp.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Swarna Tripathi on 1/18/2017.
 */

public class FrequencyGenerator
{
    public static List<String> itemFrequency = new ArrayList<>();

    public static List<String> printTopNItems(int noOfItems,ArrayList<ItemFrequency> itemList){
        return processNPrint(itemList);
    }

    public static List<String> processNPrint(ArrayList<ItemFrequency> itemList){
        HashMap<String, ItemFrequency> mapOfItems =
                new HashMap<String, ItemFrequency>();

        for(ItemFrequency i: itemList){
            if(!mapOfItems.containsKey(i.getExtension().trim())){
                i.setFrequency(1);
                mapOfItems.put(i.getExtension().trim(), i);
            } else{
                ItemFrequency updateFrequency = mapOfItems.get(i.getExtension().trim());
                updateFrequency.setFrequency(updateFrequency.getFrequency()+1);
            }

        }

        Collections.sort(itemList, new ItemFrequencyComparator());
        return printItemFrequencyList(itemList);
    }

    public static List<String> printItemFrequencyList(ArrayList<ItemFrequency> itemList){
        System.out.println("Item Extension,\tFrequency");
        int totalCount=0;
        String extensions = null;
        itemFrequency.clear();
        for(ItemFrequency i: itemList)
        {
            System.out.println(""+i.getExtension()+",\t"+i.getFrequency());
            extensions = String.format("Extension: \t\t %s\t\t\t\t\t\t\t\t Frequency: %d",i.getExtension(),i.getFrequency());
            itemFrequency.add(extensions);
            totalCount+=i.getFrequency();
            if(totalCount==itemList.size())
                break;
        }
        return itemFrequency;
    }
}
