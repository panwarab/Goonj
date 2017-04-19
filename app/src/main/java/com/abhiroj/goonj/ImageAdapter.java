package com.abhiroj.goonj;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import static com.abhiroj.goonj.Constants.IMAGE_COUNT;

/**
 * Created by ruthless on 19/4/17.
 */

public class ImageAdapter extends FragmentPagerAdapter{

    private final String TAG=ImageAdapter.class.getSimpleName();

    public ImageAdapter(FragmentManager fm) {
        super(fm);
        Log.d(TAG,"Adapter init");
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG,"Fetching item "+position);
        return ImagePagerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return IMAGE_COUNT;
    }
}
