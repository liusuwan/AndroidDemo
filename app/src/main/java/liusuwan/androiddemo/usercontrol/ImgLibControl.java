package liusuwan.androiddemo.usercontrol;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import liusuwan.androiddemo.R;
import liusuwan.androiddemo.helper.AnimationHelper;

/**
 * Created by Jack on 2017-01-13.
 */

public class ImgLibControl extends RelativeLayout {


    public Context context;
    Random random = new Random();
    Handler baseHandler = new Handler();
    ImageView imageView1, imageView2;
    ViewGroup relRoot;

    public ImgLibControl(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public ImgLibControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public ImgLibControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.uc_imglib, this, true);
        relRoot = (ViewGroup) view.findViewById(R.id.rel_root);
        imageView1 = (ImageView) view.findViewById(R.id.img1);
        imageView2 = (ImageView) view.findViewById(R.id.img2);
        startChange();
    }

    public void startChange() {
        changeImage();
    }

    //切换图片
    public void changeImage() {
        int delay = random.nextInt(4000) + 1000;
        Log.d("delay", String.valueOf(delay));
        baseHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (imageView1.getVisibility() == VISIBLE) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView1, "rotationX", 0f, 90f);
                    ObjectAnimator translate = ObjectAnimator.ofFloat(imageView1, "Y", 0f, -imageView1.getHeight() / 2);
                    translate.setRepeatCount(1);
                    translate.setRepeatMode(Animation.REVERSE);
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView1, "scaleX", 1f, 0.8f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView1, "scaleY", 1f, 0.8f);
                    animatorSet.setDuration(3000);
                    animatorSet.play(rotation).with(translate).with(scaleX).with(scaleY);//两个动画同时开始
                    animatorSet.start();


                    imageView2.setVisibility(VISIBLE);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    ObjectAnimator rotation1 = ObjectAnimator.ofFloat(imageView2, "rotationX", -90f, 0f);
                    ObjectAnimator translate1 = ObjectAnimator.ofFloat(imageView2, "Y", imageView2.getHeight() , 0f);
                    ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(imageView2, "scaleX", 0.8f, 1f);
                    ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(imageView2, "scaleY", 0.8f, 1f);
                    animatorSet1.setDuration(3000);
                    animatorSet1.play(rotation1).with(translate1).with(scaleX1).with(scaleY1);//两个动画同时开始
                    animatorSet1.start();
                    imageView1.setVisibility(GONE);
                } else {
                    AnimatorSet animatorSet = new AnimatorSet();
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView2, "rotationX", 0f, 90f);
                    ObjectAnimator translate = ObjectAnimator.ofFloat(imageView2, "Y", 0f, -imageView2.getHeight() / 2);
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView2, "scaleX", 1f, 0.8f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView2, "scaleY", 1f, 0.8f);
                    animatorSet.setDuration(3000);
                    animatorSet.play(rotation).with(translate).with(scaleX).with(scaleY);//两个动画同时开始
                    animatorSet.start();

                    imageView1.setVisibility(VISIBLE);
                    AnimatorSet animatorSet1 = new AnimatorSet();
                    ObjectAnimator rotation1 = ObjectAnimator.ofFloat(imageView1, "rotationX", -90f, 0f);
                    ObjectAnimator translate1 = ObjectAnimator.ofFloat(imageView1, "Y", imageView1.getHeight() / 2, 0f);
                    ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(imageView1, "scaleX", 0.8f, 1f);
                    ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(imageView1, "scaleY", 0.8f, 1f);
                    animatorSet1.setDuration(3000);
                    animatorSet1.play(rotation1).with(translate1).with(scaleX1).with(scaleY1);//两个动画同时开始
                    animatorSet1.start();

                    imageView2.setVisibility(GONE);
                }
                changeImage();
            }
        }, 5000);
    }
}
