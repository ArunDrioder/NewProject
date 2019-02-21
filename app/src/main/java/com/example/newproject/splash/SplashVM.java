package com.example.newproject.splash;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

public class SplashVM extends ViewModel {

    public ObservableField<String> firstSlideText1 = new ObservableField<>();
    public ObservableField<String> firstSlideText2 = new ObservableField<>();
    public ObservableField<String> otherSlideText = new ObservableField<>();


    public SplashVM() {
    }
}
