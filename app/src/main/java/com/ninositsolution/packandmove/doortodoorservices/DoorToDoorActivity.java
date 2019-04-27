package com.ninositsolution.packandmove.doortodoorservices;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.doortodoorservices.pojo.DoorServiceRequest;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.utils.Session;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorToDoorActivity extends AppCompatActivity implements OnItemClickListener {
    EditText pickupPlace, pickupDate, pickupTime, dropPlace,dropDate,dropTime, typesOfGoods,estimatedWeight,estimatedVolume,doorRemarks;
    String pickPlace,pickDate,pickTime,droppingPlace,droppingDate,droppingTime,goodsType,weight,volume,remark,uploaded_photos;
    Button doorSubmitBtn;
    ProgressBar progressBar;
    RecyclerView doorRecyclerView;
    private static final int REQUEST_CAMERA = 100;
    private static final int RESULT_LOAD_IMAGE = 101;
    private static final String TAG = "DoorToDoorActivity";
    ArrayList<String> doorArrayList;

    ArrayList<String> base64ArrayList;
    DoorRecyclerViewAdapter doorRecyclerViewAdapter;
    private Context context;
    POJOClass pojoClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_to_door);
        pickupPlace = findViewById(R.id.pickupPlace);
        pickupDate = findViewById(R.id.pickupDate);
        pickupTime = findViewById(R.id.pickupTime);
        dropPlace = findViewById(R.id.deliverPlace);
        dropDate = findViewById(R.id.deliverDate);
        dropTime = findViewById(R.id.deliverTime);
        typesOfGoods = findViewById(R.id.serviceGoodsType);
        estimatedWeight = findViewById(R.id.goodsWeight);
        estimatedVolume = findViewById(R.id.goodsVolume);
        progressBar = findViewById(R.id.door_progress);
        doorRemarks = findViewById(R.id.goodsRemarks);

        doorRecyclerView = findViewById(R.id.doorTodoorRecyclerView);
        context = DoorToDoorActivity.this;
        doorArrayList = new ArrayList<>();
        base64ArrayList = new ArrayList<>();


        ViewGroup.LayoutParams params = doorRecyclerView.getLayoutParams();
        params.height =500;
        doorRecyclerView.setLayoutParams(params);
        doorRecyclerViewAdapter = new DoorRecyclerViewAdapter(doorArrayList, context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        doorRecyclerView.setLayoutManager(linearLayoutManager);
        doorRecyclerView.setAdapter(doorRecyclerViewAdapter);


        doorSubmitBtn = findViewById(R.id.doorToDoor_submit_button);

        doorSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showProgressBar();

                pickPlace = pickupPlace.getText().toString();
                pickDate = pickupDate.getText().toString();
                pickTime = pickupTime.getText().toString();
                droppingDate = dropDate.getText().toString();
                droppingPlace = dropPlace.getText().toString();
                droppingTime = dropTime.getText().toString();
                goodsType = typesOfGoods.getText().toString();
                weight = estimatedWeight.getText().toString();
                volume = estimatedVolume.getText().toString();
                remark = doorRemarks.getText().toString();

                for (int i = 0; i< doorArrayList.size(); i++)
                {
                    base64ArrayList.add(convertTobase64(doorArrayList.get(i)));
                }

                uploaded_photos = base64ArrayList.toString();

                DoorServiceRequest doorServiceRequest = new DoorServiceRequest();
                doorServiceRequest.setUser_id(Session.getUserId(context));
                doorServiceRequest.setPickup_from(pickPlace);
                doorServiceRequest.setPickup_date(pickDate);
                doorServiceRequest.setPickup_time(pickTime);
                doorServiceRequest.setDelivery_to(droppingPlace);
                doorServiceRequest.setDelivery_date(droppingDate);
                doorServiceRequest.setDelivery_time(droppingTime);
                doorServiceRequest.setType_of_goods(goodsType);
                doorServiceRequest.setWeight(weight);
                doorServiceRequest.setVolume(volume);
                doorServiceRequest.setRemark(remark);
                doorServiceRequest.setUpload_photos(uploaded_photos);

                RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
                Call<POJOClass> DoorService = retrofitInterface.doorServiceApi("Bearer " + Session.getToken(context), doorServiceRequest);
                DoorService.enqueue(new Callback<POJOClass>() {
                    @Override
                    public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                    {
                        if (response.code()== 200)
                        {
                            pojoClass = response.body();
                            Log.i(TAG, "code : "+response.code());

                            if (pojoClass.getStatus().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();
                                Log.i(TAG, "Entered on success Status is: ->" + pojoClass.getStatus());
                                Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(DoorToDoorActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                hideProgressBar();
                                Log.i(TAG, "Entered on error Status is: ->" + pojoClass.getStatus());

                                Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();

                            }

                        }

                        else
                        {
                            hideProgressBar();
                            Toast.makeText(context, "Error code : "+response.code(), Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Error code : "+response.code());

                        }
                    }

                    @Override
                    public void onFailure(Call<POJOClass> call, Throwable t)
                    {
                        hideProgressBar();

                        Log.i(TAG,"Error is:->" + t);

                        Toast.makeText(getApplicationContext(),""+t, Toast.LENGTH_LONG).show();



                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view, int position)
    {
        captureImage();

    }

    @Override
    public void onRemoved(int position) {

        doorArrayList.remove(position);

        Log.i(TAG,"doorArrayList->" +doorArrayList);

        doorRecyclerViewAdapter = new DoorRecyclerViewAdapter(doorArrayList, context, this);
        doorRecyclerView.setAdapter(doorRecyclerViewAdapter);

    }


    private void captureImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            dialogShowPhoto();

        }

    }

    public void dialogShowPhoto() {
        String takephoto = "Take Photo";
        String chooseFromLibrary = "Choose from Gallery";
        String cancel = "cancel";
        String addPhoto = "add photo";
        final CharSequence[] items = {takephoto, chooseFromLibrary, cancel};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(addPhoto);
        final String finalTakephoto = takephoto;
        final String finalChooseFromLibrary = chooseFromLibrary;
        final String finalCancel = cancel;
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(finalTakephoto))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }

                else if (items[item].equals(finalChooseFromLibrary))
                {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, RESULT_LOAD_IMAGE);
                }

                else if (items[item].equals(finalCancel)) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override

protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode,data);

        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Matrix mat = new Matrix();
            mat.postRotate(Integer.parseInt("270"));
            Bitmap bMapRotate = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), mat, true);
            doorArrayList.add(getPath(context, data.getData()));
            Log.i(TAG,"doorArrayList->" +doorArrayList);
            doorRecyclerViewAdapter.notifyDataSetChanged();

        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImageURI = data.getData();
            doorArrayList.add(getPath(context, data.getData()));
            doorRecyclerViewAdapter.notifyDataSetChanged();
            Log.i(TAG,"doorArrayList->" +doorArrayList);
        }

    }


    private static String getPath(Context context, Uri data)
    {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( data, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ))
            {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString(column_index);
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;

    }







    private String convertTobase64(String path) {

        String base64 = "";
        File file = new File(path);
        try{

            byte[] buffer = new byte[(int) file.length() + 100];
            int length = new FileInputStream(file).read(buffer);
            base64 = "data:image/jpeg;base64,"+ Base64.encodeToString(buffer, 0, length,
                    Base64.DEFAULT);
            Log.d("base64 image", base64);
        }catch (Exception e){
            e.printStackTrace();
        }
        return base64;

    }

    private void showProgressBar ()
    {
        if (progressBar.getVisibility() == View.GONE)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar ()
    {
        if (progressBar.getVisibility() == View.VISIBLE)
        {
            progressBar.setVisibility(View.GONE);
        }


    }






}
