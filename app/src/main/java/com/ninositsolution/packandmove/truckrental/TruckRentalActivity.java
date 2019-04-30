package com.ninositsolution.packandmove.truckrental;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.ninositsolution.packandmove.doortodoorservices.DoorRecyclerViewAdapter;
import com.ninositsolution.packandmove.doortodoorservices.DoorToDoorActivity;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.truckrental.pojo.TruckRentalRequest;
import com.ninositsolution.packandmove.utils.Session;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TruckRentalActivity extends AppCompatActivity implements OnItemClickListener {

    LinearLayout yesLabourLayout;
    RadioGroup labourSupplyRadioGrp;
    RadioButton labourYesRadioBtn;
    RadioButton labourNoRadioBtn;
    ImageView truckRental_backArrow;
    RecyclerView truckRentalRecyclerView;
    TruckRecyclerViewAdapter truckRecyclerViewAdapter;
    Spinner truckType;
    RadioGroup truckRental_package;
    ProgressBar progressBar;
    RadioButton openType, boxType;
    EditText rentalPickupPlace,rentalDeliveryPlace,rentalAddress, rentalDistance, noOfRentalLabours,rentalGoodsType,rentalGoodsQuantity,rentalGoodsWeight,rentalGoodsVolume, rentalRemarks;
    Button truckRentalSubmit;
    String rentalTruckType,rentalPackageType,rentalPickPlace,rentalDropPlace,rentalDropAddress,labourSupply,laboursCount,rentalGoods,rentalQuantity,rentalWeight,rentalVolume, distance, remarks, uploaded_photos;
    private static final int REQUEST_CAMERA = 100;
    private static final int RESULT_LOAD_IMAGE = 101;
    private static final String TAG = "TruckActivity";

    ArrayList<String> truckArrayList = new ArrayList<>();

    ArrayList<String> truckBase64ArrayList = new ArrayList<>();



    Context context;
    POJOClass pojoClass;

    String[] typeOfTruck = { "Hijet", "1Oft Truck", "14ft Truck", "2Oft Truck","32 ft Truck","40 ft Truck"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_rental);

        context = TruckRentalActivity.this;

        yesLabourLayout = findViewById(R.id.yesLabourLayout);
        yesLabourLayout.setVisibility(View.GONE);
        labourSupplyRadioGrp = findViewById(R.id.labourSupplyRadioGrp);

        labourYesRadioBtn = findViewById(R.id.labourYesRadioBtn);
        labourNoRadioBtn = findViewById(R.id.labourNoRadioBtn);
        truckRental_package = findViewById(R.id.truckRental_packageType);
        openType = findViewById(R.id.openPackageType);
        boxType = findViewById(R.id.boxPackageType);
        truckRental_backArrow = findViewById(R.id.truckRental_backArrow);

        truckType = findViewById(R.id.truck_rental_trucktype);
        truckRentalRecyclerView = findViewById(R.id.truckRental_RecyclerView);
        rentalPickupPlace = findViewById(R.id.truckRental_pickupPlace);
        rentalDeliveryPlace = findViewById(R.id.truckRental_deliveryPlace);
        rentalAddress = findViewById(R.id.truckRental_deliveryAddress);
        noOfRentalLabours = findViewById(R.id.noOfRentalLabours);
        rentalGoodsType = findViewById(R.id.rental_typesOfGoods);
        rentalGoodsQuantity = findViewById(R.id.rental_quantity);
        rentalGoodsWeight = findViewById(R.id.rental_weight);
        rentalGoodsVolume = findViewById(R.id.rental_volume);
        rentalDistance = findViewById(R.id.truckRental_distance);
        rentalRemarks = findViewById(R.id.truckRental_remarks);
        progressBar = findViewById(R.id.truckRental_progress);

        truckRental_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });






        ViewGroup.LayoutParams params = truckRentalRecyclerView.getLayoutParams();
        params.height =500;
        truckRentalRecyclerView.setLayoutParams(params);
        truckRecyclerViewAdapter = new TruckRecyclerViewAdapter(truckArrayList, context, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        truckRentalRecyclerView.setLayoutManager(linearLayoutManager);
        truckRentalRecyclerView.setAdapter(truckRecyclerViewAdapter);




        ArrayAdapter truckAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typeOfTruck);
        truckAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        truckType.setAdapter(truckAdapter);




        labourSupplyRadioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.labourYesRadioBtn)
                {
                    if (yesLabourLayout.getVisibility() == View.GONE)
                    {
                        yesLabourLayout.setVisibility(View.VISIBLE);

                        labourSupply = "1";


                    }
                    else
                    {
                        yesLabourLayout.setVisibility(View.GONE);
                    }
                }

                if (checkedId == R.id.labourNoRadioBtn)
                {
                    if (yesLabourLayout.getVisibility() == View.VISIBLE)
                    {
                        yesLabourLayout.setVisibility(View.GONE);

                        labourSupply = "0";



                    }
                }

            }
        });



        truckRental_package.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (checkedId == R.id.openPackageType)
                {
                    rentalPackageType = "open";

                }

                if (checkedId == R.id.boxPackageType)
                {
                    rentalPackageType = "box";
                }

            }
        });







        truckRentalSubmit = findViewById(R.id.truckRental_submitBtn);
        truckRentalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showProgressBar();

                rentalTruckType = truckType.getSelectedItem().toString();
                rentalPickPlace = rentalPickupPlace.getText().toString();
                rentalDropPlace = rentalDeliveryPlace.getText().toString();
                rentalDropAddress = rentalAddress.getText().toString();
                laboursCount = noOfRentalLabours.getText().toString();
                rentalGoods = rentalGoodsType.getText().toString();
                rentalQuantity = rentalGoodsQuantity.getText().toString();
                rentalWeight = rentalGoodsWeight.getText().toString();
                rentalVolume = rentalGoodsVolume.getText().toString();
                distance = rentalDistance.getText().toString();
                remarks = rentalRemarks.getText().toString();

                for ( int i =0; i< truckArrayList.size(); i++)
                {
                    truckBase64ArrayList.add(convertTobase64(truckArrayList.get(i)));
                }
                uploaded_photos =  truckBase64ArrayList.toString();

                TruckRentalRequest truckRentalRequest = new TruckRentalRequest();
                truckRentalRequest.setUser_id(Session.getUserId(context));
                truckRentalRequest.setTruck_type(rentalTruckType);
                truckRentalRequest.setRental_pack_type(rentalPackageType);
                truckRentalRequest.setPickup_from(rentalPickPlace);
                truckRentalRequest.setDelivery_to(rentalDropPlace);
                truckRentalRequest.setDelivery_address(rentalDropAddress);
                truckRentalRequest.setLabour_supply(labourSupply);
                truckRentalRequest.setNo_of_labour(laboursCount);
                truckRentalRequest.setQuantity(rentalQuantity);
                truckRentalRequest.setWeight(rentalWeight);
                truckRentalRequest.setVolume(rentalVolume);
                truckRentalRequest.setType_of_goods(rentalGoods);
                truckRentalRequest.setDistance_meter(distance);
                truckRentalRequest.setSpecial_instruction(remarks);
                truckRentalRequest.setUpload_photo(uploaded_photos);

                RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
                Call<POJOClass> TruckRental = retrofitInterface.truckRentalApi("Bearer "+ Session.getToken(context), truckRentalRequest);
                TruckRental.enqueue(new Callback<POJOClass>() {
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
                                Intent intent = new Intent(TruckRentalActivity.this, MainActivity.class);
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
    public void onRemoved(int position)
    {
        truckArrayList.remove(position);
        truckRecyclerViewAdapter = new TruckRecyclerViewAdapter(truckArrayList,context,this);
        truckRentalRecyclerView.setAdapter(truckRecyclerViewAdapter);

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

        if (requestCode == REQUEST_CAMERA  && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Matrix mat = new Matrix();
            mat.postRotate(Integer.parseInt("270"));
            Bitmap bMapRotate = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), mat, true);
            Uri tempUri = getImageUri(getApplicationContext(), photo);

            Log.i(TAG,"path is: ->" +getRealPathFromURI(tempUri));
            truckArrayList.add(getRealPathFromURI(tempUri));
            truckRecyclerViewAdapter.notifyDataSetChanged();
        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImageURI = data.getData();
            truckArrayList.add(getRealPathFromURI(data.getData()));
            truckRecyclerViewAdapter.notifyDataSetChanged();
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
