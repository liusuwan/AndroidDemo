package liusuwan.androiddemo.helper;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2017-01-12.
 */

public class AccessUtil {


    //检查权限
    public static void getAccess(Activity activity) {
        PackageManager pm = activity.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] permissions = pi.requestedPermissions;
            List<String> permissionList = new ArrayList<>();
            if (permissions != null) {
                for (String str : permissions) {
                    if (ContextCompat.checkSelfPermission(activity, str) != PackageManager.PERMISSION_GRANTED) {
                        permissionList.add(str);
//                            PermissionInfo permissionInfo = activity.getPackageManager().getPermissionInfo(str, 0);
//                            permissionInfoList.add(permissionInfo);
                    }
                }
            }
            checkAccess(activity, permissionList);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    //申请权限
    public static void checkAccess(Activity activity, List<String> permissionList) {
        if (permissionList.size() > 0) {
            String[] permissions = new String[permissionList.size()];
            for (int i = 0; i < permissionList.size(); i++) {
                permissions[i] = permissionList.get(i);
            }
            ActivityCompat.requestPermissions(activity, permissions, 0);
        }
    }
}

