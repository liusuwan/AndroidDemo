package liusuwan.androiddemo.helper;

import java.util.ArrayList;
import java.util.List;

import liusuwan.androiddemo.model.ChildItem;
import liusuwan.androiddemo.model.ParentItem;

/**
 * Created by Jack on 2017-01-12.
 */

public class DataHelper {

    public static List<ParentItem> getRecyAddRecyData() {
        List<ParentItem> parentItemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ParentItem parentItem = new ParentItem();
            parentItem.childItemList.add(new ChildItem(i + " 0"));
            parentItem.childItemList.add(new ChildItem(i + " 1"));
            parentItem.childItemList.add(new ChildItem(i + " 2"));
            parentItem.childItemList.add(new ChildItem(i + " 3"));
            parentItem.childItemList.add(new ChildItem(i + " 4"));
            parentItem.childItemList.add(new ChildItem(i + " 5"));
            parentItem.childItemList.add(new ChildItem(i + " 6"));
            parentItem.childItemList.add(new ChildItem(i + " 7"));
            parentItem.childItemList.add(new ChildItem(i + " 8"));
            parentItem.childItemList.add(new ChildItem(i + " 9"));

            parentItemList.add(parentItem);
        }
        return parentItemList;
    }

    public static List<String> getTestData() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            itemList.add("i");
        }
        return itemList;
    }
}
