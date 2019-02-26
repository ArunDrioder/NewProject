package com.ninositsolution.packandmove;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasicInfoActivity extends AppCompatActivity {
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        continueButton = (Button)findViewById(R.id.Continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasicInfoActivity.this, OverviewResidentialActivity.class);
                startActivity(intent);
            }
        });
    }
}
