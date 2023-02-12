package com.project.tablayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.project.tablayout.fragment.ComingSoonFragment;
import com.project.tablayout.fragment.HomeFragment;
import com.project.tablayout.fragment.TrendingFragment;

public class myViewAdapter extends FragmentStateAdapter {
    public myViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();

            case 1:
                return new TrendingFragment();

            case 2: return new ComingSoonFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
