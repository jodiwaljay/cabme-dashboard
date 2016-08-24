package cabme.cabmedashboard;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class capture_and_crop extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeControls();


    }

    private void initializeControls() {

    /* create an instance of intent
     * pass action android.media.action.IMAGE_CAPTURE
     * as argument to launch camera
     */
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
    /*create instance of File with name img.jpg*/
                File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
    /*put uri as extra in intent object*/
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
    /*start activity for result pass intent as argument and request code */
                startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if request code is same we pass as argument in startActivityForResult
        if(requestCode==1){
            //create instance of File with same name we created before to get image from storage
            File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
            //Crop the captured image using an other intent
            try {

    /*the user's device may not support cropping*/
                if(resultCode == RESULT_OK)
                    cropCapturedImage(Uri.fromFile(file));
            }
            catch(ActivityNotFoundException aNFE){
                //display an error message if user device doesn't support
                String errorMessage = "Sorry - your device doesn't support the capture_and_crop action!";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
            catch(NullPointerException e){
                Log.e("jay",e.toString());
            }
        }
        if(requestCode==2){
            //Create an instance of bundle and get the returned data
            try{
                //set image bitmap to image view
                sendBackImage(data);
                }
            catch (NullPointerException e){
                Log.e("jay",e.toString());
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

    public void sendBackImage(Intent data){
        Intent returnIntent = new Intent();

        //get the cropped bitmap from extras

        Bundle extras = data.getExtras();
        Bitmap thePic = extras.getParcelable("data");

        returnIntent.putExtra("profile_photo",thePic);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

}