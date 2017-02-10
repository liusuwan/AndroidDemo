package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import liusuwan.androiddemo.R;
import liusuwan.androiddemo.usercontrol.SlideMenu;

public class ViewDragActivity extends AppCompatActivity {

    public TextView tvReview;
    public SlideMenu slide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvReview = (TextView) findViewById(R.id.tv_review);
        slide = (SlideMenu) findViewById(R.id.slide);
        tvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slide.review();
            }
        });


    }
}
