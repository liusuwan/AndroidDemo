package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;

public class MPActivity extends AppCompatActivity {

    @BindView(R.id.chart1)
    HorizontalBarChart horizontalBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp);
        ButterKnife.bind(this);
    }
}
