package com.swarna.android.mysdscannerapp.Model;

/**
 * Created by Swarna Tripathi on 1/18/2017.
 */

public class ItemFrequency
{
    private String extension;
    private int frequency;

    public ItemFrequency(String fileExtensions)
    {
        this.extension = fileExtensions;
    }

    public ItemFrequency(String fileExtensions, int frequency)
    {
        this.extension = fileExtensions;
        this.frequency = frequency;
    }



    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
