package cabme.cabmedashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, dialog_fragment.NoticeDialogListener{

    ImageButton edit_profile_photo;
    ImageButton mImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("My Account");
        setSupportActionBar(toolbar);

        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.profile_user);
        mImage = (ImageButton) findViewById(R.id.bBookNow);
        edit_profile_photo = (ImageButton)findViewById(R.id.edit_profile_photo);
        edit_profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoticeDialog();

            }
        });

        mImage.setImageBitmap(new CircularBitmap().getCircleBitmap(bm));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new dialog_fragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if request code is same we pass as argument in startActivityForResult

        if(requestCode==333){
            //Create an instance of bundle and get the returned data
            try{Bundle extras = data.getExtras();
                //get the cropped bitmap from extras
                Bitmap thePic = extras.getParcelable("profile_photo");
                //set image bitmap to image view
                mImage.setImageBitmap(new CircularBitmap().getCircleBitmap(thePic));}
            catch (NullPointerException e){
                Log.e("j",e.toString());
            }
        }
    }
    //create helping method cropCapturedImage(Uri picUri)
    public void cropCapturedImage(Uri picUri){
        //call the standard capture_and_crop action intent
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        //indicate image type and Uri of image
        cropIntent.setDataAndType(picUri, "image/*");
        //set capture_and_crop properties
        cropIntent.putExtra("capture_and_crop", "true");
        //indicate aspect of desired capture_and_crop
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        //indicate output X and Y
        cropIntent.putExtra("outputX", 256);
        cropIntent.putExtra("outputY", 256);
        //retrieve data on return
        cropIntent.putExtra("return-data", true);
        //start the activity - we handle returning in onActivityResult
        startActivityForResult(cropIntent, 2);
    }

    @Override
    public void onItemChosen(DialogFragment dialog, int PROFILE_PHOTO) {
        if(PROFILE_PHOTO == 0){
            Intent i = new Intent(getApplicationContext(),capture_and_crop.class);
            startActivityForResult(i, 333);
        }
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
                Log.e("Opening activity", "Opened or not");
                finish();
            }
        },delay);

    }
}
