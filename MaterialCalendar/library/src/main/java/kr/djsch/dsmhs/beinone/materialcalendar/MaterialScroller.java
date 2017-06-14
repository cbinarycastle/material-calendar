package kr.djsch.dsmhs.beinone.materialcalendar;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by BeINone on 2017-05-28.
 */

public class MaterialScroller extends Scroller {

    private int mDuration = 5000;

    public MaterialScroller(Context context) {
        super(context);
    }

    public MaterialScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public MaterialScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}