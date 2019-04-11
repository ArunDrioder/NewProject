package com.ninositsolution.packandmove.longdistancetransportation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ninositsolution.packandmove.R;

public class LongDistanceActivity extends AppCompatActivity {

    LinearLayout packagingYesLayout;
    RadioGroup packagingRadioGroup;
    RadioButton packagingYesBtn,packagingNoBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_distance);
        packagingYesLayout = findViewById(R.id.packagingYesLayout);
        packagingYesLayout.setVisibility(View.GONE);
        packagingRadioGroup = findViewById(R.id.packagingRadioGrp);
        packagingYesBtn = findViewById(R.id.packagingYesRadioBtn);
        packagingNoBtn = findViewById(R.id.packagingNoRadioBtn);

        packagingRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.packagingYesRadioBtn)
                {
                    if (packagingYesLayout.getVisibility()==View.GONE)
                    {
                        packagingYesLayout.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        packagingYesLayout.setVisibility(View.GONE);
                    }
                }
                if (checkedId == R.id.packagingNoRadioBtn)
                {
                    if (packagingYesLayout.getVisibility() == View.VISIBLE)
                    {
                        packagingYesLayout.setVisibility(View.GONE);
                    }

                }
            }
        });



    }
}
