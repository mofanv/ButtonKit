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