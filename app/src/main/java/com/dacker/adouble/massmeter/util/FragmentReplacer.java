package com.dacker.adouble.massmeter.util;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.dacker.adouble.massmeter.R;

import java.util.List;

public class FragmentReplacer {

    public static void setFragment(AppCompatActivity activity, Fragment fragment) {
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        if (fragments.size() > 0) {
            if (!fragments.get(0).equals(fragment)) {
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        } else {
            activity.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
