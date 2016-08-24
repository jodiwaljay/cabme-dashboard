package cabme.cabmedashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TabHost;

/**
 * Created by Jay on 11/23/2015.
 */
public class my_bookings extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_bookings);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Bookings");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("tag1");
        spec.setIndicator("Upcoming Rides");
        spec.setContent(R.id.tab1);
        tabHost.addTab(spec);
        TabHost.TabSpec spec2 = tabHost.newTabSpec("tag1");
        spec2.setIndicator("Past Rides");
        spec2.setContent(R.id.tab1);
        tabHost.addTab(spec2);
        TabHost.TabSpec spec3 = tabHost.newTabSpec("tag1");
        spec3.setIndicator("My Favourite Rides");
        spec3.setContent(R.id.tab3);
        tabHost.addTab(spec3);
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
            Intent open_main_activity = new Intent(intent);

            @Override
            public void run() {

                startActivity(open_main_activity);

                finish();
            }
        }, delay);

    }

}
