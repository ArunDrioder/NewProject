package com.ninositsolution.packandmove.customclearence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ninositsolution.packandmove.R;

public class CustomClearenceActivity extends AppCompatActivity {

    LinearLayout airExportLyout,airImportLayout;
    Button airExportBtn, airImportBtn;
    RadioButton seaRadioBtn, airRadioBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_clearence);


        airExportLyout = findViewById(R.id.airExportLayout);

        airExportLyout.setVisibility(View.VISIBLE);

        airImportLayout  =findViewById(R.id.airImportLayout);
        airImportLayout.setVisibility(View.GONE);






        airExportBtn = findViewById(R.id.air_export_btn);
        airImportBtn = findViewById(R.id.air_import_btn);

        seaRadioBtn = findViewById(R.id.seaRadioBtn);
        airRadioBtn = findViewById(R.id.airRadioBtn);

        airImportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (airExportLyout.getVisibility() == View.VISIBLE)
                {
                    airExportLyout.setVisibility(View.GONE);
                }
                if (airImportLayout.getVisibility() == View.GONE)
                {
                    airImportLayout.setVisibility(View.VISIBLE);
                }






                airImportBtn.setBackground(getResources().getDrawable(R.drawable.custom_import_button));
                airImportBtn.setTextColor(getResources().getColor(R.color.white));
                airExportBtn.setBackground(getResources().getDrawable(R.drawable.custom_export_button_unselected));
                airExportBtn.setTextColor(getResources().getColor(R.color.colorPrimary));




            }
        });

        airExportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (airExportLyout.getVisibility() ==View.GONE)
                {
                    airExportLyout.setVisibility(View.VISIBLE);
                }
                if (airImportLayout.getVisibility() ==View.VISIBLE)
                {
                    airImportLayout.setVisibility(View.GONE);
                }



                airExportBtn.setBackground(getResources().getDrawable(R.drawable.custom_export_button));
                airExportBtn.setTextColor(getResources().getColor(R.color.white));
                airImportBtn.setBackground(getResources().getDrawable(R.drawable.custom_import_button_unselected));
                airImportBtn.setTextColor(getResources().getColor(R.color.colorPrimary));




            }
        });



    }

}
