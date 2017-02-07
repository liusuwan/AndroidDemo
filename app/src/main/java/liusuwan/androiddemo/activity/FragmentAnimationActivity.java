package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import liusuwan.androiddemo.R;
import liusuwan.androiddemo.fragment.ParentFragment;

public class FragmentAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_animation);
        initView();
    }

    public void initView() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_root,new ParentFragment(), "parent");
        ft.commit();
    }
}
