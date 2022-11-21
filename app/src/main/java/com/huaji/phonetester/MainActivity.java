package com.huaji.phonetester;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.litepal.LitePal;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
public static long cpuscore=0;
public static long zscore=0;
public static long xscore=0;
public static String perform="";
public static String BB = "内核BB:1.0";

public static String filepath="/sdcard/";
File file=new File(filepath,"testfile");
public static long wtspeed;
public static long dqspeed;

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
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("权限授予");
            dialog.setMessage("应用检测到你没有授予基本的储存权限。如果没有授予储存权限，将不能愉快地跑分。");
            dialog.setCancelable(false);
            dialog.setPositiveButton("明白了，授予", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
            });
            dialog.setNegativeButton("老子就是不给", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "(눈_눈)", Toast.LENGTH_SHORT).show();

                }


            });
            dialog.show();
        }
    }
public void cpurun(View view){
new cpuruntask().execute();
}
public void runcpu() {
    for (int i = 0; i < 100; i++) {
        int x = 0;
        int y=0;
        while (x < 16000000) {
            while ( y <2560000){
                double m = Math.pow(2, 99999);
                y++;
            }
            x++;
        }
    }
    }
    public  void cpuup(){
        long time1=System.currentTimeMillis();
        while(true){
            long time2=System.currentTimeMillis();
            if(time2-time1>15000) break;

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
        int m = 0;
        while(m < 10) {
            pi(1000000000);
            m++;
        }
    }

    class cpuruntask extends AsyncTask<Void,String,Long>{
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute(){
            progressDialog.setMessage("模拟使用环境...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Long doInBackground(Void ... params){
            cpuup();
            publishProgress("正在跑分（第1项/共2项）...");
            long Starttime=System.currentTimeMillis();
            runcpu();
            long Endtime=System.currentTimeMillis();
            long runningtime1=Endtime-Starttime;
            publishProgress("正在跑分(第2项/共2项)...");
long starttime=System.currentTimeMillis();
cpu2();
long endtime=System.currentTimeMillis();
long runningtime2=endtime-starttime;
double b=100000-runningtime1;
double c=100000-runningtime2*2;
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
            if(result<=30000){
                cpuscore=150000+(100000-result);
                zscore=zscore+100000;
                xscore=xscore+50000;
                perform="您的cpu属于：超高性能cpu";
            }
            else if(result>30000){
                 if(result<=60000){
                 cpuscore=100000+(100000-result);
                     zscore=zscore+80000;
                     xscore=xscore+50000;
                 perform="您的cpu属于：高性能cpu";
                 }
                 else{
                     if(result<=90000){
                         cpuscore=50000+(100000-result);
                         zscore=zscore+50000;
                         xscore=xscore+10000;
                         perform="您的cpu属于：中高性能cpu";
                     }
                     else{
                         if(result<=120000){
                             cpuscore=10000+(120000-result);
                             zscore=zscore+30000;
                             xscore=xscore+15000;
                             perform="您的cpu属于：中低性能cpu";
                         }
                         else{
                             long noscore=(long)(Math.random()*5000+1);
                             cpuscore=noscore;
                             zscore=(long)(Math.random()*5000+1)+500;
                             xscore=(long)(Math.random()*5000+1)+500;
                             perform="您的cpu属于：低性能cpu";
                         }
                     }
                 }
              }
            String cpudot=String.valueOf(cpuscore);
            String zsdot=String.valueOf(zscore);
            String xsdot=String.valueOf(xscore);

            final AlertDialog.Builder progressDialog1=new AlertDialog.Builder(MainActivity.this);
             progressDialog1.setTitle("跑分结果");
             progressDialog1.setMessage("cpu综合得分："+cpudot+"\n整数运算综合得分："+zsdot+"\n浮点运算综合得分："+xsdot+"\n"+perform);
             History HP = new History();
             HP.setScore(cpuscore);
             HP.setBB(BB);
             HP.setTime(Calendar.getInstance().get(Calendar.YEAR)+"."+Calendar.getInstance().get(Calendar.MONTH)+"."+Calendar.getInstance().get(Calendar.DATE)+"|"+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE));
             HP.setType(0);
             HP.save();
             progressDialog1.setCancelable(false);
             progressDialog1.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             progressDialog1.show();

        }
    }
    //文件写入
    public boolean createFile(String targetFile, long fileLength, String unit) {
        //指定每次分配的块大小
        long KBSIZE = 1024;
        long MBSIZE1 = 1024 * 1024;
        long MBSIZE10 = 1024 * 1024 * 10;
        long MBSIZE100=1024*1024*100;
        if(unit=="KB")
        {
            fileLength = fileLength * 1024;
        }
        if(unit=="MB")
        {
            fileLength = fileLength * 1024*1024;
        }
        if(unit=="GB")
        {
            fileLength = fileLength * 1024*1024*1024;
        }
        if (unit=="TB")
        {
            fileLength=fileLength*1024*1024*1024*1024;
        }


        FileOutputStream fos = null;
        File file = new File(targetFile);
        try {
//如果文件存在
            if
            (!file.exists()) {
                file.createNewFile();
            }

            long batchSize = 0;
            batchSize = fileLength;
            if (fileLength > KBSIZE) {
                batchSize = KBSIZE;
            }
            if (fileLength > MBSIZE1) {
                batchSize = MBSIZE1;
            }
            if (fileLength > MBSIZE10) {
                batchSize = MBSIZE10;
            }
            if (fileLength >MBSIZE100){
                batchSize = MBSIZE100;
            }
            long count = fileLength / batchSize;
            long last = fileLength % batchSize;

            fos = new FileOutputStream(file);
            FileChannel fileChannel = fos.getChannel();
            for (int i = 0; i < count; i++) {
                ByteBuffer buffer = ByteBuffer.allocate((int) batchSize);
                fileChannel.write(buffer);

            }
            if (last != 0) {
                ByteBuffer buffer = ByteBuffer.allocate((int) last);
                fileChannel.write(buffer);
            }
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return false;
    }
    public String saveImage(String imgPath) {
        long startTime = System.currentTimeMillis();//开始读取时间
        long endTime = 0L;

        try {
            FileInputStream inputStream = new FileInputStream(imgPath);//图片输入流
            FileOutputStream outputStream = new FileOutputStream(filepath+"testfile2");
            int size = inputStream.available();//得到文件总大小
            byte[] buff = new byte[size];
            inputStream.read(buff);
            outputStream.write(buff);
            inputStream.close();
            outputStream.close();
            endTime = System.currentTimeMillis();//读取结束时间
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "整体读取耗时："+(endTime-startTime) + "ms";
    }


    class romruntask extends AsyncTask<Void,String,Long>{
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute(){
            progressDialog.setMessage("清理缓存...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Long doInBackground(Void ... params){

            publishProgress("正在跑分（第1项/共2项）...");
            long Starttime=System.currentTimeMillis();
            createFile(filepath+"testfile",200,"MB");
            long Endtime=System.currentTimeMillis();
            long runningtime1=Endtime-Starttime;

          wtspeed=200/(runningtime1/200);
            publishProgress("正在跑分(第2项/共2项)...");
            long starttime=System.currentTimeMillis();

                saveImage(filepath+"testfile");

            long endtime=System.currentTimeMillis();
            long runningtime2=endtime-starttime;
             dqspeed=200/(runningtime2/200);

if(file.exists()){
    file.delete();
            }
File file2=new File(filepath,"testfile2");
if(file2.exists()){
    file2.delete();
}
            return (wtspeed+dqspeed)/2;
        }
        @Override
        protected  void onProgressUpdate(String... values){
            progressDialog.setMessage(values[0]);
        }
        @Override
        protected void onPostExecute(Long result){
            progressDialog.dismiss();
long zhspeed=(long)Math.pow(result,2.5);
            String cpudot=String.valueOf(zhspeed);
            String xrdot=String.valueOf(wtspeed)+"MB/s";
            String dqdot=String.valueOf(dqspeed)+"MB/s";

            final AlertDialog.Builder progressDialog1=new AlertDialog.Builder(MainActivity.this);
            progressDialog1.setTitle("跑分结果");
            progressDialog1.setMessage("内存综合得分："+cpudot+"\n写入速度："+xrdot+"\n读取速度："+dqdot);
            progressDialog1.setCancelable(false);
            progressDialog1.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            progressDialog1.show();
            History HP = new History();
            HP.setScore(Long.valueOf(cpudot));
            HP.setBB(BB);
            HP.setTime(Calendar.getInstance().get(Calendar.YEAR)+"."+Calendar.getInstance().get(Calendar.MONTH)+"."+Calendar.getInstance().get(Calendar.DATE)+"|"+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE));
            HP.setType(1);
            HP.save();

        }
    }
public void romrun(View view){
        new romruntask().execute();
}

}