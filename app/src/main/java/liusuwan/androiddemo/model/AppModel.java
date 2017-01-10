package liusuwan.androiddemo.model;

import java.util.ArrayList;
import java.util.List;

import liusuwan.androiddemo.R;
import liusuwan.androiddemo.activity.RecyAddRecyActivity;

/**
 * Created by Jack on 2017-01-09.
 */

public class AppModel {
    public String name;
    public String desc;
    public int icon;
    public Class mClass;
    public String remark;

    public AppModel() {
    }

    public AppModel(String name, String desc, int icon, Class mClass, String remark) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.mClass = mClass;
        this.remark = remark;
    }

    public static List<AppModel> getAppModels() {
        List<AppModel> appModelList = new ArrayList<>();
        appModelList.add(new AppModel("RecyAddRecyActivity", "recy里添加recy", R.mipmap.ic_launcher, RecyAddRecyActivity.class, ""));
        return appModelList;
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

}
