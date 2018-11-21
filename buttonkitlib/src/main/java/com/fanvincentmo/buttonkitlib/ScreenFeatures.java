/*
 * Copyright (c) 20148. Fan Vincent Mo
 * Fan Vincent Mo, f.mo18@imperial.ac.uk
 *
 * This file is part of ButtonKit library.
 *
 * ButtonKit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ButtonKit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SensingKit-Android.  If not, see <http://www.gnu.org/licenses/>.
 */

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