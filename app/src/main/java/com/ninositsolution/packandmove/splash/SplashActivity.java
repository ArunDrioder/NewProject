package com.ninositsolution.packandmove.splash;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ninositsolution.packandmove.Login.LoginActivity;
import com.ninositsolution.packandmove.MainActivity;
import com.ninositsolution.packandmove.R;
import com.ninositsolution.packandmove.Registration.RegistrationActivity;
import com.ninositsolution.packandmove.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity{

    ActivitySplashBinding binding;

    SplashVM splashVM;

    ViewPager viewPager;

    private int dotscount;
    private ImageView[] dots;

    LinearLayout sliderpanel;

    Button letsgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_splash);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        splashVM = ViewModelProviders.of(this).get(SplashVM.class);

        binding.setSplash(splashVM);

        splashVM.firstSlideText1.set(getResources().getString(R.string.welcome_textview));
        splashVM.firstSlideText2.set(getResources().getString(R.string.get_started));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderpanel = (LinearLayout) findViewById(R.id.SliderDots);
        letsgo = (Button)findViewById(R.id.lets_go_button);

        letsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderpanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                switch (position)
                {
                    case 0 :

                        if (binding.welcomeText.getVisibility() == View.GONE)
                            binding.welcomeText.setVisibility(View.VISIBLE);

                        if (binding.swipeText.getVisibility() == View.GONE)
                            binding.swipeText.setVisibility(View.VISIBLE);

                        if (binding.textView2.getVisibility() == View.VISIBLE)
                            binding.textView2.setVisibility(View.GONE);

                        splashVM.firstSlideText1.set(getResources().getString(R.string.welcome_textview));
                        splashVM.firstSlideText2.set(getResources().getString(R.string.get_started));

                        if (binding.letsGoButton.getVisibility() == View.VISIBLE)
                            binding.letsGoButton.setVisibility(View.GONE);

                        break;

                    case 1:

                        if (binding.welcomeText.getVisibility() == View.VISIBLE)
                            binding.welcomeText.setVisibility(View.GONE);

                        if (binding.swipeText.getVisibility() == View.VISIBLE)
                            binding.swipeText.setVisibility(View.GONE);

                        if (binding.textView2.getVisibility() == View.GONE)
                            binding.textView2.setVisibility(View.VISIBLE);


                        if (binding.letsGoButton.getVisibility() == View.VISIBLE)
                            binding.letsGoButton.setVisibility(View.GONE);

                        splashVM.otherSlideText.set(getResources().getString(R.string.instructions_textview));

                        break;

                    case 2:

                        if (binding.welcomeText.getVisibility() == View.VISIBLE)
                            binding.welcomeText.setVisibility(View.GONE);

                        if (binding.swipeText.getVisibility() == View.VISIBLE)
                            binding.swipeText.setVisibility(View.GONE);

                        if (binding.textView2.getVisibility() == View.GONE)
                            binding.textView2.setVisibility(View.VISIBLE);


                        if (binding.letsGoButton.getVisibility() == View.GONE)
                            binding.letsGoButton.setVisibility(View.VISIBLE);

                        splashVM.otherSlideText.set(getResources().getString(R.string.slide_finishing_text));

                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}