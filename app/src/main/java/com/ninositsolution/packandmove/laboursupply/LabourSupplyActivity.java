package com.ninositsolution.packandmove.laboursupply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ninositsolution.packandmove.R;

public class LabourSupplyActivity extends AppCompatActivity {

    LinearLayout radioLabelLayout;
    RadioButton generalLabourRadioBtn, containerLoadingRadioBtn,containerUnloadingRadioBtn;
    RadioGroup containerSizeRadioGroup;
    CardView loadingUnloadingCardView,labourCarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_supply);
        radioLabelLayout = findViewById(R.id.radioLabelLayout);
        generalLabourRadioBtn = findViewById(R.id.generalLabourRadioBtn);

        containerLoadingRadioBtn = findViewById(R.id.containerLoadingRadioBtn);
        containerUnloadingRadioBtn = findViewById(R.id.containerUnLoadingRadioBtn);
        loadingUnloadingCardView = findViewById(R.id.loadiingUnloadingcardView);
        labourCarView = findViewById(R.id.labourCardView);
        containerSizeRadioGroup = findViewById(R.id.containerSizeRadioGroup);
        labourCarView.setVisibility(View.GONE);

        generalLabourRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioLabelLayout.setVisibility(View.GONE);
                labourCarView.setVisibility(View.VISIBLE);
                loadingUnloadingCardView.setVisibility(View.GONE);
                containerSizeRadioGroup.setVisibility(View.GONE);

            }
        });

        containerLoadingRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioLabelLayout.setVisibility(View.VISIBLE);
                labourCarView.setVisibility(View.GONE);
                loadingUnloadingCardView.setVisibility(View.VISIBLE);
                containerSizeRadioGroup.setVisibility(View.VISIBLE);

            }
        });

        containerUnloadingRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingUnloadingCardView.setVisibility(View.VISIBLE);
                containerSizeRadioGroup.setVisibility(View.VISIBLE);
                labourCarView.setVisibility(View.GONE);
                radioLabelLayout.setVisibility(View.VISIBLE);
            }
        });


    }
}
