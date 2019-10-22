package com.huaji.phonetester;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
public static long cpuscore=0;
public static long zscore=0;
public static long xscore=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {//活动启动时发生事件
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }
public void cpurun(View view){
new cpuruntask().execute();
}
public void runcpu() {
    for (int i = 0; i < 110; i++) {
        for(int x=0;x<1500000;x++){
            double m=Math.pow(2,999);
        }
    }
    }
    public  void cpuup(){
        long time1=System.currentTimeMillis();
        while(true){
            long time2=System.currentTimeMillis();
            if(time2-time1>15000){
                break;
            }

        }
    }
    static double pi(double j) {
        double p = 1;
        for(double i = 1; i < 50000000; i++) { //循环相加
            double pCopy = p - (int)p;//最后两次的数值相减，精度位相减为0，说明精度已经达到
            p += Math.pow(-1,i) / ( 2 * i + 1 ); //莱布尼兹级数求和
            if( ( Math.abs( pCopy - ( p - (int)p ) ) * Math.pow(10,j) ) <= 0) break;//公式实现精度后退出循环
        }
        return p*4;
    }
    public void cpu2(){
pi(10000);
    }

    class cpuruntask extends AsyncTask<Void,String,Long>{
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute(){
            progressDialog.setMessage("CPU优化中....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Long doInBackground(Void ... params){
            cpuup();
            publishProgress("正在跑分...");
            long Starttime=System.currentTimeMillis();
            runcpu();
            long Endtime=System.currentTimeMillis();
            long runningtime1=Endtime-Starttime;
            publishProgress("正在跑分...(65%)");
long starttime=System.currentTimeMillis();
cpu2();
long endtime=System.currentTimeMillis();
long runningtime2=endtime-starttime;
double b=920000-Math.pow(runningtime1,1.3);
double c=920000-Math.pow(runningtime2,1.3);
zscore=(long) b;
xscore=(long)c;
            return runningtime1+runningtime2;
        }
        @Override
        protected  void onProgressUpdate(String... values){
            progressDialog.setMessage(values[0]);
        }
        @Override
        protected void onPostExecute(Long result){
            progressDialog.dismiss();
            cpuscore=(zscore+xscore)/2;

            String result2=String.valueOf(cpuscore);

            Toast.makeText(MainActivity.this,result2,Toast.LENGTH_SHORT).show();
        }
    }
}