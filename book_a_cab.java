package cabme.cabmedashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Jay on 11/23/2015.
 */
public class book_a_cab extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RadioButton OneWay, TwoWay, MultiDestin;
    TextView return_details;
    EditText date_travel_return, time_travel_return;
    LinearLayout book_a_cab_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_cab_container);
        SettingUpVariables();
        ClickListeners();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){


            @Override
            public void onDrawerSlide(View drawerView,float f) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerSlide(drawerView, f);

                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerOpened(View DrawerView) {
                super.onDrawerOpened(DrawerView);
            }

        };

        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void ClearRadio(){
        OneWay.setChecked(false);
        TwoWay.setChecked(false);
        MultiDestin.setChecked(false);
    }
    public void TwoWayDetails(Boolean trait){
        int visibility;
        if(trait){
            visibility = View.VISIBLE;
        }
        else {
            visibility = View.GONE;
        }
        date_travel_return.setVisibility(visibility);
        time_travel_return.setVisibility(visibility);
        return_details.setVisibility(visibility);

    }
    public void SettingUpVariables(){
        OneWay = (RadioButton)findViewById(R.id.OneWay);
        TwoWay = (RadioButton)findViewById(R.id.TwoWay);
        MultiDestin = (RadioButton)findViewById(R.id.MultiDestin);
        date_travel_return = (EditText)findViewById(R.id.date_travel_return);
        time_travel_return = (EditText)findViewById(R.id.time_travel_return);
        return_details = (TextView)findViewById(R.id.return_details);
        book_a_cab_layout = (LinearLayout)findViewById(R.id.book_a_cab_layout);
    }
    public void ClickListeners(){
        OneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearRadio();
                OneWay.setChecked(true);
                TwoWayDetails(false);
            }
        });

        TwoWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearRadio();
                TwoWay.setChecked(true);
                TwoWayDetails(true);
            }
        });

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
