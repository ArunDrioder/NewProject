package com.ninositsolution.packandmove.longdistancetransportation;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.longdistancetransportation.pojo.LongDistanceRequest;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.truckrental.TruckRentalActivity;
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

public class LongDistanceActivity extends AppCompatActivity implements OnItemClickListener {

    LinearLayout packagingYesLayout;
    RadioGroup pickupElevatorRadioGrp, dropElevatorRadioGrp, packagingRadioGroup;
    RadioButton packagingYesBtn,packagingNoBtn,pickupYesElevatorRadioBtn,pickupNoElevatorRadioBtn,dropYesElevatorRadioBtn,dropNoElevatorRadioBtn;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();
    ProgressBar progressBar;
    EditText pickupFrom,pickupFloor,deliveryTo,deliveryFloor,longDistanceWeight,longDistanceVolume,longDistanceQuantity,longDistanceNoOfPackages,longDistanceLabour,longDistanceRemarks, longPickupDate, longDeliveryDate;
    String longPickup,longDelivery,longPickupFloor,longDeliveryFloor,longPickupElevator,longDeliveryElevator,longPickDate,longDeliverDate,longNoOfPackages,longVolume,longWeight,longQuantity,longGoodsType,longPackage,longLabour,longRemarks,longUploadPhotos;

    Spinner longDistanceGoodsTypeSpin;
    ImageView longDistance_backArrow;
    RecyclerView longDistanceRecyclerView;
    Button longDistanceSubmit;
    LongDistanceRecyclerViewAdapter longDistanceRecyclerViewAdapter;
    private static final int REQUEST_CAMERA = 100;
    private static final int RESULT_LOAD_IMAGE = 101;
    private static final String TAG = "LongDistanceActivity";

    ArrayList<String> longDistanceArrayList = new ArrayList<>();

    ArrayList<String> longDistanceBase64ArrayList = new ArrayList<>();
    private Context context;
    POJOClass pojoClass;

    String[]  typesOfLongDistanceGoods = {"Household","Machinery","Fragile Items","Medical Items","General Cargo","Heavy Equipments","Beverage","Musical Instrument","Tele-Communication","Stationary","Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_distance);
        context  = LongDistanceActivity.this;

        packagingYesLayout = findViewById(R.id.packagingYesLayout);
        packagingYesLayout.setVisibility(View.GONE);
        packagingRadioGroup = findViewById(R.id.packagingRadioGrp);
        pickupFrom = findViewById(R.id.longDistance_pickupFrom);
        pickupFloor = findViewById(R.id.pickup_floor);
        deliveryTo = findViewById(R.id.longDistance_delivery);
        deliveryFloor = findViewById(R.id.delivery_floor);
        longDistanceWeight = findViewById(R.id.longDistsanceWeight);
        longDistanceVolume = findViewById(R.id.longDistanceVolume);
        longDistanceQuantity = findViewById(R.id.longDistanceQuantity);
        longDistanceNoOfPackages = findViewById(R.id.longDistance_noOfPackages);
        longDistanceLabour = findViewById(R.id.longDistance_labour);
        longDistanceRemarks = findViewById(R.id.longDistance_remarks);
        longDistanceRecyclerView = findViewById(R.id.longDistance_RecyclerView);
        progressBar = findViewById(R.id.longDistance_progress);


        ViewGroup.LayoutParams params = longDistanceRecyclerView.getLayoutParams();
        params.height =500;
        longDistanceRecyclerView.setLayoutParams(params);
        longDistanceRecyclerViewAdapter = new LongDistanceRecyclerViewAdapter(longDistanceArrayList,context,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        longDistanceRecyclerView.setLayoutManager(linearLayoutManager);
        longDistanceRecyclerView.setAdapter(longDistanceRecyclerViewAdapter);

        ArrayAdapter longDistanceAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
        longDistanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        longDistanceGoodsTypeSpin.setAdapter(longDistanceAdapter);






        longDistanceGoodsTypeSpin = findViewById(R.id.longDistance_goodsTypeSpin);


        pickupElevatorRadioGrp = findViewById(R.id.pickup_elevator_radioGrp);
        pickupYesElevatorRadioBtn = findViewById(R.id.pickup_YesElevatorRadioBtn);
        pickupNoElevatorRadioBtn = findViewById(R.id.pickup_NoElevatorRadioBtn);
        dropYesElevatorRadioBtn = findViewById(R.id.dropYesElevatorRadioBtn);
        dropNoElevatorRadioBtn = findViewById(R.id.dropNoElevatorRadioBtn);

        dropElevatorRadioGrp = findViewById(R.id.drop_elevator_radioGrp);
        packagingYesBtn = findViewById(R.id.packagingYesRadioBtn);
        packagingNoBtn = findViewById(R.id.packagingNoRadioBtn);
        longPickupDate = findViewById(R.id.longTransport_pickupDate);
        longDeliveryDate = findViewById(R.id.longTransport_dropDate);
        longDistance_backArrow = findViewById(R.id.longDistance_backArrow);
        longDistanceSubmit = findViewById(R.id.longDistance_submit_button);

        longDistance_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });






        final DatePickerDialog.OnDateSetListener datePickup = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatePickLabel();


            }

            private void updatePickLabel()
            {
                String pickDateFormat = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pickDateFormat, Locale.US);

                longPickupDate.setText(simpleDateFormat.format(myCalendar.getTime()));


            }
        };

        longPickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(LongDistanceActivity.this,datePickup,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

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
                longDeliveryDate.setText(sdf.format(myCalendar2.getTime()));


            }
        };

      longDeliveryDate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v)
          {
              new DatePickerDialog(LongDistanceActivity.this,dateDelivery,myCalendar2.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH)).show();



          }
      });


      packagingRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.packagingYesRadioBtn)
                {
                    if (packagingYesLayout.getVisibility()==View.GONE)
                    {
                        packagingYesLayout.setVisibility(View.VISIBLE);

                        longPackage = "1";

                    }
                    else
                    {
                        packagingYesLayout.setVisibility(View.GONE);
                    }
                }
                if (checkedId == R.id.packagingNoRadioBtn)
                {
                    if (packagingYesLayout.getVisibility() == View.VISIBLE)
                    {
                        packagingYesLayout.setVisibility(View.GONE);

                        longPackage = "0";
                    }

                }
            }
        }







        );


      pickupElevatorRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {

              if (checkedId == R.id.pickup_YesElevatorRadioBtn)
              {
                  longPickupElevator = "1";

              }

              if (checkedId == R.id.pickup_NoElevatorRadioBtn)
              {
                  longPickupElevator = "0";

              }



          }
      });



      dropElevatorRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
              if (checkedId == R.id.dropYesElevatorRadioBtn)
              {
                  longDeliveryElevator = "1";
              }

              if (checkedId == R.id.dropNoElevatorRadioBtn)
              {
                  longDeliveryElevator = "0";
              }

          }
      });


      longDistanceSubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v)
          {
              showProgressBar();
              longPickup = pickupFrom.getText().toString();
              longDelivery = deliveryTo.getText().toString();
              longPickupFloor = pickupFloor.getText().toString();
              longDeliveryFloor = deliveryFloor.getText().toString();
              longPickDate = longPickupDate.getText().toString();
              longDeliverDate = longDeliveryDate.getText().toString();
              longNoOfPackages = longDistanceNoOfPackages.getText().toString();
              longVolume = longDistanceVolume.getText().toString();
              longWeight = longDistanceWeight.getText().toString();
              longQuantity = longDistanceQuantity.getText().toString();
              longGoodsType = longDistanceGoodsTypeSpin.getSelectedItem().toString();
              longLabour = longDistanceLabour.getText().toString();
              longRemarks = longDistanceRemarks.getText().toString();
              for ( int i =0; i< longDistanceArrayList.size(); i++)
              {
                  longDistanceBase64ArrayList.add(convertTobase64(longDistanceArrayList.get(i)));
              }
              longUploadPhotos = longDistanceBase64ArrayList.toString();

              LongDistanceRequest longDistanceRequest = new LongDistanceRequest();
              longDistanceRequest.setUser_id(Session.getUserId(context));
              longDistanceRequest.setPickup_from(longPickup);
              longDistanceRequest.setDelivery_to(longDelivery);
              longDistanceRequest.setPickup_floor(longPickupFloor);
              longDistanceRequest.setDeliver_floor(longDeliveryFloor);
              longDistanceRequest.setPickup_elevator(longPickupElevator);
              longDistanceRequest.setDeliver_elevator(longDeliveryElevator);
              longDistanceRequest.setPickup_date(longPickDate);
              longDistanceRequest.setDelivery_date(longDeliverDate);
              longDistanceRequest.setNo_of_packages(longNoOfPackages);
              longDistanceRequest.setVolume(longVolume);
              longDistanceRequest.setWeight(longWeight);
              longDistanceRequest.setQuantity(longQuantity);
              longDistanceRequest.setType_of_goods(longGoodsType);
              longDistanceRequest.setPackagee(longPackage);
              longDistanceRequest.setLabour(longLabour);
              longDistanceRequest.setSpecial_instruction(longRemarks);
              longDistanceRequest.setUpload_photos(longUploadPhotos);

              RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
              Call<POJOClass> LongDistance = retrofitInterface.longDistanceApi("Bearer "+Session.getToken(context), longDistanceRequest);
              LongDistance.enqueue(new Callback<POJOClass>() {
                  @Override
                  public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                  {
                      if (response.code() == 200)
                      {
                          pojoClass = response.body();
                          Log.i(TAG, "code : "+response.code());

                          if (pojoClass.getStatus().equalsIgnoreCase("success"))
                          {
                              hideProgressBar();
                              Log.i(TAG, "Entered on success Status is: ->" + pojoClass.getStatus());
                              Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();
                              Intent intent = new Intent(LongDistanceActivity.this, MainActivity.class);
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

    private void captureImage()
    {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else
        {
            dialogShowPhoto();

        }
    }

    private void dialogShowPhoto()
    {
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
            public void onClick(DialogInterface dialog, int item)
            {
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
    public void onRemoved(int position)
    {
        longDistanceArrayList.remove(position);
        longDistanceRecyclerViewAdapter = new LongDistanceRecyclerViewAdapter(longDistanceArrayList,context,this);
        longDistanceRecyclerView.setAdapter(longDistanceRecyclerViewAdapter);


    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode,data);

        if (requestCode == REQUEST_CAMERA  && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Matrix mat = new Matrix();
            mat.postRotate(Integer.parseInt("270"));
            Bitmap bMapRotate = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), mat, true);
            Uri tempUri = getImageUri(getApplicationContext(), photo);
            Log.i(TAG,"path is: ->" +getRealPathFromURI(tempUri));
            longDistanceArrayList.add(getRealPathFromURI(tempUri));
            longDistanceRecyclerViewAdapter.notifyDataSetChanged();


        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImageURI = data.getData();
            longDistanceArrayList.add(getRealPathFromURI(data.getData()));
            longDistanceRecyclerViewAdapter.notifyDataSetChanged();

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
