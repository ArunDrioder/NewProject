package com.example.newproject.International;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newproject.R;

public class AirOutboundActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] residentialType = { "Individual House ", "Apartment", "Mini Condo", "Flat"};
    String[] floorCount = {"1 Floor","2 Floors","3 Floors","4 Floors","5 Floors","6 Floors","7 Floors","8 Floors"};
    String[] spinnerWeight = {"1-300 Kg","301-500 Kg","501-100 Kg","Above 1000 Kg","Other"};
    String[] spinVol = {"1 CBM","2 CBM","3 CBM","4 CBM","5 CBM","Other"};

    private RadioGroup radioGroup,serviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_outbound);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        serviceType = (RadioGroup)findViewById(R.id.serviceType);
        radioGroup.clearCheck();
        serviceType.clearCheck();


        Spinner spin = (Spinner) findViewById(R.id.spinner);
        Spinner floorSpin = (Spinner)findViewById(R.id.spinnerFloor);
        Spinner weightSpinner = (Spinner)findViewById(R.id.spinnerWeight);
        Spinner spinnerVolume = (Spinner)findViewById(R.id.spinnerVolume);
        weightSpinner.setOnItemSelectedListener(this);
        spin.setOnItemSelectedListener(this);
        floorSpin.setOnItemSelectedListener(this);
        spinnerVolume.setOnItemSelectedListener(this);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,residentialType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);



        ArrayAdapter floorAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, floorCount);
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        floorSpin.setAdapter(floorAdapter);


        ArrayAdapter weightAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerWeight);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        weightSpinner.setAdapter(weightAdapter);

        ArrayAdapter volumeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinVol);
        volumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerVolume.setAdapter(volumeAdapter);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > 1) {
                    Toast.makeText(AirOutboundActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }




            }

            public void onClear(View v) {
                /* Clears all selected radio buttons to default */
                radioGroup.clearCheck();
            }

            public void onSubmit(View v) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                Toast.makeText(AirOutboundActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });



        serviceType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton st = (RadioButton) group.findViewById(checkedId);
                if (null != st && checkedId > 1) {
                    Toast.makeText(AirOutboundActivity.this, st.getText(), Toast.LENGTH_SHORT).show();
                }




            }

            public void onClear(View v) {
                /* Clears all selected radio buttons to default */
                radioGroup.clearCheck();
            }

            public void onSubmit(View v) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                Toast.makeText(AirOutboundActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplicationContext(),residentialType[position] , Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),floorCount[position] , Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
