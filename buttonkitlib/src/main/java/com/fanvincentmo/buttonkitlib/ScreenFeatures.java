package com.fanvincentmo.buttonkitlib;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;

class ScreenFeatures {

    /**
     * get features of the target screen:
     * id, rotation, widthPixels, heightPixels, density, densityDpi, xdpi, ydpi, screenInches
     *
     * @return these features
     */
    String getScreenFeatures(Object screenTarget) {

        String screenFeatures = "null";

        // whether the instance is a button
        if (screenTarget instanceof Activity) {

            // init activity
            Activity activityReal = (Activity) screenTarget;

            // read all features using the function
            screenFeatures = readAllFeatures(activityReal);

        } else {

            // log the error info
            Log.e("Instance Error", "getScreenFeatures: This is not a Activity instance");
        }

        return (screenFeatures);
    }


    /**
     * private function of getScreenFeatures
     */
    private String readAllFeatures(Activity activityReal) {

        // init a String to save features
        Point size = new Point();

        // widthPixels heightPixels
        activityReal.getWindowManager().getDefaultDisplay().getRealSize(size);
        int widthPixels = size.x;
        int heightPixels = size.y;

        // density densityDpi xdpi ydpi
        DisplayMetrics displayMetrics = activityReal.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        int densityDpi = displayMetrics.densityDpi;
        float xdpi = displayMetrics.xdpi;
        float ydpi = displayMetrics.xdpi;

        // screenInches
        double x = Math.pow(widthPixels / xdpi, 2);
        double y = Math.pow(heightPixels / ydpi, 2);
        double screenInches = Math.sqrt(x + y);

        // Id rotation
        int id = activityReal.getWindowManager().getDefaultDisplay().getDisplayId();
        int rotation = activityReal.getWindowManager().getDefaultDisplay().getRotation();

        //paste features
        return (id + "," + rotation + "," + heightPixels + "," + widthPixels + "," + density + "," +
                densityDpi + "," + xdpi + "," + ydpi + "," + screenInches);
    }
}