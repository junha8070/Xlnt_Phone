package com.xlntsmmr.xlnt_phone;

import android.os.BatteryManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.MaterialToolbar;

public class CheckingFragment extends Fragment {

    private String TAG = CheckingFragment.class.getSimpleName();

    private MaterialToolbar toolbar;
    private TextView tv_status;
    private LottieAnimationView lottie;

    private final Animation fade_in = new AlphaAnimation(0.0f, 1.0f);
    private final Animation fade_out = new AlphaAnimation(1.0f, 0.0f);

    DeviceInfo deviceInfo;
    BatteryInfo batteryInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checking, container, false);

        init(view);

        Log.d(TAG, deviceInfo.getDeviceId(getContext()));
        Log.d(TAG, deviceInfo.getManufacturer());
        Log.d(TAG, deviceInfo.getDeviceBrand());
        Log.d(TAG, deviceInfo.getDeviceModel());
        Log.d(TAG, deviceInfo.getDeviceOs());
        Log.d(TAG, String.valueOf(deviceInfo.getDeviceSdk()));

        animationSetting(R.raw.battery_scan, "배터리를 검사할게요");

        Log.d(TAG, "충전중? "+String.valueOf(batteryInfo.isCharging()));
        Log.d(TAG, "충전기 종류: "+String.valueOf(batteryInfo.chargingKind()));
        Log.d(TAG, "배터리 양: "+String.valueOf(batteryInfo.batteryPct()));
        Log.d(TAG, "배터리 수명: "+batteryInfo.batteryHealth());
//        Log.d(TAG, "배터리 충전 횟수: "+batteryInfo.batteryCycle());



//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return view;
    }

    private void init(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        lottie = view.findViewById(R.id.lottie);
        tv_status = view.findViewById(R.id.tv_status);

        batteryInfo = new BatteryInfo(view.getContext());

    }

    private void animationSetting(int rawRes, String status_txt){
        fade_out.setDuration(2000);
        fade_in.setDuration(2000);
        tv_status.setAnimation(fade_out);
        lottie.setAnimation(fade_out);
        tv_status.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv_status.setText(status_txt);
                tv_status.setAnimation(fade_in);
                lottie.setAnimation(rawRes);
                lottie.setAnimation(fade_in);
                lottie.playAnimation();
                Navigation.findNavController(getView()).navigate(R.id.action_checkingFragment_to_displayFragment);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}