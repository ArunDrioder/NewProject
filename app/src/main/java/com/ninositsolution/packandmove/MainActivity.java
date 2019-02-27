package com.ninositsolution.packandmove;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ninositsolution.packandmove.International.InternationalActivity;
import com.ninositsolution.packandmove.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout residentialLayout;
    LinearLayout officeremovallayout;
    ImageView logOut;
    Button logOutYesAlertBtn,logOutNoAlertBtn;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    LinearLayout factory,packaging,carton,longDistance,truck,doortodoor,temproary,international,labour,custom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();



        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();


                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                    case R.id.mycart:
                        Toast.makeText(MainActivity.this, "My Cart", Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }
            }
        });




        residentialLayout = (LinearLayout)findViewById(R.id.residential_removal_layout);
        officeremovallayout = (LinearLayout)findViewById(R.id.office_removal_layout);
        factory = (LinearLayout)findViewById(R.id.factory_layout);
        packaging = (LinearLayout)findViewById(R.id.packaging_layout);
        carton = (LinearLayout)findViewById(R.id.carton_layout);
        longDistance = (LinearLayout)findViewById(R.id.longDistance_layout);
        truck = (LinearLayout)findViewById(R.id.truck_layout);
        doortodoor = (LinearLayout)findViewById(R.id.doorLayout);
        temproary = (LinearLayout)findViewById(R.id.temporaryLayout);
        international = (LinearLayout)findViewById(R.id.internationalLayout);
        labour = (LinearLayout)findViewById(R.id.labour_layout);
        custom = (LinearLayout)findViewById(R.id.customLayout);
        logOut = (ImageView)findViewById(R.id.logOutImgView);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup viewGroup = findViewById(android.R.id.content);
                final View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_logout_alert, viewGroup, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);
                logOutYesAlertBtn = (Button)dialogView.findViewById(R.id.logOutYesAlertBtn);
                logOutNoAlertBtn = (Button)dialogView.findViewById(R.id.logOutNoAlertBtn);
                final AlertDialog alertDialog = builder.create();
                logOutYesAlertBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent  intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                logOutNoAlertBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();




            }
        });



        residentialLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BasicInfoActivity.class);
                startActivity(intent);

            }
        });

        officeremovallayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OfficeRemovalActivity.class);
                startActivity(intent);
            }
        });
        factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FactoryActivity.class);
                startActivity(intent);
            }
        });
        packaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PackagingActivity.class);
                startActivity(intent);
            }
        });
        carton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartonActivity.class);
                startActivity(intent);
            }
        });

        longDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LongDistanceActivity.class);
                startActivity(intent);
            }
        });
        truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TruckRentalActivity.class);
                startActivity(intent);
            }
        });
        doortodoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DoorToDoor.class);
                startActivity(intent);
            }
        });
        temproary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TemproaryActivity.class);
                startActivity(intent);
            }
        });
        international.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InternationalActivity.class);
                startActivity(intent);
            }
        });
        labour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LabourActivity.class);
                startActivity(intent);
            }
        });
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomClearence.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}




