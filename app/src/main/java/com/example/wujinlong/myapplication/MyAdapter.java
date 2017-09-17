package com.example.wujinlong.myapplication;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.net.ConnectException;
import java.util.List;
import java.util.Map;

/**
 * Created by WuJinlong on 2017/9/17.
 */

public class MyAdapter extends BaseAdapter {
    Context context=null;
    List<Map<String,Object>> list=null;

    MyAdapter(Context context,List list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Icon
        ImageView iv=new ImageView(context);
        iv.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp_iv=new RelativeLayout.LayoutParams(-2,-2);
        lp_iv.width = 120;
        lp_iv.height = 120;
        lp_iv.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        iv.setLayoutParams(lp_iv);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //iv.setImageResource((Integer)((list.get(position)).get("appIcon")));
        iv.setImageDrawable(((AppInfo)list.get(position)).getAppIcon());
        //appName
        TextView name=new TextView(context);
        name.setId(View.generateViewId());
        RelativeLayout.LayoutParams lp_tv=new RelativeLayout.LayoutParams(-2,-2);
        //lp_tv.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp_iv.addRule(RelativeLayout.ALIGN_TOP,iv.getId());
        lp_tv.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        name.setLayoutParams(lp_tv);
        name.setTextSize(name.getTextSize()/2);
        name.setText(((AppInfo)list.get(position)).getAppName());

        TextView  tv_packageName=new TextView(context);
        RelativeLayout.LayoutParams lp_age=new RelativeLayout.LayoutParams(-2,-2);
        lp_age.addRule(RelativeLayout.BELOW,name.getId());
        lp_age.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tv_packageName.setLayoutParams(lp_age);
        tv_packageName.setText(((AppInfo)list.get(position)).getPacakgeName());

        //versionName
        TextView tv_version=new TextView(context);
        RelativeLayout.LayoutParams lp_sex=new RelativeLayout.LayoutParams(-2,-2);
        lp_sex.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tv_version.setLayoutParams(lp_sex);
        tv_version.setText("version-name: "+((AppInfo)list.get(position)).getVersionName()+", code: "+((AppInfo)list.get(position)).getVersionCode());

        //相对布局1
        RelativeLayout rl1=new RelativeLayout(context);
        rl1.setLayoutParams(new RelativeLayout.LayoutParams(-1,-2));
        rl1.addView(iv);
        rl1.addView(name);
        rl1.addView(tv_packageName);
        //相对布局2
        RelativeLayout rl2=new RelativeLayout(context);
        rl2.setLayoutParams(new LinearLayout.LayoutParams(-1,-2));
        rl2.addView(tv_version);
        LinearLayout returnView=new LinearLayout(context);
        returnView.setLayoutParams(new ListView.LayoutParams(-1,-2));
        returnView.setOrientation(LinearLayout.VERTICAL);
        returnView.addView(rl1);
        returnView.addView(rl2);

        return returnView;
    }
}
