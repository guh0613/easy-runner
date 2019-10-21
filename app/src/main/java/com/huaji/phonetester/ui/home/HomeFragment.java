package com.huaji.phonetester.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class HomeFragment extends Fragment {
    public String androidversion= Build.VERSION.RELEASE;//获取安卓版本
    public String mobilephone2=Build.MODEL;//获取手机型号
    public String mobilephone1=Build.BRAND;//获取手机品牌
    public String cpu[]=getCpuInfo();
    public String cpumodel=cpu[0];
    public String str1=getvalidCpuFreq();
    public double cpuclock1=Integer.parseInt(str1);
    double cpuspeed=cpuclock1/1000000;
    public String cpuclock=String.format("%.2f",cpuspeed);

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView aversion1=root.findViewById(R.id.aversion1);
        aversion1.setText("手机型号："+mobilephone1+mobilephone2 +"\n安卓版本："+androidversion+"\ncpu:"+cpumodel+"\n当前调度最高频率："+cpuclock+"ghz");
        return root;
    }
    /**
     * 手机CPU信息
     */
    public String[] getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""}; //1-cpu型号 //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        Log.i("text", "cpuinfo:" + cpuInfo[0] + " " + cpuInfo[1]);
        return cpuInfo;
    }
    public static String getvalidCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            result = "N/A";
        }

        return result.trim();
 }
}