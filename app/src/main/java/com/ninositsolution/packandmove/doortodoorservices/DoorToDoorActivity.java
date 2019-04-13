package com.ninositsolution.packandmove.doortodoorservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ninositsolution.packandmove.R;

public class DoorToDoorActivity extends AppCompatActivity {
    EditText pickupPlace, pickupDate, pickupTime, dropPlace,dropDate,dropTime, typesOfGoods,estimatedWeight,estimatedVolume,remarks;
    String pickPlace,pickDate,pickTime,droppingPlace,droppingDate,droppingTime,goodsType,weight,volume,remark;
    Button doorSubmitBtn;


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

        doorSubmitBtn = findViewById(R.id.doorToDoor_submit_button);

        doorSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }
}
