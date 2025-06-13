package com.example.sajagindia.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sajagindia.fragments.FollowerFragment;
import com.example.sajagindia.fragments.FollowingFragment;

public class StateAdapter extends FragmentStateAdapter {
    public StateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new FollowerFragment();
        }
        return new FollowingFragment();

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
