package com.ninositsolution.packandmove.laboursupply;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.laboursupply.pojo.LabourSupplyRequest;
import com.ninositsolution.packandmove.pojo.POJOClass;
import com.ninositsolution.packandmove.retrofit.RetrofitClient;
import com.ninositsolution.packandmove.retrofit.RetrofitInterface;
import com.ninositsolution.packandmove.utils.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LabourSupplyActivity extends AppCompatActivity {

    private static final String TAG = "LabourActivity";


    RadioGroup labourSupplyGrp,containerSize;
    ProgressBar progressBar;
    RadioButton containerLoading,containerUnloading, generalLabour, twentySize, fortySize;
    LinearLayout containerSizeLayout, containerWeight, containerVolume,containerQuantity, noOflabours,noOfHours,noOfDays,noOfFloors;
    EditText weightofContainer,volumeOfContainer,quantityOfContainer,goodsType,workPlace,containerDistance,labours,labourHours,labourDays,floors, labour_remarks;
    String supplyType, size, weight,volume,quantity,type,place,distance,totalLabours,hrs,days,floor, labourRemarks;
    Button labour_submit;
    POJOClass pojoClass;

     Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_supply);

        labourSupplyGrp = findViewById(R.id.labourSupplyRadioGroup);
        containerSize = findViewById(R.id.containerSizeRadioGroup);
        containerLoading = findViewById(R.id.containerLoadingRadioBtn);
        containerUnloading = findViewById(R.id.containerUnLoadingRadioBtn);
        generalLabour = findViewById(R.id.generalLabourRadioBtn);
        twentySize = findViewById(R.id.button_twenty);
        fortySize = findViewById(R.id.button_fourty);
        containerSizeLayout = findViewById(R.id.containerSizeLayout);
        containerWeight = findViewById(R.id.containerWeightLayout);
        containerVolume = findViewById(R.id.containerVolumeLayout);
        containerQuantity = findViewById(R.id.containerQuantityLayout);
        noOflabours = findViewById(R.id.noOfLabourLayout);
        noOfHours = findViewById(R.id.noOfHoursLayout);
        noOfDays = findViewById(R.id.noOfDaysLayout);
        noOfFloors = findViewById(R.id.noOfFloorsLayout);
        weightofContainer = findViewById(R.id.containerWeight);
        volumeOfContainer = findViewById(R.id.containerVolume);
        quantityOfContainer = findViewById(R.id.containerQuantity);
        goodsType = findViewById(R.id.containerGoodsType);
        workPlace = findViewById(R.id.containerPlace);
        containerDistance = findViewById(R.id.containerDistance);
        labours = findViewById(R.id.noOfLabours);
        labourHours = findViewById(R.id.hours);
        labourDays = findViewById(R.id.days);
        floors = findViewById(R.id.floors);
        labour_remarks = findViewById(R.id.labour_remarks);
        labour_submit = findViewById(R.id.labour_supply_submit_button);
        progressBar = findViewById(R.id.labour_progress);

        context = LabourSupplyActivity.this;




        labourSupplyGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.containerUnLoadingRadioBtn)
                {
                    twentySize.setChecked(false);
                    fortySize.setChecked(false);
                }

                supplyType = "Container Unloading";


                if (containerSizeLayout.getVisibility() == View.GONE)
                {
                    containerSizeLayout.setVisibility(View.VISIBLE);
                }

                if (containerWeight.getVisibility() == View.GONE)
                {
                    containerWeight.setVisibility(View.VISIBLE);
                }

                if (noOflabours.getVisibility() == View.VISIBLE)
                {
                    noOflabours.setVisibility(View.GONE);
                }


                if (containerVolume.getVisibility() == View.GONE)
                {
                    containerVolume.setVisibility(View.VISIBLE);
                }

                if (noOfHours.getVisibility() == View.VISIBLE)
                {
                    noOfHours.setVisibility(View.GONE);
                }




                if (containerQuantity.getVisibility() == View.GONE)
                {
                    containerQuantity.setVisibility(View.VISIBLE);
                }

                if (noOfDays.getVisibility() == View.VISIBLE)
                {
                    noOfDays.setVisibility(View.GONE);
                }
                if (noOfFloors.getVisibility() == View.VISIBLE)
                {
                    noOfFloors.setVisibility(View.GONE);
                }


                if (checkedId == R.id.containerLoadingRadioBtn)
                {
                    twentySize.setChecked(false);
                    fortySize.setChecked(false);
                }

                supplyType = "Container Loading";

                if (containerSizeLayout.getVisibility() == View.GONE)
                {
                    containerSizeLayout.setVisibility(View.VISIBLE);
                }

                if (containerWeight.getVisibility() == View.GONE)
                {
                    containerWeight.setVisibility(View.VISIBLE);
                }

                if (noOflabours.getVisibility() == View.VISIBLE)
                {
                    noOflabours.setVisibility(View.GONE);
                }


                if (containerVolume.getVisibility() == View.GONE)
                {
                    containerVolume.setVisibility(View.VISIBLE);
                }

                if (noOfHours.getVisibility() == View.VISIBLE)
                    noOfHours.setVisibility(View.GONE);



                if (containerQuantity.getVisibility() == View.GONE)
                {
                    containerQuantity.setVisibility(View.VISIBLE);
                }

                if (noOfDays.getVisibility() == View.VISIBLE)
                {
                    noOfDays.setVisibility(View.GONE);
                }
                if (noOfFloors.getVisibility() == View.VISIBLE)
                {
                    noOfFloors.setVisibility(View.GONE);
                }

                if (checkedId == R.id.generalLabourRadioBtn)
                {
                    if (containerSizeLayout.getVisibility() == View.VISIBLE)
                    {
                        containerSizeLayout.setVisibility(View.GONE);
                    }

                    supplyType = "General Labour";

                    if (containerWeight.getVisibility() == View.VISIBLE)
                    {
                        containerWeight.setVisibility(View.GONE);
                    }
                    if (containerQuantity.getVisibility() == View.VISIBLE)
                    {
                        containerQuantity.setVisibility(View.GONE);
                    }
                    if (containerVolume.getVisibility() == View.VISIBLE)
                    {
                        containerVolume.setVisibility(View.GONE);
                    }

                    if (noOflabours.getVisibility() == View.GONE)
                    {
                        noOflabours.setVisibility(View.VISIBLE);
                    }
                    if (noOfFloors.getVisibility() == View.GONE)
                    {
                        noOfFloors.setVisibility(View.VISIBLE);
                    }
                    if (noOfDays.getVisibility() == View.GONE)
                    {
                        noOfDays.setVisibility(View.VISIBLE);
                    }
                    if (noOfHours.getVisibility() == View.GONE)
                    {
                        noOfHours.setVisibility(View.VISIBLE);
                    }


                }


            }
        });

        containerSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if (checkedId == R.id.button_twenty)
                {
                    size = "20'";

                }
                if (checkedId == R.id.button_fourty)
                {
                    size = "40'";
                }

            }
        });


        labour_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgressBar();

                weight = weightofContainer.getText().toString();
                volume = volumeOfContainer.getText().toString();
                quantity = quantityOfContainer.getText().toString();
                type = goodsType.getText().toString();
                place = workPlace.getText().toString();
                distance = containerDistance.getText().toString();
                totalLabours = labours.getText().toString();
                hrs = labourHours.getText().toString();
                days = labourDays.getText().toString();
                floor = floors.getText().toString();
                labourRemarks = labour_remarks.getText().toString();


                LabourSupplyRequest labourSupplyRequest = new LabourSupplyRequest();
                labourSupplyRequest.setUser_id(Session.getUserId(context));
                labourSupplyRequest.setSupply_type(supplyType);
                labourSupplyRequest.setContainer(size);
                labourSupplyRequest.setNo_of_labour(totalLabours);
                labourSupplyRequest.setNo_of_hours(hrs);
                labourSupplyRequest.setNo_of_days(days);
                labourSupplyRequest.setFloor(floor);
                labourSupplyRequest.setVolume(volume);
                labourSupplyRequest.setWeight(weight);
                labourSupplyRequest.setQuantity(quantity);
                labourSupplyRequest.setType_of_goods(type);
                labourSupplyRequest.setWork_place(place);
                labourSupplyRequest.setDistance(distance);
                labourSupplyRequest.setSpecial_instruction(labourRemarks);

                RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
                Call<POJOClass> LabourSupply = retrofitInterface.labourSupplyApi("Bearer "+ Session.getToken(context), labourSupplyRequest);
                LabourSupply.enqueue(new Callback<POJOClass>() {
                    @Override
                    public void onResponse(Call<POJOClass> call, Response<POJOClass> response)
                    {
                        if (response.code()==200)
                        {
                            pojoClass = response.body();
                            Log.e(TAG, "code : "+response.code());

                            if (pojoClass.getStatus().equalsIgnoreCase("success"))
                            {
                                hideProgressBar();
                                Log.e(TAG, "Entered on success Status is: ->" + pojoClass.getStatus());
                                Toast.makeText(context, response.body().getMessage(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LabourSupplyActivity.this, MainActivity.class);
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
                            hideProgressBar();
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
