package com.a0xffffffff.dinesum;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.facebook.Profile;

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener, NewRequestFragment.OnFragmentInteractionListener {
    private static final String SELECTED_ITEM = "arg_selected_item";

    private BottomNavigationViewEx mBottomNav;
    private ViewPager mViewPager;
    private Toolbar mToolbar;

    private SparseIntArray items;
    private List<Fragment> fragments;

    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlaceDetectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp);

        initView();
        initData();
        initFirebaseData();
        initGooglePlacesAPI();
        initEvent();
        initCreateNewUserIfFirstTime();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.app_bar_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                // TODO: logout gracefully
                break;
        }
        return true;
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mBottomNav = (BottomNavigationViewEx) findViewById(R.id.bnve);
        mBottomNav.enableAnimation(false);
        mBottomNav.enableShiftingMode(false);
        mBottomNav.enableItemShiftingMode(false);
        mBottomNav.setTextVisibility(false);
        mBottomNav.setItemHeight(200); //px
        mBottomNav.setIconSize(40, 40); //dp
        mBottomNav.setCurrentItem(0);
    }

    private void initData() {
        fragments = new ArrayList<>(3);
        items = new SparseIntArray(3);

        Fragment userFragment = MainFragment.newInstance("User");
        Fragment homeFragment = MainFragment.newInstance("Home");
//        Fragment addFragment = MainFragment.newInstance("Add");
        Fragment addFragment = NewRequestFragment.newInstance();

        fragments.add(userFragment);
        fragments.add(homeFragment);
        fragments.add(addFragment);

        items.put(R.id.menu_user, 0);
        items.put(R.id.menu_home, 1);
        items.put(R.id.menu_add, 2);

        mViewPager.setAdapter(new VpAdapter(getFragmentManager(), fragments));
    }

    private void initEvent() {
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    previousPosition = position;
                    mViewPager.setCurrentItem(position);
                }

                return true;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNav.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFirebaseData() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userFbId");
        // TODO: get the userCity using their Android location
        String userCity = "Los Angeles";
        FirebaseManager.attachInitialFirebaseListeners(userID, userCity);
        FirebaseManager.attachFirebaseListeners(userID, userCity);
    }

    private void initCreateNewUserIfFirstTime() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userFbId");
        ArrayList<User> allUsers = UserTracker.getInstance().getAllUsers();
        for (User user: allUsers) {
            if (user.getUserID() == userID)
                return;
        }
        User newUser = new User();
        newUser.setUserID(userID);
        FirebaseManager.getInstance().writeUser(newUser);
    }

    private void updateToolbarText(CharSequence text) {
        Log.d("bahhh", "got here in updateToolbarText");
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Hi");
        }
    }

    private void initGooglePlacesAPI() {
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
    }

    public void onFragmentInteraction(String TAG) {
        // TODO
    }

    public void onSubmitButtonPressed(String TAG) {
        if (TAG.equals(NewRequestFragment.TAG)) {
            mViewPager.setCurrentItem(1);
        }
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

}

