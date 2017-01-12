package liusuwan.androiddemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017-01-12.
 */

public class ParentItem {
    public String Name;
    public Integer scrValue=0;
    public boolean isLoadSate=false;
    public List<ChildItem> childItemList = new ArrayList<>();

    public ParentItem() {
    }

    public ParentItem(String name, Integer scrValue, List<ChildItem> childItemList) {
        Name = name;
        this.scrValue = scrValue;
        this.childItemList = childItemList;
    }

    public List<ChildItem> getChildItemList() {
        return childItemList;
    }

    public void setChildItemList(List<ChildItem> childItemList) {
        this.childItemList = childItemList;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getScrValue() {
        return scrValue;
    }

    public void setScrValue(Integer scrValue) {
        this.scrValue = scrValue;
    }

    public boolean isLoadSate() {
        return isLoadSate;
    }

    public void setLoadSate(boolean loadSate) {
        isLoadSate = loadSate;
    }
}
