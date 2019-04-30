package com.ninositsolution.packandmove.packaging;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.packaging.pojo.PackageRequest;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.utils.Session;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackagingActivity extends AppCompatActivity {

    private static final String TAG = "PackageActivity";

    EditText packagingDate, packedLocation, goodsType, packageVolume, packageWeight, packageRemarks;
    final Calendar myCalendar = Calendar.getInstance();
    String user_id, token, dateOfPackaging, packageLocation, goodType, volumeOfPackaging, packageWeigh, remarks;
    Button submitPkgBtn;
    ProgressBar progressBar;
    ImageView package_back;
    Context context;
    private POJOClass pojoClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packaging);

        packagingDate = findViewById(R.id.packagingDate);
        packedLocation = findViewById(R.id.packedLocation);
        goodsType = findViewById(R.id.goodsType);
        packageVolume = findViewById(R.id.package_volume);
        progressBar = findViewById(R.id.package_progress);
        packageWeight = findViewById(R.id.packageWeight);
        packageRemarks = findViewById(R.id.package_remarks);
        submitPkgBtn = findViewById(R.id.package_submitBtn);
        package_back = findViewById(R.id.packaging_backArrow);
        context = PackagingActivity.this;


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }

            private void updateLabel()
            {
                String dateFormat = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                packagingDate.setText(simpleDateFormat.format(myCalendar.getTime()));
            }
        };

        packagingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(PackagingActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        package_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goBack();
            }
        });



        submitPkgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgressBar();

                user_id = Session.getUserId(context);
                token = Session.getToken(context);
                dateOfPackaging = packagingDate.getText().toString();
                packageLocation = packedLocation.getText().toString();
                goodType = goodsType.getText().toString();
                volumeOfPackaging = packageVolume.getText().toString();
                packageWeigh = packageWeight.getText().toString();
                remarks = packageRemarks.getText().toString();


                Log.e(TAG, "submit button clicked() ->");

                PackageRequest packageRequest = new PackageRequest();
                packageRequest.setUser_id(user_id);

                packageRequest.setDop(dateOfPackaging);
                packageRequest.setLocation(packageLocation);
                packageRequest.setType_of_goods(goodType);
                packageRequest.setVolume(volumeOfPackaging);
                packageRequest.setWeight(packageWeigh);
                packageRequest.setRemark(remarks);
                Log.e(TAG, "All textfields has been assigned and setted to request class");

                RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
               Call<POJOClass> Packaging = retrofitInterface.packageApi("Bearer "+ Session.getToken(context), packageRequest);
               Packaging.enqueue(new Callback<POJOClass>() {
                   @Override
                   public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                   {

                       if (response.code() == 200)
                       {
                           pojoClass = response.body();

                           Log.e(TAG, "code : "+response.code());

                           if (pojoClass.getStatus() != null)
                           {
                               if (pojoClass.getStatus().equalsIgnoreCase("success"))
                               {
                                   hideProgressBar();
                                   Log.e(TAG, "Entered on success Status is: ->" + pojoClass.getStatus());
                                   Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();
                                   Intent intent = new Intent(PackagingActivity.this, MainActivity.class);
                                   startActivity(intent);

                               }
                               else
                               {
                                       hideProgressBar();
                                       Log.e(TAG, "Entered on error Status is: ->" + pojoClass.getStatus());

                                       Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();

                               }
                           }
                           else
                           {
                               Toast.makeText(getApplicationContext(),"Status Null",Toast.LENGTH_LONG).show();

                           }

                       }
                       else
                       {
                           Toast.makeText(context, "Error code : "+response.code(), Toast.LENGTH_SHORT).show();
                           Log.e(TAG, "Error code : "+response.code());
                       }

                   }

                   @Override
                   public void onFailure(Call<POJOClass> call, Throwable t)
                   {
                       hideProgressBar();


                       Log.e(TAG,"Error is:->" + t);

                       Toast.makeText(getApplicationContext(),""+t, Toast.LENGTH_LONG).show();

                   }
               });


            }



        private void showProgressBar ()
        {
            if (progressBar.getVisibility() == View.GONE) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }

        private void hideProgressBar ()
        {
            if (progressBar.getVisibility() == View.VISIBLE) {
                progressBar.setVisibility(View.GONE);
            }


        }

    }
        );
}

    private void goBack()
    {
        super.onBackPressed();
    }
}
