package liusuwan.androiddemo.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.stylingandroid.prism.Prism;
import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.TintFilter;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;

public class PrismActivity extends AppCompatActivity {
    private static final float TINT_FACTOR_50_PERCENT = 0.5f;

    @BindView(R.id.lin_patte)
    public LinearLayout linPatte;
    @BindView(R.id.img_icon)
    ImageView mImageView;

    Prism prism;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prism);
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
        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.app_bar);
        Filter tint = new TintFilter(TINT_FACTOR_50_PERCENT);
        prism = Prism.Builder.newInstance()
                .background(appBar)
                .background(toolbar)
                .background(getWindow())
                .background(fab, tint)
                .build();
    }

    public void setColor(View view) {
        if (view.getId() == R.id.btn_black) {
            prism.setColor(Color.BLACK);
            setDrawColor(Color.BLACK);
        } else if (view.getId() == R.id.btn_white) {
            prism.setColor(Color.WHITE);
            setDrawColor(Color.WHITE);
        } else if (view.getId() == R.id.btn_blue) {
            prism.setColor(Color.BLUE);
            setDrawColor(Color.BLUE);
        } else if (view.getId() == R.id.btn_red) {
            prism.setColor(Color.RED);
            setDrawColor(Color.RED);
        } else if (view.getId() == R.id.btn_gray) {
            prism.setColor(Color.GRAY);
            setDrawColor(Color.GRAY);
        } else if (view.getId() == R.id.btn_yellow) {
            prism.setColor(Color.YELLOW);
            setDrawColor(Color.YELLOW);
        } else if (view.getId() == R.id.btn_green) {
            prism.setColor(Color.GREEN);
            setDrawColor(Color.GREEN);
        }
    }

    public void setDrawColor(int i) {
        mImageView.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }
}
