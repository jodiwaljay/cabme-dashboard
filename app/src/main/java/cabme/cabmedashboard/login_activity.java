package cabme.cabmedashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jay on 11/23/2015.
 */
public class login_activity extends AppCompatActivity {

    VolleyController volleyController;
    ProgressDialog pDialog;
    EditText et_username,et_password;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button login;

        setContentView(R.layout.login_page);
        login = (Button) findViewById(R.id.login);
        et_username = (EditText) findViewById(R.id.input_email);
        et_password = (EditText) findViewById(R.id.input_password);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Logging in..");
        volleyController = new VolleyController();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                makeGetRequest(username,password);
            }
        });

    }

    private void makeGetRequest(final String username, final String password) {
        showDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Stored_Urls.login_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("get_request_response", response.toString());

                try {
                    // Getting json object
                    Boolean error = response.getBoolean("error");
                    String msg = response.getString("response_msg");

                    if(!error){
                        Intent open = new Intent("cabme.cabmedashboard.MAINACTIVITY");
                        startActivity(open);
                    }else{
                        Toast.makeText(login_activity.this,msg,Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error occured..Please try later", Toast.LENGTH_LONG).show();
                }
                hideDialog();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(login_activity.this,"Error occured..Please try later",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username);
                params.put("password", password);
                return params;
            }
        };
        volleyController.addToRequestQueue(jsonObjectRequest,"login_request");

    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.hide();
        }
    }
}
