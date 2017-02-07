package liusuwan.androiddemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import liusuwan.androiddemo.adapter.BaseRecyclerAdapter;
import liusuwan.androiddemo.helper.DataHelper;
import liusuwan.androiddemo.usercontrol.RecyclerRefreshLayout;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecyLoadActivity extends AppCompatActivity {

    @BindView(R.id.recy_content)
    public RecyclerView recyContent;
    @BindView(R.id.swip_content)
    public RecyclerRefreshLayout swipContent;

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

        swipContent.setRecyclerView(recyContent);
        swipContent.setCanLoadMore(true);
        swipContent.setSuperRefreshLayoutListener(new RecyclerRefreshLayout.SuperRefreshLayoutListener() {
            @Override
            public void onRefreshing() {
                getData();
            }

            @Override
            public void onLoadMore() {
                dataAdapter.setState(BaseRecyclerAdapter.STATE_LOAD_MORE, true);
                loadMore();
            }
        });

        recyContent.setHasFixedSize(true);
        recyContent.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter = new DataAdapter(this, BaseRecyclerAdapter.ONLY_FOOTER);
        dataAdapter.addAll(DataHelper.getTestData());
        recyContent.setAdapter(dataAdapter);
        swipContent.setProgressViewOffset(false, 0, 100);
        getData();
    }

    //获取数据
    public void getData() {
        swipContent.setRefreshing(true);
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                subscriber.onNext(true);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                swipContent.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                swipContent.onComplete();
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    dataAdapter.clear();
                    dataAdapter.addAll(DataHelper.getTestData());
                    dataAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void loadMore() {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                subscriber.onNext(true);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                swipContent.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                swipContent.onComplete();
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    dataAdapter.addAll(DataHelper.getTestData());
                    dataAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    //适配器
    public class DataAdapter extends BaseRecyclerAdapter<String> {

        public DataAdapter(Context context, int mode) {
            super(context, mode);
        }

        @Override
        protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
            return new DataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
        }

        @Override
        protected void onBindDefaultViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
            DataViewHolder dataViewHolder = (DataViewHolder) holder;
            dataViewHolder.imgIcon.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.ic_unity));
            dataViewHolder.tvName.setText(item);
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
