package liusuwan.androiddemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import liusuwan.androiddemo.R;
import liusuwan.androiddemo.helper.CubeAnimation;

public class ChangeAnimaActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.image1)
    ImageView imageView1;

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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnim();
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnim();
            }
        });

    }

    public void startAnim() {

        imageView.startAnimation(CubeAnimation.create(CubeAnimation.DOWN,false,2000));
//        AnimatorSet animatorSet = new AnimatorSet();
//        ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView, "rotationX", 0f, 90f);
//        ObjectAnimator translate = ObjectAnimator.ofFloat(imageView, "Y", imageView.getY(), 0);
//        translate.setRepeatCount(1);
//        translate.setRepeatMode(Animation.INFINITE);
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 0.8f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 0.8f);
//        animatorSet.setDuration(3000);
//        animatorSet.setTarget(imageView);
//        animatorSet.play(rotation).with(translate).with(scaleX).with(scaleY);//两个动画同时开始
//        animatorSet.start();
//
//        AnimatorSet animatorSet1 = new AnimatorSet();
//        ObjectAnimator rotation1 = ObjectAnimator.ofFloat(imageView1, "rotationX", -90f, 0f);
//        ObjectAnimator translate1 = ObjectAnimator.ofFloat(imageView1, "Y", imageView1.getHeight(), imageView1.getY());
//        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(imageView1, "scaleX", 0.8f, 1f);
//        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(imageView1, "scaleY", 0.8f, 1f);
//        animatorSet1.setDuration(3000);
//        animatorSet1.play(rotation1).with(translate1).with(scaleX1).with(scaleY1);//两个动画同时开始
//        animatorSet1.start();

    }
}
