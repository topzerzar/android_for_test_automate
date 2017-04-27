package topzme.couseandroid;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by weerapon on 9/15/16.
 */
public class CustomView extends View {


    private boolean isBlue = false;
    private boolean isDown = false;



    private GestureDetector gestureDetector;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initWithAttrs(attrs, 0, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    public void init() {
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                // Decide : Care or not Care?
                //
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                // Do whatever toy want
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {

                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

                isBlue = !isBlue;
                invalidate();
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        if (isBlue) {
            p.setColor(0xFF0000FF); // #Blue
        } else {
            p.setColor(0xFFFF0000); // #AARRGGBB
        }

        canvas.drawLine(0, 0,
                getMeasuredWidth(), getMeasuredHeight(), p);

        if (isDown) {
            p.setColor(0xFF00FF00); // Green

            // Convert dp to px
            float px = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    5,
                    getContext().getResources().getDisplayMetrics());

            p.setStrokeWidth(px);

            canvas.drawLine(0, getMeasuredWidth(), getMeasuredHeight(), 0, p);
        }

    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomView,
                defStyleAttr, defStyleRes);

        try {
            isBlue = a.getBoolean(R.styleable.CustomView_isBlue, false);
        } finally {
            a.recycle();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDown = true;
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                isDown = false;
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                isDown = false;
                invalidate();
                return true;
            case MotionEvent.ACTION_CANCEL:
                isDown = false;
                invalidate();
                return true;
        }

        return super.onTouchEvent(event);

    }

    @Override
    protected Parcelable onSaveInstanceState() {
//        Parcelable saveInstanceStateParcelable = super.onSaveInstanceState();
//        CustomViewSavedState savedState = new CustomViewSavedState(saveInstanceStateParcelable);
//        savedState.setBlue(isBlue);
//        return saveInstanceStateParcelable;
        Parcelable superState = super.onSaveInstanceState();
        BundleSavedState savedState = new BundleSavedState(superState);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isBlue", isBlue);
        savedState.setBundle(bundle);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
//        CustomViewSavedState restoreState = (CustomViewSavedState) state;
//        super.onRestoreInstanceState(restoreState.getSuperState());
//        isBlue = restoreState.isBlue();
        BundleSavedState bundleSavedState = (BundleSavedState) state;
        super.onRestoreInstanceState(bundleSavedState.getSuperState());
        Bundle bundle = bundleSavedState.getBundle();
        isBlue = bundle.getBoolean("isBlue");
     }
}
