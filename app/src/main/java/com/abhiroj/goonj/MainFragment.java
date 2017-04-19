package com.abhiroj.goonj;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import static com.abhiroj.goonj.Constants.HANDLER_POST_DELAYED_TIME;
import static com.abhiroj.goonj.Constants.IMAGE_COUNT;
import static com.abhiroj.goonj.Utility.checkNotNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private ViewPager imagepager;
    private ImageAdapter imageAdapter;
    public static final String TAG=MainFragment.class.getSimpleName();
    private View rootView;
    private int currentImage=0;
    private Handler slide_handler=makeHandler();

    Runnable automatic_sliding=new Runnable() {
        @Override
        public void run() {
            Log.d(TAG,"Runnable Called");
         imagepager.setCurrentItem((currentImage%IMAGE_COUNT)<4?currentImage++:setCurrentImage(0),true);
         slide_handler.postDelayed(automatic_sliding,HANDLER_POST_DELAYED_TIME);
        }
    };

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_main, container, false);
        setupImageSlider();
        return rootView;
    }

    private void setupImageSlider() {
        imagepager=(ViewPager)rootView.findViewById(R.id.imageslider);
        imagepager.setAdapter(new ImageAdapter(getChildFragmentManager()));
        imagepager.setCurrentItem(0);
        imagepager.setPageTransformer(true,new DepthPageTransformer());
    }

    /**
     * WIll use later for automation purpose, requires improvement
     */
    private void initiateHandler()
    {
        if(checkNotNull(slide_handler))
        {
            slide_handler.post(automatic_sliding);
        }
    }

    private void destroyHandler()
    {
        if(checkNotNull(slide_handler))
        {
         slide_handler.removeCallbacksAndMessages(null);
         slide_handler=null;
        }
    }

    /**
     *
     * @param currentImage current image displayed by image slider
     * @return
     */
    public int setCurrentImage(int currentImage) {
        Log.d(TAG,"Position Updated :"+currentImage);
         this.currentImage = currentImage;
    return currentImage;
    }


    private Handler makeHandler() {
        return new Handler();
    }
}
