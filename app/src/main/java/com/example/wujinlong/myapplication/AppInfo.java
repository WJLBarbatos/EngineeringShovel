package com.example.wujinlong.myapplication;

import android.graphics.drawable.Drawable;

/**
 * Created by WuJinlong on 2017/9/17.
 */

public class AppInfo {
    private String appName = "";
    private String pacakgeName = "";
    private String versionName = "";
    private int versionCode = 0;
    private Drawable appIcon = null;


    public boolean isSystemApp = false;

    public String getAppName(){
        return this.appName;
    }
    public void setAppName(String appName){
        this.appName = appName;
    }

    public String getPacakgeName(){
        return this.pacakgeName;
    }
    public void setPacakgeName(String pacakgeName){
        this.pacakgeName = pacakgeName;
    }

    public String getVersionName(){
        return this.versionName;
    }
    public void setVersionName(String versionName){
        this.versionName = versionName;
    }

    public int getVersionCode(){
        return this.versionCode;
    }
    public void setVersionCode(int versionCode){
        this.versionCode = versionCode;
    }

    public Drawable getAppIcon(){
        return this.appIcon;
    }

    public void setAppIcon(Drawable appIcon){
        this.appIcon = appIcon;
    }
}
