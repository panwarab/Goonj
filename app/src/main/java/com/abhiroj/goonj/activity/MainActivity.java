package com.abhiroj.goonj.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.data.UpdateData;
import com.abhiroj.goonj.data.User;
import com.abhiroj.goonj.fragment.EventDetailListFragment;
import com.abhiroj.goonj.fragment.EventsFragment;
import com.abhiroj.goonj.fragment.MainFragment;
import com.abhiroj.goonj.fragment.TeamListFragment;
import com.abhiroj.goonj.fragment.UpdateFragment;
import com.abhiroj.goonj.listener.OnCardTappedListener;
import com.abhiroj.goonj.listener.OnDialogResponse;
import com.abhiroj.goonj.utils.Utility;
import com.crashlytics.android.Crashlytics;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import io.fabric.sdk.android.Fabric;

import static com.abhiroj.goonj.data.Constants.ARTS;
import static com.abhiroj.goonj.data.Constants.DANCE;
import static com.abhiroj.goonj.data.Constants.DRAMATICS;
import static com.abhiroj.goonj.data.Constants.FRAG_KEY;
import static com.abhiroj.goonj.data.Constants.KEY_EVENT_LIST;
import static com.abhiroj.goonj.data.Constants.LITERARY;
import static com.abhiroj.goonj.data.Constants.MUSIC;
import static com.abhiroj.goonj.data.Constants.OTHERS;
import static com.abhiroj.goonj.data.Constants.PHOTOGRAPHY;
import static com.abhiroj.goonj.data.Constants.RESULT_FROM_ADD;
import static com.abhiroj.goonj.data.Constants.RQ_UPDATE;
import static com.abhiroj.goonj.data.Constants.UPDATE_MESSAGE;
import static com.abhiroj.goonj.data.Constants.UPDATE_TITLE;
import static com.abhiroj.goonj.data.Constants.UPD_ROOT;
import static com.abhiroj.goonj.data.Constants.USER_ROOT;
import static com.abhiroj.goonj.data.Constants.auth_mail;
import static com.abhiroj.goonj.data.Constants.fragtag;
import static com.abhiroj.goonj.utils.Utility.checkNotNull;
import static com.abhiroj.goonj.utils.Utility.showSnackBar;
import static com.abhiroj.goonj.utils.Utility.showToast;


public class MainActivity extends AppCompatActivity implements OnCardTappedListener{

    private static final int RC_SIGN_IN = 1;
    private static final int RC_FIREBASE_DATA_ADD = 2;
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
    private FirebaseUser user;
    private Activity activity;
    private DatabaseReference user_root;
    private TeamListFragment teamListFragment;
    private AlertDialog updateDialog;
    private UpdateFragment updateFragment;
    private View navview;
    private TextView userlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=MainActivity.this;
        Fabric.with(this, new Crashlytics());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Drawable drawable=getDrawable(R.drawable.ic_dehaze);
        drawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(drawable);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        setupNavigationView();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        user_root=databaseReference.child(USER_ROOT);
        fragmentManager = getSupportFragmentManager();
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user=firebaseAuth.getCurrentUser();
                if(checkNotNull(user)) {
                    userStatus(user);
                    navigationView.getMenu().findItem(R.id.sign_in).setVisible(false);
                    navigationView.getMenu().findItem(R.id.sign_out).setVisible(true);
                }
                else
                {
                    navigationView.getMenu().findItem(R.id.sign_in).setVisible(true);
                    navigationView.getMenu().findItem(R.id.sign_out).setVisible(false);
                }
            }
        };
        if(checkNotNull(savedInstanceState) && savedInstanceState.size()!=0)
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
            else if(fragment instanceof UpdateFragment)
            {
                updateFragment=(UpdateFragment) fragment;
                fragmentManager.beginTransaction().replace(R.id.fragment_container,updateFragment,UpdateFragment.TAG).commit();
            }
            else if(fragment instanceof TeamListFragment)
            {
                teamListFragment=(TeamListFragment) fragment;
                fragmentManager.beginTransaction().replace(R.id.fragment_container,teamListFragment,TeamListFragment.TAG).commit();
            }
        }
        else {
            MainFragment mainfrag = MainFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, mainfrag, MainFragment.TAG).addToBackStack(MainFragment.TAG).commit();
        }
    }

    private void userStatus(final FirebaseUser  user) {
        userlogin.setText("Hi! "+user.getDisplayName());
        userlogin.setVisibility(View.VISIBLE);
        databaseReference.child(USER_ROOT).child(user.getUid()).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
               if(dataSnapshot.exists())
               {
                   User user=dataSnapshot.getValue(User.class);
                   navigationView.getMenu().findItem(R.id.add_update).setVisible(user.getAdmin());
               }
               else
               {
                   User newuser=new User();
                   newuser.setProvider(user.getProviderId());
                   newuser.setUname(user.getDisplayName());
                   newuser.setUmail(user.getEmail());
                   newuser.setUid(user.getUid());
                   newuser.setAdmin(false); // As the user is new, we set admin to false
                   databaseReference.child(USER_ROOT).child(newuser.getUid()).setValue(newuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           showSnackBar(MainActivity.this,"Thank You!");
                       }
                   });
               }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              showSnackBar(MainActivity.this,databaseError.getMessage());
          }
      });
    }





    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(checkNotNull(fragmentManager)) {
            int lastfragonstack = fragmentManager.getBackStackEntryCount();
            if (lastfragonstack >= 0) {
                String tag = fragmentManager.getBackStackEntryAt(lastfragonstack-1).getName();
                Fragment fragment=fragtag.get(tag);
                if (fragment!=null && fragment.isAdded())
                    fragmentManager.putFragment(outState, FRAG_KEY, fragtag.get(tag));
            }
        }
    }

    private void swapFragment(String cardname) {
        switch (cardname) {
            case "Events":
                EventsFragment eventsFragment = EventsFragment.newInstance();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, eventsFragment, EventsFragment.TAG).addToBackStack(EventsFragment.TAG).commit();
                break;
            case "Updates":
                UpdateFragment updateFragment= UpdateFragment.newInstance();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, updateFragment, UpdateFragment.TAG).addToBackStack(UpdateFragment.TAG).commit();
                break;
            case "Team":
               TeamListFragment teamListFragment= TeamListFragment.newInstance();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, teamListFragment, TeamListFragment.TAG).addToBackStack(TeamListFragment.TAG).commit();
                break;
            case "Dramatics":
                attachEventDetailListFragment(DRAMATICS);
                break;
            case "Literary":
                attachEventDetailListFragment(LITERARY);
                break;
            case "Arts":
                attachEventDetailListFragment(ARTS);
                break;
            case "Photography":
                attachEventDetailListFragment(PHOTOGRAPHY);
                break;
            case "Music":
                attachEventDetailListFragment(MUSIC);
                break;
            case "Dance":
                attachEventDetailListFragment(DANCE);
                break;
            case "Other":
                attachEventDetailListFragment(OTHERS);
                break;
            default:
                Toast.makeText(activity, R.string.wrong_choice, Toast.LENGTH_SHORT).show();
        }


    }

    private void attachEventDetailListFragment(String event) {
        EventDetailListFragment eventDetailListFragment=EventDetailListFragment.newInstance();
        Bundle bundle=new Bundle();
        bundle.putString(KEY_EVENT_LIST,event);
        eventDetailListFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragment_container,eventDetailListFragment,EventDetailListFragment.TAG).addToBackStack(EventDetailListFragment.TAG).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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
            signIn();
            break;
        case R.id.sign_out:
             signOut();
            break;
        case R.id.add_update:
            startActivityForResult(new Intent(MainActivity.this,ReleaseUpdate.class),RQ_UPDATE);
            break;
        case R.id.send:
            startActivity(new Intent(MainActivity.this,FeedbackActivity.class));
    }
    drawerLayout.closeDrawers();
    }

    private void signIn() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .setIsSmartLockEnabled(false)
                        .build(),
                RC_SIGN_IN);

    }

    private void signOut() {
        if(user!=null)
        {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            userlogin.setVisibility(View.INVISIBLE);
                            showSnackBar(activity,R.string.signout_success);
                            navigationView.getMenu().findItem(R.id.add_update).setVisible(false);
                        }
                    });
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
      switch(requestCode) {
          case RC_SIGN_IN:
              IdpResponse idpResponse=IdpResponse.fromResultIntent(data);
          if (resultCode == ResultCodes.OK) {
              // Sign-In Successful
              showSnackBar(activity, R.string.signing_in);
          }
          else
          {
              if(idpResponse==null)
              {
                  showSnackBar(activity,R.string.user_cancel_signin);
              return;
              }

              if(idpResponse!=null && idpResponse.getErrorCode()== ErrorCodes.NO_NETWORK)
              {
                  showSnackBar(activity,R.string.no_network);
              return;
              }

              if(idpResponse!=null && idpResponse.getErrorCode()==ErrorCodes.UNKNOWN_ERROR)
              {
                  showSnackBar(activity,R.string.techincal_issues);
              return;
              }

              else
              {
                  showSnackBar(activity,R.string.techincal_issues);
              }

          }
          break;
          case RC_FIREBASE_DATA_ADD:
              String message=data.getExtras().getString(RESULT_FROM_ADD);
                  Utility.showSnackBar(activity,message);
          break;
          case RQ_UPDATE:
              if(resultCode==RESULT_OK)
              {
                  if(user!=null) {
                      UpdateData updateData = new UpdateData();
                      updateData.setTitle(data.getExtras().getString(UPDATE_TITLE));
                      updateData.setMessage(data.getExtras().getString(UPDATE_MESSAGE));
                      updateData.setUpdate_by(user.getDisplayName());
                      addUpdateToFirebase(updateData);
                  }
                  else
                  {
                      showSnackBar(MainActivity.this,R.string.login_require);
                  }

              }
              else if(requestCode==RESULT_CANCELED)
              {
                  showSnackBar(MainActivity.this,R.string.upd_no_rel);
              }
              break;
      }
    }


    private void addUpdateToFirebase(UpdateData updateData) {
    DatabaseReference database=FirebaseDatabase.getInstance().getReference(UPD_ROOT);
        database.push().setValue(updateData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showSnackBar(MainActivity.this,R.string.upd_released);
            }
        }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showSnackBar(MainActivity.this,R.string.upd_not_released);
            }
        });
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
        navview = navigationView.inflateHeaderView(R.layout.nav_header);
        ImageView imageView=(ImageView) navview.findViewById(R.id.goonj_logo);
        Picasso.with(MainActivity.this).load(R.drawable.goonj_logo).resize(256,256).centerInside().into(imageView);
        userlogin= (TextView) navview.findViewById(R.id.log_welcome);
        navigationView.getMenu().findItem(R.id.add_update).setVisible(false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item.getItemId());
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
       int count=fragmentManager.getBackStackEntryCount();
        if(count<=1)
        {
            finish();
        }
        super.onBackPressed();

    }
}
