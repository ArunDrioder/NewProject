package com.ninositsolution.packandmove.International;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ninositsolution.packandmove.R;

public class AirOutboundActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] residentialType = { "Individual House ", "Apartment", "Mini Condo", "Flat"};
    String[] floorCount = {"1 Floor","2 Floors","3 Floors","4 Floors","5 Floors","6 Floors","7 Floors","8 Floors"};
    String[] spinnerWeight = {"1-300 Kg","301-500 Kg","501-100 Kg","Above 1000 Kg","Other"};
    String[] spinVol = {"1 CBM","2 CBM","3 CBM","4 CBM","5 CBM","Other"};

    private RadioGroup radioGroup,serviceType;

    Button residentialYesBtn;
    Button residentialNoBtn;
    private Spinner spin;
    private Spinner floorSpin;
    private Spinner weightSpinner;
    private Spinner spinnerVolume;
    private String apartmentselected;
    private String minicondoselected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_outbound);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        serviceType = (RadioGroup)findViewById(R.id.serviceType);
        radioGroup.clearCheck();
        serviceType.clearCheck();


         spin = (Spinner) findViewById(R.id.spinner);
         floorSpin = (Spinner)findViewById(R.id.spinnerFloor);
         weightSpinner = (Spinner)findViewById(R.id.spinnerWeight);
         spinnerVolume = (Spinner)findViewById(R.id.spinnerVolume);
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

        switch (position){

            case R.id.spinner:

                apartmentselected = spin.getSelectedItem().toString();
                minicondoselected = spin.getSelectedItem().toString();

                if (apartmentselected.equalsIgnoreCase("Apartment")){

                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_residential_alert, viewGroup, false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setView(dialogView);
                    residentialYesBtn = (Button)dialogView.findViewById(R.id.residentialYesAlertBtn);
                    residentialNoBtn = (Button)dialogView.findViewById(R.id.residentialNoAlertBtn);
                    final AlertDialog alertDialog = builder.create();
                    residentialYesBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"Elevator will be used for your delivery",Toast.LENGTH_LONG).show();
                        }
                    });

                    residentialNoBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"Elevator will not be used for your delivery",Toast.LENGTH_LONG).show();


                        }
                    });

                }

                else if (minicondoselected.equalsIgnoreCase("Mini Condo")){
                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_residential_alert, viewGroup, false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setView(dialogView);
                    residentialYesBtn = (Button)dialogView.findViewById(R.id.residentialYesAlertBtn);
                    residentialNoBtn = (Button)dialogView.findViewById(R.id.residentialNoAlertBtn);
                    final AlertDialog alertDialog = builder.create();
                    residentialYesBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"Elevator will be used for your delivery",Toast.LENGTH_LONG).show();
                        }
                    });

                    residentialNoBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"Elevator will not be used for your delivery",Toast.LENGTH_LONG).show();


                        }
                    });


                }

                break;





        }










    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
