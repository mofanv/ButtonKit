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

import android.util.Log;
import android.widget.Button;

class ButtonFeatures {

    // get button feature

    /**
     * get features of the target button:
     * id, height, width, text size, x and y coordinate on the screen
     *
     * @return these features
     */
    String getButtonFeatures(Object buttonTarget) {

        // init a String to save features
        String buttonFeatures = null;

        // whether the instance is a button
        if (buttonTarget instanceof Button) {

            // init button
            Button buttonReal = (Button) buttonTarget;

            //get id, height, width, text size, x and y coordinate on the screen
            int id = buttonReal.getId();
            int height = buttonReal.getHeight();
            int width = buttonReal.getWidth();
            float textSize = buttonReal.getTextSize();
            float xCoordinate = buttonReal.getX();
            float yCoordinate = buttonReal.getY();

            //paste and save features
            buttonFeatures = id + "," + height + "," + width + "," + textSize + "," + xCoordinate
                    + "," + yCoordinate;
        } else {

            // log the error info
            Log.e("Instance Error", "getButtonFeatures: This is not a BUTTON instance");
        }

        return (buttonFeatures);
    }
}