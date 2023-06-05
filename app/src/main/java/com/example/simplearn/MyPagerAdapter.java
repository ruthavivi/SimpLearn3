package com.example.simplearn;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter  {
    int tabcount;
    private OnOptionSelectedListener optionSelectedListener;

    public MyPagerAdapter(FragmentManager fm,int behavior,OnOptionSelectedListener listener) {
        super(fm, behavior);
        tabcount = behavior;
        optionSelectedListener = listener;
    }

    @Override
    public Fragment getItem(int position) {
        // Return the Fragment for the given position
        switch (position) {
            case 0:
                return new game1fragment();
            case 1:
                return new game2fragment();
            case 2:
                return new game3fragment();
            case 3:
                return new game4fragment();
            default:
                return new game1fragment();
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

    public interface OnOptionSelectedListener {
        void onOptionSelected(int position);
    }
}

