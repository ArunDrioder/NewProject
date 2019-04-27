package com.ninositsolution.packandmove.truckrental;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.doortodoorservices.DoorRecyclerViewAdapter;

import java.util.ArrayList;

public class TruckRentalActivity extends AppCompatActivity implements OnItemClickListener {

    LinearLayout yesLabourLayout;
    RadioGroup labourSupplyRadioGrp;
    RadioButton labourYesRadioBtn;
    RadioButton labourNoRadioBtn;
    RecyclerView truckRentalRecyclerView;
    TruckRecyclerViewAdapter truckRecyclerViewAdapter;
    Spinner truckType;
    RadioGroup truckRental_package;
    RadioButton openType, boxType;
    EditText rentalPickupPlace,rentalDeliveryPlace,rentalAddress, rentalDistance, noOfRentalLabours,rentalGoodsType,rentalGoodsQuantity,rentalGoodsWeight,rentalGoodsVolume, rentalRemarks;
    String rentalTruckType,rentalPackageType,rentalPickPlace,rentalDropPlace,rentalDropAddress,labourSupply,laboursCount,rentalGoods,rentalQuantity,rentalWeight,rentalVolume, distance, remarks;
    private static final int REQUEST_CAMERA = 100;
    private static final int RESULT_LOAD_IMAGE = 101;
    private static final String TAG = "TruckActivity";
    ArrayList<String> truckArrayList = new ArrayList<>();

    ArrayList<String> truckBase64ArrayList = new ArrayList<>();



    Context context;

    String[] typeOfTruck = { "Hijet", "1Oft Truck", "14ft Truck", "2Oft Truck","32 ft Truck","40 ft Truck"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_rental);
        yesLabourLayout = findViewById(R.id.yesLabourLayout);
        yesLabourLayout.setVisibility(View.GONE);
        labourSupplyRadioGrp = findViewById(R.id.labourSupplyRadioGrp);
        labourYesRadioBtn = findViewById(R.id.labourYesRadioBtn);
        labourNoRadioBtn = findViewById(R.id.labourNoRadioBtn);
        truckType = findViewById(R.id.truck_rental_trucktype);
        truckRentalRecyclerView = findViewById(R.id.truckRental_RecyclerView);





        context = TruckRentalActivity.this;
        ViewGroup.LayoutParams params = truckRentalRecyclerView.getLayoutParams();
        params.height =500;
        truckRentalRecyclerView.setLayoutParams(params);
        //truckRentalRecyclerView = new TruckRecyclerViewAdapter(truckArrayList, context, this);
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
                    }
                }

            }
        });


    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onRemoved(int position) {

    }
}
