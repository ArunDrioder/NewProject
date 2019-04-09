package com.ninositsolution.packandmove.customclearence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ninositsolution.packandmove.R;

public class CustomClearenceActivity extends AppCompatActivity {

    LinearLayout airExportLyout,airImportLayout;
    Button exportBtn, importBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_clearence);

        airExportLyout = findViewById(R.id.airExportLayout);

        airExportLyout.setVisibility(View.VISIBLE);

        airImportLayout  =findViewById(R.id.airImportLayout);
        airImportLayout.setVisibility(View.GONE);

        exportBtn = findViewById(R.id.export_btn);
        importBtn = findViewById(R.id.import_btn);

        importBtn.setOnClickListener(new View.OnClickListener() {
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

                importBtn.setBackground(getResources().getDrawable(R.drawable.custom_import_button));
                importBtn.setTextColor(getResources().getColor(R.color.white));
                exportBtn.setBackground(getResources().getDrawable(R.drawable.custom_export_button_unselected));
                exportBtn.setTextColor(getResources().getColor(R.color.colorPrimary));



            }
        });



        exportBtn.setOnClickListener(new View.OnClickListener() {
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
                importBtn.setBackground(getResources().getDrawable(R.drawable.custom_import_button_unselected));
                importBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
                exportBtn.setBackground(getResources().getDrawable(R.drawable.custom_export_button));
                exportBtn.setTextColor(getResources().getColor(R.color.white));



            }
        });

    }

}
