package cabme.cabmedashboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

/**
 * Created by Jay on 11/23/2015.
 */
public class Splash extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);

            Thread timer = new Thread(){
                public void run(){
                    try{
                        sleep(2000);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    finally {
                        Intent open_main_activity=new Intent("cabme.cabmedashboard.");
                        startActivity(open_main_activity);
                        finish();
                    }
                }
            };
            timer.start();



        }
}
