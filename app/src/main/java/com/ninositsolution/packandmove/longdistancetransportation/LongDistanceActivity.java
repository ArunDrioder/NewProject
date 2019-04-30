package com.ninositsolution.packandmove.longdistancetransportation;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ninositsolution.packandmove.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LongDistanceActivity extends AppCompatActivity {

    LinearLayout packagingYesLayout;
    RadioGroup packagingRadioGroup;
    RadioButton packagingYesBtn,packagingNoBtn;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();
    EditText longPickupDate, longDeliveryDate;
    ImageView longDistance_backArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_distance);
        packagingYesLayout = findViewById(R.id.packagingYesLayout);
        packagingYesLayout.setVisibility(View.GONE);
        packagingRadioGroup = findViewById(R.id.packagingRadioGrp);
        packagingYesBtn = findViewById(R.id.packagingYesRadioBtn);
        packagingNoBtn = findViewById(R.id.packagingNoRadioBtn);
        longPickupDate = findViewById(R.id.longTransport_pickupDate);
        longDeliveryDate = findViewById(R.id.longTransport_dropDate);
        longDistance_backArrow = findViewById(R.id.longDistance_backArrow);
        longDistance_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });






        final DatePickerDialog.OnDateSetListener datePickup = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatePickLabel();


            }

            private void updatePickLabel()
            {
                String pickDateFormat = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pickDateFormat, Locale.US);

                longPickupDate.setText(simpleDateFormat.format(myCalendar.getTime()));


            }
        };

        longPickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(LongDistanceActivity.this,datePickup,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        final DatePickerDialog.OnDateSetListener dateDelivery = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDropLabel();

            }

            private void updateDropLabel()
            {
                String deliverDateFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(deliverDateFormat, Locale.US);
                longDeliveryDate.setText(sdf.format(myCalendar2.getTime()));


            }
        };

      longDeliveryDate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v)
          {
              new DatePickerDialog(LongDistanceActivity.this,dateDelivery,myCalendar2.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH)).show();



          }
      });


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

    private void goBack()
    {
        super.onBackPressed();
    }
}
