package liusuwan.androiddemo.usercontrol;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Jack on 2017-02-09.
 */

public class SlideMenu extends RelativeLayout {

    private ViewDragHelper mDragger;
    public View v1, v2;
    private Point mAutoBackOriginPos = new Point();

    public SlideMenu(Context context) {
        super(context);
        initView();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }


    public void initView() {
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == v2;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (-left >= child.getWidth() / 2) {
                    return -child.getWidth() / 2;
                } else if (left > 0) {
                    return 0;
                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return 0;
            }


            //手指释放的时候回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //mAutoBackView手指释放时可以自动回去
                if (releasedChild == v2) {
                    // mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);

                    if (-releasedChild.getX() < v1.getWidth() / 2) {
                        mDragger.settleCapturedViewAt(getPaddingLeft(), 0);
                    } else {
                        mDragger.settleCapturedViewAt(-v1.getWidth() / 2, 0);
                    }
                    invalidate();
                }
            }
        });
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = v2.getLeft();
        mAutoBackOriginPos.y = v2.getTop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        v1 = getChildAt(0);
        v2 = getChildAt(1);
    }

    public void review() {
        if (mDragger.smoothSlideViewTo(v2, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
            postInvalidate();
        }
    }
}
