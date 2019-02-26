package com.ninositsolution.packandmove.International;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.ninositsolution.packandmove.R;

public class AirInboundActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] residentialInboundType = { "Individual House ", "Apartment", "Mini Condo", "Flat"};
    String[] inboundfloorCount = {"1 Floor","2 Floors","3 Floors","4 Floors","5 Floors","6 Floors","7 Floors","8 Floors"};
    String[] inboundSpinnerWeight = {"1-300 Kg","301-500 Kg","501-100 Kg","Above 1000 Kg","Other"};
    String[] inboundSpinVol = {"1 CBM","2 CBM","3 CBM","4 CBM","5 CBM","Other"};

    private String inboundApartmentselected;
    private String inboundMiniCondoselected;
    private RadioGroup diplomacyInboundRadioGrp;

    private Spinner countrySpinner,spinnerInboundFloor,residentialInboundSpinner,spinnerInboundWeight,spinnerInboundVolume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_inbound);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
