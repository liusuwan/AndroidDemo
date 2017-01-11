package liusuwan.androiddemo.model;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import liusuwan.androiddemo.R;
import liusuwan.androiddemo.activity.PrismActivity;
import liusuwan.androiddemo.activity.RecyAddRecyActivity;
import liusuwan.androiddemo.activity.RecyLoadActivity;

/**
 * Created by Jack on 2017-01-09.
 */

public class AppModel {
    public String name;
    public String desc;
    public int icon;
    public Class mClass;
    public String remark;
    public OnAppStart onAppStart;

    public AppModel() {
    }


    public void setOnAppStart(OnAppStart onAppStart) {
        this.onAppStart = onAppStart;
    }

    public AppModel(String name, String desc, int icon, Class mClass, String remark, OnAppStart onAppStart) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.mClass = mClass;
        this.remark = remark;
        this.onAppStart = onAppStart;
    }

    public static List<AppModel> getAppModels() {
        OnAppStart onAppStart = new OnAppStart() {
            @Override
            public void OnAppStart(Context context, Class cls) {
                    Intent i = new Intent(context, cls);
                    context.startActivity(i);
            }
        };

        List<AppModel> appModelList = new ArrayList<>();
        appModelList.add(new AppModel("RecyAddRecyActivity", "recy里添加recy", R.mipmap.ic_launcher, RecyAddRecyActivity.class, "", onAppStart));
        appModelList.add(new AppModel("RecyLoadActivity", "recy加载", R.mipmap.ic_launcher, RecyLoadActivity.class, "", onAppStart));
        appModelList.add(new AppModel("PrismActivity", "Prism框架", R.mipmap.ic_launcher, PrismActivity.class, "", onAppStart));
        return appModelList;
    }

    public static interface OnAppStart {
        void OnAppStart(Context context, Class cls);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getmClass() {
        return mClass;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OnAppStart getOnAppStart() {
        return onAppStart;
    }
}
