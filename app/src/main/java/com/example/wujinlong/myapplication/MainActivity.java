package com.example.wujinlong.myapplication;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView mainListView = null;
    private TextView imeiView = null;
    private TextView imsiView = null;
    private ArrayList<AppInfo> appList;
    private ArrayList<AppInfo> showList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        initList();
        showList = new ArrayList<AppInfo>();
        for(int i=0;i<appList.size();i++){
            AppInfo tempInfo = (AppInfo)appList.get(i);
            if(!tempInfo.isSystemApp){
                showList.add(tempInfo);
            }
        }

        imeiView = new TextView(this);
        imeiView.setText("IMEI: "+getIMEI(this));

        imsiView = new TextView(this);
        imsiView.setText("IMSI: "+getIMSI(this));

        View line = new View(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,1);
        lp.setMargins(dip2px(5),0,dip2px(5),dip2px(10));
        line.setLayoutParams(lp);
        line.setBackgroundColor(Color.BLACK);

        mainListView =  new ListView(this);
        mainListView.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
//        String[] strList = {"aaa","bbb"};
//        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strList);
//        mainListView.setAdapter(aa);
        MyAdapter ma=new MyAdapter(this,showList);
        mainListView.setAdapter(ma);
        LinearLayout rl = new LinearLayout(this);
        rl.setOrientation(LinearLayout.VERTICAL);
        rl.addView(imeiView);
        rl.addView(imsiView);
        rl.addView(line);
        rl.addView(mainListView);
        setContentView(rl);
    }

    private void initList() {
        appList = new ArrayList<AppInfo>();
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        for (int i=0;i<packages.size();i++){
            PackageInfo packageInfo = packages.get(i);
            AppInfo tempInfo = new AppInfo();
            if((packageInfo.applicationInfo.flags& ApplicationInfo.FLAG_SYSTEM)==0){
                //非系统应用
                tempInfo.isSystemApp = false;
            }else{
                //系统应用
                tempInfo.isSystemApp = true;
            }
            tempInfo.setAppName(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
            tempInfo.setPacakgeName(packageInfo.packageName);
            tempInfo.setVersionName(packageInfo.versionName);
            tempInfo.setVersionCode(packageInfo.versionCode);
            tempInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));
            appList.add(tempInfo);
        }
    }

    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

    private String getIMSI(Context context){
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = mTelephonyMgr.getSubscriberId();
        return imsi ;
    }

    public int dip2px(float dpValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
