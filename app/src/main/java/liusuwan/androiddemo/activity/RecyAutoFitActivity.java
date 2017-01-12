package liusuwan.androiddemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
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

public class RecyAutoFitActivity extends AppCompatActivity {

    @BindView(R.id.recy_content)
    public RecyclerView recyContent;
    @BindView(R.id.tv_scrValue)
    public TextView tvScrValue;
    @BindView(R.id.tv_scrValue2)
    public TextView tvScrValue2;
    @BindView(R.id.tv_state)
    TextView tvState;

    public int scrValue = 0;
    public boolean isJump = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_auto_fit);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyContent.setHasFixedSize(true);
        recyContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrValue += dx;
                tvScrValue.setText(scrValue + "  " + dy);
            }


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                tvState.setText(newState + "");
                if (newState == 0) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int itemWidth = manager.getChildAt(0).getWidth();
                    int itemNo = scrValue / itemWidth;
                    if (isJump) {
                        isJump = false;
                    } else {
                        isJump = true;
                        recyContent.smoothScrollToPosition(itemNo);
                    }
                    //recyContent.smoothScrollToPosition(itemNo);
                }
            }
        });

        recyContent.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                holder.itemView.getWidth();
            }
        });
        recyContent.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false));
        recyContent.setAdapter(new DataAdapter(this, DataHelper.getTestData()));
    }


    //适配器
    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

        public Context context;
        public List<String> dataList = new ArrayList<>();

        public DataAdapter(Context context, List<String> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        @Override
        public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
        }

        @Override
        public void onBindViewHolder(DataViewHolder holder, int position) {
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
