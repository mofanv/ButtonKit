package com.fanvincentmo.buttonkitlib;


import android.view.MotionEvent;

public class getFeatures {

    //init x,y,timestamp of tap down and up
    int xDown = 0;
    int yDown = 0;
    int xUp = 0;
    int yUp = 0;
    Long tsDown = (long) 0;
    Long tsUp = (long) 0;

    /**
     * get features of the touch behavior:
     * xDown,yDown,tsDown,xUp,yUp,tsUp
     * output other features from motion, button, screen
     *
     * @return these features
     */
    public String getTapFeatures(final Object activityTarget, final Object buttonTarget,
                                 MotionEvent event) {

        // get raw x, y, timestamp values
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        Long tsLong = System.currentTimeMillis();

        // init return result
        String returnResult = "null";

        switch (event.getAction()) {

            // if the action is tap down
            case MotionEvent.ACTION_DOWN:
                // save data to xdown ydown tsdown
                xDown = x;
                yDown = y;
                tsDown = tsLong;

                // return null
                returnResult = "null";
                break;

            // if the action is tap up
            case MotionEvent.ACTION_UP:
                // save data to xup yup tsup
                xUp = x;
                yUp = y;
                tsUp = tsLong;

                // return all features
                returnResult = outputTouchData(activityTarget, buttonTarget);
                break;
        }
        return (returnResult);
    }

    /**
     * get features of the touch behavior:
     * xDown,yDown,tsDown,xUp,yUp,tsUp
     * output other features from motion, button, screen
     *
     * @return these features
     */
    private String outputTouchData(final Object activityTarget, final Object buttonTarget) {

        // init class ButtonFeatures, ScreenFeatures, MotionListener
        ButtonFeatures bf = new ButtonFeatures();
        ScreenFeatures sf = new ScreenFeatures();
        MotionListener ml = new MotionListener();

        // get screen features, button features, and motion features
        String screenFeatures = sf.getScreenFeatures(activityTarget);
        String buttonFeatures = bf.getButtonFeatures(buttonTarget);
        String motionFeatures = ml.getMotionFeatures();

        // return all features
        return (xDown + "," + yDown + "," + tsDown + "," + xUp + "," + yUp +
                "," + tsUp + "," + screenFeatures + "," + buttonFeatures + "," + motionFeatures);
    }

    /**
     * @return csv header of all features
     */
    public String getCsvHeader() {
        String csvHeader = "xDown,yDown,tsDown,xUp,yUp,tsUp," +
                "scrId,scrRotation,scrHeightPixels,scrWidthPixels,scrDensity,scrDensityDpi," +
                "scrXdpi,scrYdpi,scrInches," +
                "butId,butHeight,butWidth,butTextSize,butX,butY," +
                "motionLevel";
        return (csvHeader);
    }

}