package com.abhiroj.goonj.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.fragment.EventDetailListFragment;
import com.abhiroj.goonj.fragment.EventsFragment;
import com.abhiroj.goonj.fragment.MainFragment;
import com.abhiroj.goonj.listener.OnCardTappedListener;

import static com.abhiroj.goonj.data.Constants.FRAG_KEY;
import static com.abhiroj.goonj.data.Constants.card_titles;
import static com.abhiroj.goonj.data.Constants.fragtag;
import static com.abhiroj.goonj.utils.Utility.checkNotNull;


public class MainActivity extends AppCompatActivity implements OnCardTappedListener {

    private FragmentManager fragmentManager;
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainFragment mainFragment;
    private EventsFragment eventFragment;
    private EventDetailListFragment eventDetailListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_dehaze);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        if(checkNotNull(savedInstanceState))
        {
            Fragment fragment=fragmentManager.getFragment(savedInstanceState,FRAG_KEY);
            if(fragment instanceof MainFragment)
            {
                mainFragment=(MainFragment) fragment;
                fragmentManager.beginTransaction().replace(R.id.fragment_container,mainFragment,MainFragment.TAG).commit();
            }
            else if(fragment instanceof EventsFragment)
            {
                eventFragment=(EventsFragment) fragment;
                fragmentManager.beginTransaction().replace(R.id.fragment_container,eventFragment,EventsFragment.TAG).commit();
            }
            else if(fragment instanceof EventDetailListFragment)
            {
                eventDetailListFragment=(EventDetailListFragment) fragment;
                fragmentManager.beginTransaction().replace(R.id.fragment_container,eventDetailListFragment,EventDetailListFragment.TAG).commit();
            }
        }
        else {
            MainFragment mainfrag = MainFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, mainfrag, MainFragment.TAG).addToBackStack(MainFragment.TAG).commit();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(checkNotNull(fragmentManager))
        {
                String tag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            if(fragtag.get(tag).isAdded())
            fragmentManager.putFragment(outState, FRAG_KEY, fragtag.get(tag));
            }
    }

    private void swapFragment(String cardname) {
        switch (cardname) {
            case "Events":
                EventsFragment eventsFragment = EventsFragment.newInstance();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, eventsFragment, EventsFragment.TAG).addToBackStack(EventsFragment.TAG).commit();

                break;
            case "Latest Updates":
                //TODO: Latest Updates Fragment
                break;
            case "Team":
                // TODO: Team Fragment
                break;
            case "Register":
                // TODO: Register Fragment
                break;
            case "Event 1":
                EventDetailListFragment eventDetailListFragment=EventDetailListFragment.newInstance();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,eventDetailListFragment,EventDetailListFragment.TAG).addToBackStack(EventDetailListFragment.TAG).commit();
                break;
            case "Event 2":

                break;
            case "Event 3":

                break;
            default:
                Toast.makeText(MainActivity.this, R.string.wrong_choice, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCardTapped(String cardname) {
        if (!checkNotNull(fragmentManager))
            fragmentManager = getSupportFragmentManager();
        swapFragment(cardname);
    }
}
