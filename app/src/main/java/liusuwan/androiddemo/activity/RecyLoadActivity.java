package liusuwan.androiddemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import liusuwan.androiddemo.helper.DataHelper;

public class RecyLoadActivity extends AppCompatActivity {

    @BindView(R.id.recy_content)
    public RecyclerView recyContent;
    @BindView(R.id.swip_content)
    public SwipeRefreshLayout swipContent;

    public DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_load);
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

        swipContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        recyContent.setHasFixedSize(true);
        recyContent.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new DataAdapter(this, DataHelper.getTestData());
        recyContent.setAdapter(dataAdapter);
        swipContent.setProgressViewOffset(false, 0, 100);
        getData();
    }

    //获取数据
    public void getData() {
        swipContent.setRefreshing(true);
        dataAdapter.setDataList(DataHelper.getTestData());
        dataAdapter.notifyDataSetChanged();
        swipContent.setRefreshing(false);
    }

    //适配器
    public class DataAdapter extends RecyclerView.Adapter<RecyLoadActivity.DataAdapter.DataViewHolder> {

        public Context context;
        public List<String> dataList = new ArrayList<>();

        public void setDataList(List<String> dataList) {
            this.dataList = dataList;
        }

        public DataAdapter(Context context, List<String> dataList) {
            this.context = context;
            this.dataList = dataList;
        }


        @Override
        public RecyLoadActivity.DataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyLoadActivity.DataAdapter.DataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyLoadActivity.DataAdapter.DataViewHolder holder, int position) {
            holder.imgIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_unity));
            holder.tvName.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class DataViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img_icon)
            public ImageView imgIcon;
            @BindView(R.id.tv_name)
            public TextView tvName;

            public DataViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
