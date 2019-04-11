package com.ninositsolution.packandmove.truckrental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ninositsolution.packandmove.R;

public class TruckRentalActivity extends AppCompatActivity {

    LinearLayout yesLabourLayout;
    RadioGroup labourSupplyRadioGrp;
    RadioButton labourYesRadioBtn;
    RadioButton labourNoRadioBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_rental);
        yesLabourLayout = findViewById(R.id.yesLabourLayout);
        yesLabourLayout.setVisibility(View.GONE);
        labourSupplyRadioGrp = findViewById(R.id.labourSupplyRadioGrp);
        labourYesRadioBtn = findViewById(R.id.labourYesRadioBtn);
        labourNoRadioBtn = findViewById(R.id.labourNoRadioBtn);

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
}
