package com.photon.challenge.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class Utils {
    public static Fragment replaceFragment(Fragment newFrag, int containerID, FragmentManager fragmentManager, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerID, newFrag);

        if (addToBackStack)
            fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

        fragmentManager.executePendingTransactions();

        return newFrag;
    }
}
