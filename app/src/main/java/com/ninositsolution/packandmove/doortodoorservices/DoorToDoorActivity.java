package com.ninositsolution.packandmove.doortodoorservices;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.doortodoorservices.pojo.DoorServiceRequest;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.utils.Session;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorToDoorActivity extends AppCompatActivity implements OnItemClickListener {
    EditText pickupPlace, pickupDate, pickupTime, dropPlace,dropDate,dropTime, typesOfGoods,estimatedWeight,estimatedVolume,doorRemarks;
    String pickPlace,pickDate,pickTime,droppingPlace,droppingDate,droppingTime,goodsType,weight,volume,remark,uploaded_photos;
    Button doorSubmitBtn;
    ImageView doorToDoor_backArrow;
    ProgressBar progressBar;
    RecyclerView doorRecyclerView;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();
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
        doorToDoor_backArrow =findViewById(R.id.doorToDoor_backArrow);
        doorRecyclerView = findViewById(R.id.doorTodoorRecyclerView);
        context = DoorToDoorActivity.this;
        doorArrayList = new ArrayList<>();
        base64ArrayList = new ArrayList<>();


        doorToDoor_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });








        final DatePickerDialog.OnDateSetListener datePickup = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatePickLabel();


            }

            private void updatePickLabel() {
                String pickDateFormat = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pickDateFormat, Locale.US);

                pickupDate.setText(simpleDateFormat.format(myCalendar.getTime()));


            }
        };

        pickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(DoorToDoorActivity.this,datePickup,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();



            }
        });



        final DatePickerDialog.OnDateSetListener dateDelivery = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDropLabel();

            }

            private void updateDropLabel()
            {
                String deliverDateFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(deliverDateFormat, Locale.US);
                dropDate.setText(sdf.format(myCalendar2.getTime()));


            }
        };





        dropDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(DoorToDoorActivity.this,dateDelivery,myCalendar2.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH)).show();

            }
        });



        pickupTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(DoorToDoorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        pickupTime.setText( selectedHour + ":" + selectedMinute);


                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        dropTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(DoorToDoorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        dropTime.setText( selectedHour + ":" + selectedMinute);


                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });



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
                doorServiceRequest.setUpload_photos(base64ArrayList);

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

    private void goBack()
    {
        super.onBackPressed();
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


    private void captureImage()
    {
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
            Uri tempUri = getImageUri(getApplicationContext(), photo);

            Log.i(TAG,"path is: ->" +getRealPathFromURI(tempUri));
            doorArrayList.add(getRealPathFromURI(tempUri));
            doorRecyclerViewAdapter.notifyDataSetChanged();
            Log.i(TAG,"doorArrayList->" +doorArrayList);


        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImageURI = data.getData();
            doorArrayList.add(getRealPathFromURI(data.getData()));
            doorRecyclerViewAdapter.notifyDataSetChanged();
            Log.i(TAG,"doorArrayList->" +doorArrayList);
        }

    }


    public Uri getImageUri(Context inContext, Bitmap inImage)

    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }



    public String getRealPathFromURI(Uri uri)

    {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    private String convertTobase64(String path)

    {

        String base64 = "";
        File file = new File(path);
        try{

            byte[] buffer = new byte[(int) file.length() + 100];
            int length = new FileInputStream(file).read(buffer);
//            base64 = "data:image/jpeg;base64,"+ Base64.encodeToString(buffer, 0, length,
//                    Base64.DEFAULT);

            base64 =  Base64.encodeToString(buffer, 0, length,
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
