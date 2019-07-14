package com.ahmadfauzirahman.sakato.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ahmadfauzirahman.sakato.fragment.SpmFragment;
import com.ahmadfauzirahman.sakato.fragment.KontrakFragment;
import com.ahmadfauzirahman.sakato.fragment.SupplierFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.mNoOfTabs = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                SpmFragment aboutFragment = new SpmFragment();
                return aboutFragment;
            case 1:
                KontrakFragment listFragment = new KontrakFragment();
                return listFragment;
            case 2:
                SupplierFragment supplierFragment = new SupplierFragment();
                return supplierFragment;
            /*case 2 :
                SpmFragment aboutFragment = new SpmFragment();
                return aboutFragment;*/

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
