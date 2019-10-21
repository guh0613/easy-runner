package com.huaji.phonetester.ui.dashboard;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.huaji.phonetester.R;

public class DashboardFragment extends Fragment {
    public String androidversion= Build.VERSION.RELEASE;//获取安卓版本
    public String mobilephone2=Build.MODEL;//获取手机型号
    public String mobilephone1=Build.BRAND;//获取手机品牌
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView aversion1=root.findViewById(R.id.aversion2);
        aversion1.setText("手机型号："+mobilephone1+mobilephone2 +"\n安卓版本："+androidversion);
        return root;
    }
}