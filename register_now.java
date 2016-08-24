package cabme.cabmedashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class register_now extends AppCompatActivity{

    Button bRegisterNow;
    TextView already_user,sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_now);
        SettingUpVariables();
        //bRegisterNow.setTypeface(Typeface.createFromAsset(getAssets(),"Exo2-Light.otf"));
        ClickListeners();
    }

    void ClickListeners(){
        already_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_main_activity=new Intent("cabme.cabmedashboard.BOOK_A_CAB");
                startActivity(open_main_activity);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_main_activity = new Intent("cabme.cabmedashboard.BOOK_A_CAB");
                startActivity(open_main_activity);
            }
        });
    }
    void SettingUpVariables(){
        bRegisterNow = (Button)findViewById(R.id.bRegisterNow);
        already_user = (TextView)findViewById(R.id.register_now_already_user);
        sign_in = (TextView)findViewById(R.id.register_now_sign_in);
    }
}
