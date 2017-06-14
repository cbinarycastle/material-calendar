package kr.djsch.dsmhs.beinone.materialcalendar;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by BeINone on 2017-06-14.
 */

public class MaterialPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_ALPHA = 0.1f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(MIN_ALPHA);
        } else if (position <= 1) { // [-1,1]
            // Fade the page out.
            float alphaFactor = 1 - (Math.abs(position) - MIN_ALPHA);
            page.setAlpha(alphaFactor);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(MIN_ALPHA);
        }
    }
}
