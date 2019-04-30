package com.ninositsolution.packandmove.cartonboxsupply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ninositsolution.packandmove.R;

public class CartonActivity extends AppCompatActivity {
    ImageView carton_backArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carton);
        carton_backArrow = findViewById(R.id.carton_backArrow);

        carton_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void goBack()
    {
        super.onBackPressed();
    }
}
