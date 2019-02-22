package com.example.newproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OverviewResidentialActivity extends AppCompatActivity {
    Button addRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_residential);
        addRoom = (Button)findViewById(R.id.addRoomBtn);
        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OverviewResidentialActivity.this, ResidentialAddRoomActivity.class);
                startActivity(intent);
            }
        });
    }
}
