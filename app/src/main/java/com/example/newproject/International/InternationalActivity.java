package com.example.newproject.International;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newproject.R;

public class InternationalActivity extends AppCompatActivity {

    Button inBound, outBound, airShipmentInbound,airShipmentOutBound,seaShipmentInbound,seaShipmentOutbound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international);
        inBound = (Button)findViewById(R.id.inboundShipment);
        outBound = (Button)findViewById(R.id.outboundShipment);
        airShipmentInbound = (Button)findViewById(R.id.airInbound);
        airShipmentOutBound = (Button)findViewById(R.id.airOutbound);
        seaShipmentInbound = (Button)findViewById(R.id.seaInbound);
        seaShipmentOutbound = (Button)findViewById(R.id.seaOutbound);

        inBound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                airShipmentInbound.setVisibility(View.VISIBLE);
                seaShipmentInbound.setVisibility(View.VISIBLE);
                airShipmentOutBound.setVisibility(View.GONE);
                seaShipmentOutbound.setVisibility(View.GONE);
            }
        });
        outBound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                airShipmentOutBound.setVisibility(View.VISIBLE);
                seaShipmentOutbound.setVisibility(View.VISIBLE);
                airShipmentInbound.setVisibility(View.GONE);
                seaShipmentInbound.setVisibility(View.GONE);
            }
        });

        airShipmentOutBound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InternationalActivity.this,AirOutboundActivity.class);
                startActivity(intent);
            }
        });

    }
}
