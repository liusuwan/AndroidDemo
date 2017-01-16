package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import liusuwan.androiddemo.usercontrol.ImgLibControl;

public class ChangeAnimaActivity extends AppCompatActivity {

    @BindView(R.id.img)
    public ImgLibControl imgLibControl;

    public List<String> urlList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_anima);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        urlList.add("http://img1.3lian.com/img013/v1/82/d/85.jpg");
        urlList.add("http://img1.3lian.com/img013/v1/82/d/89.jpg");
        urlList.add("http://img1.3lian.com/img013/v1/82/d/90.jpg");
        urlList.add("http://img1.3lian.com/img013/v1/82/d/100.jpg");
        urlList.add("http://img1.3lian.com/img013/v1/82/d/103.jpg");
        urlList.add("http://img1.3lian.com/img013/v1/82/d/102.jpg");
        imgLibControl.setUrlList(urlList);
    }

}
