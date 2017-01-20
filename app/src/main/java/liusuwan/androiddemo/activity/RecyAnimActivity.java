package liusuwan.androiddemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class RecyAnimActivity extends AppCompatActivity {

    @BindView(R.id.recy_content)
    RecyclerView recyContent;

    public RecyAnimAdapter recyAnimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_anim);
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

        recyAnimAdapter = new RecyAnimAdapter(this, DataHelper.getTestData());
        recyContent.setHasFixedSize(true);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyContent.setLayoutManager(manager);
        recyContent.setAdapter(recyAnimAdapter);
    }


    //适配器
    public class RecyAnimAdapter extends RecyclerView.Adapter<RecyAnimAdapter.RecyAnimViewholder> {

        public List<String> dataList = new ArrayList<>();
        public Context context;

        public RecyAnimAdapter(Context context, List<String> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        @Override
        public RecyAnimViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyAnimViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_anim, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyAnimViewholder holder, int position) {
            holder.tvName.setText(dataList.get(position));
            holder.imgIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_unity));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class RecyAnimViewholder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.img_icon)
            ImageView imgIcon;

            public RecyAnimViewholder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
