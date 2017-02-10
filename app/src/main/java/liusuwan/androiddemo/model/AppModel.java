package liusuwan.androiddemo.model;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import liusuwan.androiddemo.R;
import liusuwan.androiddemo.activity.BluetoothActivity;
import liusuwan.androiddemo.activity.ChangeAnimaActivity;
import liusuwan.androiddemo.activity.DialogActivity;
import liusuwan.androiddemo.activity.FragmentAnimationActivity;
import liusuwan.androiddemo.activity.LottieActivity;
import liusuwan.androiddemo.activity.MetroViewGroupActivity;
import liusuwan.androiddemo.activity.PrismActivity;
import liusuwan.androiddemo.activity.RecyAddRecyActivity;
import liusuwan.androiddemo.activity.RecyAnimActivity;
import liusuwan.androiddemo.activity.RecyAutoFitActivity;
import liusuwan.androiddemo.activity.RecyLoadActivity;
import liusuwan.androiddemo.activity.SettingsActivity;
import liusuwan.androiddemo.activity.VectorActivity;
import liusuwan.androiddemo.activity.ViewDragActivity;

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
        appModelList.add(new AppModel("RecyAddRecyActivity", "recy里添加recy", R.drawable.ic_unity, RecyAddRecyActivity.class, "", onAppStart));
        appModelList.add(new AppModel("RecyLoadActivity", "recy加载", R.drawable.ic_unity, RecyLoadActivity.class, "", onAppStart));
        appModelList.add(new AppModel("PrismActivity", "Prism框架", R.drawable.ic_unity, PrismActivity.class, "", onAppStart));
        appModelList.add(new AppModel("RecyAutoFitActivity", "AutoFit", R.drawable.ic_unity, RecyAutoFitActivity.class, "", onAppStart));
        appModelList.add(new AppModel("BluetoothActivity", "蓝牙", R.drawable.ic_unity, BluetoothActivity.class, "", onAppStart));
        appModelList.add(new AppModel("SettingsActivity", "设置", R.drawable.ic_unity, SettingsActivity.class, "", onAppStart));
        appModelList.add(new AppModel("ChangeAnimaActivity", "切换动画", R.drawable.ic_unity, ChangeAnimaActivity.class, "", onAppStart));
        appModelList.add(new AppModel("RecyAnimActivity", "Recy加载动画", R.drawable.ic_unity, RecyAnimActivity.class, "", onAppStart));
        appModelList.add(new AppModel("VectorActivity", "矢量动画", R.drawable.ic_unity, VectorActivity.class, "", onAppStart));
        appModelList.add(new AppModel("MetroViewGroupActivity", "ViewGroup", R.drawable.ic_unity, MetroViewGroupActivity.class, "", onAppStart));
        appModelList.add(new AppModel("FragmentAnimationActivity", "ftagment动画", R.drawable.ic_unity, FragmentAnimationActivity.class, "", onAppStart));
        appModelList.add(new AppModel("ViewDragActivity", "拖拽", R.drawable.ic_unity, ViewDragActivity.class, "", onAppStart));
        appModelList.add(new AppModel("LottieActivity", "Lottie", R.drawable.ic_unity, LottieActivity.class, "", onAppStart));
        appModelList.add(new AppModel("DialogActivity", "弹出窗", R.drawable.ic_unity, DialogActivity.class, "", onAppStart));
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
