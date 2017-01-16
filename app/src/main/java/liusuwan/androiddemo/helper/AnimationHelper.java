package liusuwan.androiddemo.helper;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import java.util.Random;

import liusuwan.androiddemo.animation.CubeBottomOutAnimation;
import liusuwan.androiddemo.animation.CubeTopInAnimation;

/**
 * Created by Jack on 2017-01-13.
 */

public class AnimationHelper {

    static Random random = new Random();
    static int animTime = 3000;

    //开始动画
    public static void startAnim(View view1, View view2) {
        switch (random.nextInt(7)) {
            case 0:
                view1.startAnimation(translateUpHide());
                view2.startAnimation(translateUpShow());
                break;
            case 1:
                view1.startAnimation(CubeButtomOut());
                view2.startAnimation(CubeTopIn());
                break;
            case 2:
                view1.startAnimation(alphaHide());
                view2.startAnimation(alphaShow());
                break;
            case 3:
                view1.startAnimation(alphaHide());
                view2.startAnimation(alphaShow());
                break;
            case 4:
                view1.startAnimation(translateDownHide());
                view2.startAnimation(translateDownShow());
                break;
            case 5:
                view2.startAnimation(translateUpShow());
                break;
            case 6:
                view2.startAnimation(translateDownShow());
                break;
        }
    }

    //方块出
    public static Animation CubeButtomOut() {
        CubeBottomOutAnimation cubeBottomOutAnimation = new CubeBottomOutAnimation();
        cubeBottomOutAnimation.setDuration(animTime);
        cubeBottomOutAnimation.setFillBefore(true);
        cubeBottomOutAnimation.setFillAfter(false);
        return cubeBottomOutAnimation;
    }

    //方块入
    public static Animation CubeTopIn() {
        CubeTopInAnimation cubeTopInAnimation = new CubeTopInAnimation();
        cubeTopInAnimation.setDuration(animTime);
        cubeTopInAnimation.setFillAfter(true);
        return cubeTopInAnimation;
    }


    //向上位移显示
    public static TranslateAnimation translateUpShow() {
        TranslateAnimation mShowAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(animTime);
        mShowAction.setInterpolator(new EaseCubicInterpolator(0.6f, 0.2f, 0.7f, 0.3f));
        mShowAction.setFillAfter(true);
        return mShowAction;
    }

    //向上位移隐藏
    public static TranslateAnimation translateUpHide() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setInterpolator(new EaseCubicInterpolator(0.6f, 0.2f, 0.7f, 0.3f));
        mHiddenAction.setDuration(animTime);
        mHiddenAction.setFillBefore(true);
        mHiddenAction.setFillAfter(false);
        return mHiddenAction;
    }

    //向下位移显示
    public static TranslateAnimation translateDownShow() {
        TranslateAnimation mShowAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(animTime);
        mShowAction.setInterpolator(new EaseCubicInterpolator(0.6f, 0.2f, 0.7f, 0.3f));
        mShowAction.setFillAfter(true);
        return mShowAction;
    }

    //向下位移隐藏
    public static TranslateAnimation translateDownHide() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setInterpolator(new EaseCubicInterpolator(0.6f, 0.2f, 0.7f, 0.3f));
        mHiddenAction.setDuration(animTime);
        mHiddenAction.setFillBefore(true);
        mHiddenAction.setFillAfter(false);
        return mHiddenAction;
    }

    //透明度显示
    public static AlphaAnimation alphaShow() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(animTime);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    //透明度隐藏
    public static AlphaAnimation alphaHide() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(animTime);
        alphaAnimation.setFillBefore(true);
        alphaAnimation.setFillAfter(false);
        return alphaAnimation;
    }

    public static ObjectAnimator turnShow(View view) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setDuration(animTime);
        objectAnimator.ofFloat(view, "rotationX", 60F, 30F);
        return objectAnimator;
    }

}
