package liusuwan.androiddemo.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Jack on 2017-01-16.
 */

public class CubeBottomOutAnimation extends Animation {
    private Camera mCamera;
    private Matrix mMatrix;
    private int mWidth;
    private int mHeight;
    private static final int sFinalDegree = 90;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
        mMatrix = new Matrix();
        mWidth = width;
        mHeight = height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float rotate = (-sFinalDegree * interpolatedTime);
        mCamera.save();
        mCamera.translate(0, (-interpolatedTime * mHeight + mHeight), 0);
        mCamera.rotateX(rotate);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();
        mMatrix.preTranslate(-mWidth / 2, 0);
        mMatrix.postTranslate(mWidth / 2, mHeight);

        t.getMatrix().postConcat(mMatrix);
    }
}
