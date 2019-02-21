package com.example.newproject;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout residentialLayout;
    LinearLayout officeremovallayout;
    LinearLayout factory,packaging,carton,longDistance,truck,doortodoor,temproary,international,labour,custom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        residentialLayout = (LinearLayout)findViewById(R.id.residential_removal_layout);
        officeremovallayout = (LinearLayout)findViewById(R.id.office_removal_layout);
        factory = (LinearLayout)findViewById(R.id.factory_layout);
        packaging = (LinearLayout)findViewById(R.id.packaging_layout);
        carton = (LinearLayout)findViewById(R.id.carton_layout);
        longDistance = (LinearLayout)findViewById(R.id.longDistance_layout);
        truck = (LinearLayout)findViewById(R.id.truck_layout);
        doortodoor = (LinearLayout)findViewById(R.id.doorLayout);
        temproary = (LinearLayout)findViewById(R.id.temporaryLayout);
        international = (LinearLayout)findViewById(R.id.internationalLayout);
        labour = (LinearLayout)findViewById(R.id.labour_layout);
        custom = (LinearLayout)findViewById(R.id.customLayout);
        residentialLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BasicInfoActivity.class);
                startActivity(intent);

            }
        });

        officeremovallayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OfficeRemovalActivity.class);
                startActivity(intent);
            }
        });
        factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FactoryActivity.class);
                startActivity(intent);
            }
        });
        packaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PackagingActivity.class);
                startActivity(intent);
            }
        });
        carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartonActivity.class);
                startActivity(intent);
            }
        });

        longDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LongDistanceActivity.class);
                startActivity(intent);
            }
        });
        truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TruckRentalActivity.class);
                startActivity(intent);
            }
        });
        doortodoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DoorToDoor.class);
                startActivity(intent);
            }
        });
        temproary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TemproaryActivity.class);
                startActivity(intent);
            }
        });
        international.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InternationalActivity.class);
                startActivity(intent);
            }
        });
        labour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LabourActivity.class);
                startActivity(intent);
            }
        });
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomClearence.class);
                startActivity(intent);
            }
        });
    }
}
