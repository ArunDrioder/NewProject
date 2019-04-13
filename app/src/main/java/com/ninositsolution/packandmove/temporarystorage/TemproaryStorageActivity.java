package com.ninositsolution.packandmove.temporarystorage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.temporarystorage.pojo.StorageRequest;
import com.ninositsolution.packandmove.utils.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemproaryStorageActivity extends AppCompatActivity {

    private static final String TAG = "StorageActivity";
    EditText storageDate,temporaryStorage,longStorage,typeOfGoods,storageVolume,storageWeight,storageLocation,storageRemarks;
    String user_id,date,tempstorage,longstorage,goodsType,storagevolume,weight,location,remarks;
    Button storageSubmit;
    ProgressBar progressBar;
    private POJOClass pojoClass;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temproary_storage);

        storageDate = findViewById(R.id.storageDate);
        temporaryStorage = findViewById(R.id.temporaryStorage);
        longStorage = findViewById(R.id.longStorage);
        typeOfGoods = findViewById(R.id.typeOfGoods);
        storageVolume = findViewById(R.id.storageVolume);
        storageWeight = findViewById(R.id.storageWeight);
        storageLocation = findViewById(R.id.storageLocation);
        storageRemarks = findViewById(R.id.storageRemarks);
        storageSubmit = findViewById(R.id.storage_submit_button);
        progressBar = findViewById(R.id.storage_progress);

        context = TemproaryStorageActivity.this;


        storageSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showProgressBar();

                user_id = Session.getUserId(context);
                date = storageDate.getText().toString();
                tempstorage = temporaryStorage.getText().toString();
                longstorage=  longStorage.getText().toString();
                goodsType = typeOfGoods.getText().toString();
                storagevolume = storageVolume.getText().toString();
                weight = storageWeight.getText().toString();
                location = storageLocation.getText().toString();
                remarks = storageRemarks.getText().toString();


                StorageRequest storageRequest= new StorageRequest();
                storageRequest.setUser_id(user_id);
                storageRequest.setStorage_date(date);
                storageRequest.setTemporary_storage(tempstorage);
                storageRequest.setLong_storage(longstorage);
                storageRequest.setType_of_goods(goodsType);
                storageRequest.setVolume(storagevolume);
                storageRequest.setWeight(weight);
                storageRequest.setPick_up_from(location);
                storageRequest.setRemark(remarks);

                RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
                Call<POJOClass> Storage =  retrofitInterface.storageApi("Bearer "+Session.getToken(context), storageRequest);
                Storage.enqueue(new Callback<POJOClass>() {
                    @Override
                    public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                    {
                        if (response.code()==200)
                        {
                            pojoClass = response.body();
                            Log.e(TAG, "code : "+response.code());

                            if (pojoClass.getStatus()!= null)
                            {
                                if (pojoClass.getStatus().equalsIgnoreCase("success"))
                                {
                                    hideProgressBar();
                                    Log.e(TAG, "Entered on success Status is: ->" + pojoClass.getStatus());
                                    Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(TemproaryStorageActivity.this, MainActivity.class);
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
                                Toast.makeText(getApplicationContext(),"Status Null", Toast.LENGTH_LONG).show();
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
