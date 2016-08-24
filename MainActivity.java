package cabme.cabmedashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button bBookNow, bJoinIn, bRideShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        bBookNow = (Button)findViewById(R.id.bBookNow);
        bJoinIn = (Button)findViewById(R.id.bJoinIn);
        bRideShare = (Button)findViewById(R.id.ride_share);
        bBookNow.setTypeface(Typeface.createFromAsset(getAssets(),"eternalknightbold.ttf"));
        bJoinIn.setTypeface(Typeface.createFromAsset(getAssets(),"eternalknightbold.ttf"));

        bJoinIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent("cabme.cabmedashboard.JOININ");
                startActivity(activity);
            }
        });

        bBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent("cabme.cabmedashboard.MY_BOOKINGS");
                startActivity(activity);
            }
        });

        bRideShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent("cabme.cabmedashboard.PROFILE");
                startActivity(activity);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.book_a_cab_drawer_id) {
            delay_start_activity("cabme.cabmedashboard.BOOK_A_CAB", 300);
        }
        else if (id == R.id.login) {
            delay_start_activity("cabme.cabmedashboard.REGISTER_NOW", 300);
        }else if (id == R.id.post_a_cab_drawer_id) {
            //Intent open_main_activity=new Intent("cabme.cabmedashboard.LOGIN_ACTIVITY");
            //startActivity(open_main_activity);
        } else if (id == R.id.find_a_ride_drawer_id) {
            delay_start_activity("cabme.cabmedashboard.FIND_A_RIDE",300);

        } else if (id == R.id.profile_drawer_id) {
            delay_start_activity("cabme.cabmedashboard.PROFILE",300);
        } else if (id == R.id.myBookings) {
            delay_start_activity("cabme.cabmedashboard.MY_BOOKINGS",300);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    void delay_start_activity(final String intent, int delay){
        new Handler().postDelayed(new Runnable() {
            Intent open_main_activity=new Intent(intent);
            @Override
            public void run() {

                startActivity(open_main_activity);

            }
        },delay);

    }
}
