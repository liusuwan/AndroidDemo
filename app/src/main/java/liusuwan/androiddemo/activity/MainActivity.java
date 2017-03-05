package liusuwan.androiddemo.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import liusuwan.androiddemo.helper.AccessUtil;
import liusuwan.androiddemo.model.AppModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recy_appmodel)
    RecyclerView recyAppModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyAppModel.setHasFixedSize(true);
        recyAppModel.setLayoutManager(new GridLayoutManager(this, 4));
        AppModelAdapter appModelAdapter = new AppModelAdapter(this, AppModel.getAppModels());
        recyAppModel.setAdapter(appModelAdapter);

        if (Build.VERSION.SDK_INT >= 23) {
            AccessUtil.getAccess(MainActivity.this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else {
            Toast.makeText(this, "获取权限失败!", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    public class AppModelAdapter extends RecyclerView.Adapter<AppModelAdapter.AppModelViewHolder> {
        public List<AppModel> appModelList = new ArrayList<>();
        public Context context;

        public AppModelAdapter(Context context, List<AppModel> appModelList) {
            this.context = context;
            this.appModelList = appModelList;
        }

        @Override
        public AppModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new AppModelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appmodel, parent, false));
        }

        @Override
        public void onBindViewHolder(AppModelViewHolder holder, int position) {
            AppModel appModel = appModelList.get(position);
            holder.imgIcon.setImageDrawable(ContextCompat.getDrawable(context, appModel.getIcon()));
            holder.tvAppName.setText(appModel.getDesc());
        }

        @Override
        public int getItemCount() {
            return appModelList.size();
        }

        public class AppModelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @BindView(R.id.img_icon)
            public ImageView imgIcon;
            @BindView(R.id.tv_app_name)
            public TextView tvAppName;
            @BindView(R.id.rel_root)
            public RelativeLayout relRoot;

            public AppModelViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                relRoot.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                AppModel appModel = appModelList.get(getLayoutPosition());
                appModel.getOnAppStart().OnAppStart(context, appModel.getmClass());
            }
        }
    }
}
