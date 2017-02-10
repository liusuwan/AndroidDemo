package liusuwan.androiddemo.usercontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jack on 2017-02-10.
 */

public class BlueProgress extends View {
    public Paint paint1, paint2, paint3;
    public Point centerPoint;

    public BlueProgress(Context context) {
        super(context);
    }

    public BlueProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BlueProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BlueProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerPoint = new Point(w / 2, h / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerPoint.x, centerPoint.y);
       // canvas.drawCircle();
    }

    public void initPaint() {

    }


}
