package com.ninositsolution.packandmove;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ResidentialAddRoomActivity extends AppCompatActivity {

    ImageView alertimageView;
    Button okButton;
    LinearLayout addPhoto;
    ImageView resultImage1,resultImage2,resultImage3,resultImage4,resultImage5;
    Button closeImage1,closeImage2,closeImage3,closeImage4,closeImage5;
    RelativeLayout layout1,layout2,layout3,layout4,layout5;
    static final int CAPTURE_IMAGE_REQUEST = 1;
    private static final String TAG = "ResidentialActivity";

    final int[] count = {1};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residential_add_room);
        alertimageView = findViewById(R.id.showAlert);
        addPhoto = findViewById(R.id.addPhotoLayout);
        resultImage1 = findViewById(R.id.image1);
        resultImage2 = findViewById(R.id.image2);
        resultImage3 = findViewById(R.id.image3);
        resultImage4 = findViewById(R.id.image4);
        resultImage5 = findViewById(R.id.image5);
        layout1 = findViewById(R.id.imageRelativeLayout1);
        layout1.setVisibility(View.GONE);

        layout2 = findViewById(R.id.imageRelativeLayout2);
        layout2.setVisibility(View.GONE);
        layout3 = findViewById(R.id.imageRelativeLayout3);
        layout3.setVisibility(View.GONE);
        layout4 = findViewById(R.id.imageRelativeLayout4);
        layout4.setVisibility(View.GONE);
        layout5 = findViewById(R.id.imageRelativeLayout5);
        layout5.setVisibility(View.GONE);

        closeImage1 = findViewById(R.id.closeCaptureImage1);
        closeImage2 = findViewById(R.id.closeCaptureImage2);
        closeImage3 = findViewById(R.id.closeCaptureImage3);
        closeImage4 = findViewById(R.id.closeCaptureImage4);
        closeImage5 = findViewById(R.id.closeCaptureImage5);




        alertimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    captureImage();

            }
        });

    }

    private void captureImage() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        else {

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");

        switch (count[0])
        {
            case 1:

                layout1.setVisibility(View.VISIBLE);

                resultImage1.setImageBitmap(imageBitmap);
                count[0]++;
                Log.i(TAG,"count is:->" +count[0]);

                closeImage1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout1.setVisibility(View.GONE);
                        count[0]--;
                        addPhoto.setVisibility(View.VISIBLE);
                        Log.i(TAG,"count is:->" +count[0]);

                    }
                });




                break;

            case 2:
                layout2.setVisibility(View.VISIBLE);
                resultImage2.setImageBitmap(imageBitmap);
                count[0]++;
                Log.i(TAG,"count is:->" +count[0]);



                closeImage2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout2.setVisibility(View.GONE);
                        count[0]--;
                        addPhoto.setVisibility(View.VISIBLE);

                        Log.i(TAG,"count is:->" +count[0]);
                    }
                });



                break;

            case 3:

                layout3.setVisibility(View.VISIBLE);

                resultImage3.setImageBitmap(imageBitmap);
                count[0]++;
                Log.i(TAG,"count is:->" +count[0]);


                closeImage3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout3.setVisibility(View.GONE);
                        count[0]--;
                        addPhoto.setVisibility(View.VISIBLE);

                        Log.i(TAG,"count is:->" +count[0]);
                    }
                });



                break;

            case 4:

                layout4.setVisibility(View.VISIBLE);

                resultImage4.setImageBitmap(imageBitmap);
                count[0]++;
                Log.i(TAG,"count is:->" +count[0]);

                closeImage4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout4.setVisibility(View.GONE);
                        count[0]--;
                        addPhoto.setVisibility(View.VISIBLE);

                        Log.i(TAG,"count is:->" +count[0]);
                    }
                });




                break;


            case 5:

                layout5.setVisibility(View.VISIBLE);
                resultImage5.setImageBitmap(imageBitmap);
                addPhoto.setVisibility(View.GONE);

                closeImage5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layout5.setVisibility(View.GONE);
                        count[0]--;
                        addPhoto.setVisibility(View.VISIBLE);

                        Log.i(TAG,"count is:->" +count[0]);
                    }
                });



                default:

                    break;

        }

    }




    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_alert_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        okButton = (Button)dialogView.findViewById(R.id.buttonOk);

        final AlertDialog alertDialog = builder.create();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            }
            else {

                displayMessage(getBaseContext(),"Camera function is required for this app to work properly");

            }
        }

    }

    private void displayMessage(Context context, String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }


}
