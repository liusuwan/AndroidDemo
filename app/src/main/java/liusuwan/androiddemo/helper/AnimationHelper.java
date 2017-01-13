package liusuwan.androiddemo.helper;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Jack on 2017-01-13.
 */

public class AnimationHelper {
    //位移显示
    public static TranslateAnimation translateShow() {
        TranslateAnimation mShowAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(1000);
        mShowAction.setInterpolator(new EaseCubicInterpolator(0.6f, 0.2f, 0.7f, 0.3f));
        return mShowAction;
    }

    //位移隐藏
    public static TranslateAnimation translateHide() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setInterpolator(new EaseCubicInterpolator(0.6f, 0.2f, 0.7f, 0.3f));
        mHiddenAction.setDuration(1000);
        return mHiddenAction;
    }

    //透明度显示
    public static AlphaAnimation alphaShow() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        return alphaAnimation;
    }

    //透明度隐藏
    public static AlphaAnimation alphaHide() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(1000);
        return alphaAnimation;
    }

    public static ObjectAnimator turnShow(View view) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setDuration(1000);
        objectAnimator.ofFloat(view, "rotationX", 60F, 30F);
        return objectAnimator;
    }
}
