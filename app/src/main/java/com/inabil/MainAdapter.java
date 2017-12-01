package com.inabil;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inabil.auth.LoginFragment;
import com.inabil.auth.SignupFragment;

/**
 * Created by NABIL on 29-11-2017.
 */

public class MainAdapter extends FragmentPagerAdapter {

    private final String[] items = {"Login", "Signup"};

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0)
            fragment = new LoginFragment();
        else
            fragment = new SignupFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return items[position];
    }
}