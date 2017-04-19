package com.abhiroj.goonj;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static com.abhiroj.goonj.Constants.IMAGE_SLIDER_TAG;
import static com.abhiroj.goonj.Constants.fragtag;
import static com.abhiroj.goonj.Constants.image_placeholder;

/**
 * Created by ruthless on 19/4/17.
 */

public class ImagePagerFragment extends Fragment {


    private ImageView image_item;
    private String TAG=ImagePagerFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"Creating View");
        View rootView = inflater.inflate(R.layout.fragment_imagepager, container, false);
        image_item = (ImageView) rootView.findViewById(R.id.display_image);
        Picasso picasso=Picasso.with(getContext());
        picasso.setLoggingEnabled(true);
        picasso.load(image_placeholder).fit().into(image_item);
        return rootView;
    }

    public static ImagePagerFragment newInstance(int position) {
        MainFragment mainFragment= (MainFragment) fragtag.get(MainFragment.TAG);
        mainFragment.setCurrentImage(position);
        ImagePagerFragment imagePagerFragment=new ImagePagerFragment();
        Bundle args=new Bundle();
        args.putInt(IMAGE_SLIDER_TAG,position);
        imagePagerFragment.setArguments(args);
        return imagePagerFragment;
    }
}
