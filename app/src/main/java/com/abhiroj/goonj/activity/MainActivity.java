package com.abhiroj.goonj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.fragment.EventDetailListFragment;
import com.abhiroj.goonj.fragment.EventsFragment;
import com.abhiroj.goonj.fragment.MainFragment;
import com.abhiroj.goonj.listener.OnCardTappedListener;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import static com.abhiroj.goonj.data.Constants.FRAG_KEY;
import static com.abhiroj.goonj.data.Constants.card_titles;
import static com.abhiroj.goonj.data.Constants.fragtag;
import static com.abhiroj.goonj.utils.Utility.checkNotNull;


public class MainActivity extends AppCompatActivity implements OnCardTappedListener {

    private static final int RC_SIGN_IN = 1;
    private static final int RC_ADDTOFIREBASE = 2;
    private FragmentManager fragmentManager;
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainFragment mainFragment;
    private EventsFragment eventFragment;
    private EventDetailListFragment eventDetailListFragment;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_dehaze);
        setSupportActionBar(toolbar);
        setupNavigationView();
        fragmentManager = getSupportFragmentManager();
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(checkNotNull(user))
                {
                    navigationView.getMenu().setGroupVisible(R.id.sign_in_group,true);
                    navigationView.getMenu().findItem(R.id.sign_in).setVisible(true);
                }
                else
                {
                    navigationView.getMenu().setGroupVisible(R.id.sign_in_group,false);
                    navigationView.getMenu().findItem(R.id.sign_in).setVisible(false);
                }
            }
        };
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
       getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    private void selectDrawerItem(int id) {
    switch (id)
    {
        case R.id.sign_in:
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);
            break;
        case R.id.sign_out:
            // TODO: Sign-Out Flow
            break;
        case R.id.add_event:
            Intent intent=new Intent(MainActivity.this,AddEvent.class);
            startActivityForResult(intent,RC_ADDTOFIREBASE);
            break;
    }
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode==RC_SIGN_IN)
       {
           if(resultCode==RESULT_OK)
           {
               Toast.makeText(MainActivity.this,R.string.signing_in,Toast.LENGTH_SHORT).show();
           }
           else
           {
               Toast.makeText(MainActivity.this,R.string.signing_in_error,Toast.LENGTH_SHORT).show();
           }
       }
    }

    @Override
    public void onCardTapped(String cardname) {
        if (!checkNotNull(fragmentManager))
            fragmentManager = getSupportFragmentManager();
        swapFragment(cardname);
    }

    public void setupNavigationView()
    {
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView=(NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item.getItemId());
                return false;
            }
        });
    }

}
