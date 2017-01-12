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
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import liusuwan.androiddemo.helper.DataHelper;
import liusuwan.androiddemo.model.ChildItem;
import liusuwan.androiddemo.model.ParentItem;

public class RecyAddRecyActivity extends AppCompatActivity {

    @BindView(R.id.recy_content)
    RecyclerView recyConetnt;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recy_add_recy);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        recyConetnt.setHasFixedSize(true);
        recyConetnt.setLayoutManager(new LinearLayoutManager(this));
        recyConetnt.setAdapter(new RecyParentAdapter(this, DataHelper.getRecyAddRecyData()));
    }


    //父适配器
    public class RecyParentAdapter extends RecyclerView.Adapter<RecyParentAdapter.RecyParentViewHolder> {

        public Context context;
        public List<ParentItem> parentItemList = new ArrayList<>();
        public HashMap<Integer, Integer> scrValue = new HashMap<>();

        public RecyParentAdapter(Context context, List<ParentItem> dataList) {
            this.context = context;
            this.parentItemList = dataList;
        }


        @Override
        public RecyParentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyParentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_parent, parent, false));
        }

        @Override
        public void onBindViewHolder(final RecyParentViewHolder holder, final int position) {
            RecyChildAdapter recyChildAdapter = new RecyChildAdapter(context, parentItemList.get(position).childItemList);
            holder.recyParent.setAdapter(recyChildAdapter);
            holder.tvScrValue.setText(parentItemList.get(position).getScrValue() + "");
        }

        @Override
        public int getItemCount() {
            return parentItemList.size();
        }

        @Override
        public void onViewDetachedFromWindow(RecyParentViewHolder holder) {
            parentItemList.get(holder.getLayoutPosition()).isLoadSate = false;
        }

        public class RecyParentViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.recy_parent)
            public RecyclerView recyParent;
            @BindView(R.id.tv_scr_value)
            public TextView tvScrValue;

            boolean isScrJump = false;

            public RecyParentViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                recyParent.setHasFixedSize(true);
                recyParent.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false));
                recyParent.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        ParentItem parentItem = parentItemList.get(getLayoutPosition());
                        if (!parentItem.isLoadSate) {
                            parentItem.isLoadSate = true;
                            tvScrValue.setText(parentItem.scrValue + "");
                            isScrJump = true;
                            recyclerView.scrollBy(parentItem.scrValue, 0);
                        } else {
                            if (!isScrJump) {
                                parentItem.scrValue += dx;
                                tvScrValue.setText(parentItem.scrValue + "");
                            } else {
                                isScrJump = false;
                            }
                        }
                    }
                });
            }
        }
    }

    //子适配器
    public class RecyChildAdapter extends RecyclerView.Adapter<RecyChildAdapter.RecyChildViewHolder> {

        public Context context;
        public List<ChildItem> childItemList = new ArrayList<>();

        public RecyChildAdapter(Context context, List<ChildItem> childDataList) {
            this.context = context;
            this.childItemList = childDataList;
        }

        @Override
        public RecyChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyChildViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recy_child, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyChildViewHolder holder, int position) {
            holder.tvName.setText(childItemList.get(position).getName());
            holder.imgIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_unity));
        }

        @Override
        public int getItemCount() {
            return childItemList.size();
        }

        public class RecyChildViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img_icon)
            public ImageView imgIcon;
            @BindView(R.id.tv_name)
            public TextView tvName;

            public RecyChildViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
