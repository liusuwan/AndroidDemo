package liusuwan.androiddemo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;

public class WindowMangerActivity extends AppCompatActivity {

    @BindView(R.id.btn_dialog)
    Button btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_manger);
        ButterKnife.bind(this);

        initView();
    }

    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("1231");
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW};
                ActivityCompat.requestPermissions(this, permissions, 10);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.d("TAG", "requestCode" + requestCode + "permissions"+ Arrays.toString(permissions)+"grantResults"+Arrays.toString(grantResults));
    }

    public void showToast(String incomingNumber) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
                return;
            } else {
                //绘ui代码, 这里说明6.0系统已经有权限了
            }
        }


        //1.获取windowManager
        WindowManager windowManager = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);

        //2.获取要添加到窗口中的布局对象
        View view = View.inflate(this, R.layout.customtoast, null);
        // 初始化控件，将号码归属地放到textview中显示
        TextView mAddress = (TextView) view
                .findViewById(R.id.custom_tv_address);
        mAddress.setText("Hello");

        //获取保存的自定义Toast的背景，设置给控件显示
        view.setBackgroundResource(R.drawable.ic_unity);

        //3.设置布局对象在窗口中显示的属性
        //通过代码设置控件的属性，Andorid中每个父控件都是有自己的LayoutParams,每个layoutparams中都是根据控件设置有自己的相关属性代码的
        //LayoutParams设置的属性效果，根据在布局文件中使用相应的属性设置效果是一样的
        //使用layoutparams规则：将view对象添加到那个父控件中，就要使用那个父控件中的layoutparams，表示子控件遵循父控件的属性规则,这样子控件使用的属性才会生效，否则是有可能会失效
        //例子：比如在ImageView中使用layout_centerVertical属性，ImageView必须是RelativeLayout，如果是Linearlayout的子控件，是不能使用layout_centerVertical
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;//设置高度包裹内容
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;//设置宽度包裹内容
        params.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE //不能获取焦点
                        //| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE //不能触摸
                        |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON; //保持屏幕长亮
        params.format = PixelFormat.TRANSLUCENT; //设置背景透明效果
        params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;

// 设置控件是否toast类型,toast天生没有触摸事件，TYPE_PRIORITY_PHONE：优先于电话Ui的类型

        //4.将params设置给view对象，再将view对象添加到windowManager中显示
        windowManager.addView(view, params);
    }
}
