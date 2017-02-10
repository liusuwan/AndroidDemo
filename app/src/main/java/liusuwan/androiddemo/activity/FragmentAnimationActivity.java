package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import liusuwan.androiddemo.fragment.ParentFragment;

public class FragmentAnimationActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_animation);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParentFragment parentFragment = new ParentFragment();

                Slide slideTransition = new Slide(Gravity.LEFT);
                parentFragment.setEnterTransition(slideTransition);
                parentFragment.setAllowEnterTransitionOverlap(true);
                parentFragment.setAllowReturnTransitionOverlap(true);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame_root, parentFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
