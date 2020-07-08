package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;

public class ViewNumCount {
    public ViewNumCount(){}
    public int count(View root) {
        int viewCount = 0;

        if (null == root) {
            return 0;
        }

        if (root instanceof ViewGroup) {
            viewCount++;
            for (int i = 0; i < ((ViewGroup) root).getChildCount(); i++) {
                View view = ((ViewGroup) root).getChildAt(i);
                if (view instanceof ViewGroup) {
                    viewCount += count(view);
                } else {
                    viewCount++;
                }
            }
        }
        return viewCount;
    }
}
