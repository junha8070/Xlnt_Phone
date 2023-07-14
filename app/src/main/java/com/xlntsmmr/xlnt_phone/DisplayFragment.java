package com.xlntsmmr.xlnt_phone;

import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class DisplayFragment extends Fragment implements CustomView.CustomViewListener {

    String TAG = "DisplayFragment";

    ImageView iv_upper_left, iv_upper_middle, iv_upper_right;

    int[] location;
    int x;
    int y;
    int width;
    int height;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        init(view);

        CustomView customView = view.findViewById(R.id.customView);
        customView.setCustomViewListener(this);

        ViewTreeObserver viewTreeObserver = iv_upper_left.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv_upper_left.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // iv_upper_left의 위치 정보 얻기
                location = new int[2];
                iv_upper_left.getLocationOnScreen(location);
                x = location[0]; // x 좌표
                y = location[1]; // y 좌표

                width = iv_upper_left.getWidth(); // 너비
                height = iv_upper_left.getHeight(); // 높이

                Log.d(TAG, "iv_upper_left 위치 - x: " + x + ", y: " + y);
                Log.d(TAG, "iv_upper_left 크기 - 너비: " + width + ", 높이: " + height);
            }
        });

        ViewTreeObserver viewTreeObserver2 = iv_upper_middle.getViewTreeObserver();
        viewTreeObserver2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv_upper_middle.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // iv_upper_left의 위치 정보 얻기
                int[] location = new int[2];
                iv_upper_middle.getLocationOnScreen(location);
                int x = location[0]; // x 좌표
                int y = location[1]; // y 좌표

                Log.d(TAG, "iv_upper_middle 위치 - x: " + x + ", y: " + y);
            }
        });

        ViewTreeObserver viewTreeObserver3 = iv_upper_right.getViewTreeObserver();
        viewTreeObserver3.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv_upper_right.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                // iv_upper_left의 위치 정보 얻기
                int[] location = new int[2];
                iv_upper_right.getLocationOnScreen(location);
                int x = location[0]; // x 좌표
                int y = location[1]; // y 좌표

                Log.d(TAG, "iv_upper_right 위치 - x: " + x + ", y: " + y);
            }
        });

        return view;
    }

    private void init(View view){
        iv_upper_left = view.findViewById(R.id.iv_upper_left);
        iv_upper_middle = view.findViewById(R.id.iv_upper_middle);
        iv_upper_right = view.findViewById(R.id.iv_upper_right);
    }

    @Override
    public void onCoordinatesChanged(float touch_x, float touch_y) {
//        Log.d(TAG, "Received x: " + x + ", y: " + y);
        if(x<touch_x&&touch_x<(x+width)&&y<touch_y&&touch_y<(y+height)){
            Toast.makeText(getContext(), "터치됨", Toast.LENGTH_SHORT).show();
        }
    }
}