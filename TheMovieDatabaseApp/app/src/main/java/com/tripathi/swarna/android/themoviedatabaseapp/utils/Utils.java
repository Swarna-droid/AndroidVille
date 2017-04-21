package com.tripathi.swarna.android.themoviedatabaseapp.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils
{
    /**
     * @param context This is the context whether activity or fragment
     * @param view This is the view could be EditText, or the button
     */
    public static void hideKeyboard(Context context, View view)
    {
        try
        {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}