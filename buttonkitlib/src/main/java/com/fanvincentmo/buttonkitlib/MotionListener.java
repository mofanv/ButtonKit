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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MotionListener extends Activity implements SensorEventListener {

    // init the acceleration in x,y and z axis
    double ax, ay, az, aAll;
    // init the array for data list
    static ArrayList<Double> listAcceData = new ArrayList<>();

    /**
     * listening the data from accelerometer
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        // get accelerometer sensor data
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // get ax, ay, az
            ax = event.values[0];
            ay = event.values[1];
            az = event.values[2];

            // calculate norm and minus gravity
            aAll = Math.sqrt(ax * ax + ay * ay + az * az) - 9.81;
            // correct to three decimal places
            aAll = ((int) (aAll * 1000)) / 1000;

            // save to data list
            listAcceData.add(aAll);

            // if more then 100 records (around 3~5 seconds), then delete the first
            if (listAcceData.size() > 100) {
                listAcceData.remove(1);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    /**
     * calculate average values of data list
     *
     * @return the average values
     */
    private static double calculateAverage(List<Double> marks) {
        Double sum = 0.0;
        if (!marks.isEmpty()) {
            for (Double mark : marks) {
                sum += mark;
            }
            return sum / marks.size();
        }
        return sum;
    }

    /**
     * call motion listener
     */
    public void callMotionListener(Object activityTarget) {

        // whether is an activity instance
        if (activityTarget instanceof Activity) {

            // init activity window manager
            Activity activityReal = (Activity) activityTarget;

            // register the listener
            SensorManager sensorManager = (SensorManager) activityReal.getSystemService
                    (SENSOR_SERVICE);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor
                            .TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {

            // log the error info
            Log.e("Instance Error", "callMotionListener: This is not a Activity instance");
        }
    }

    /**
     * get the average accelerometer data as a measurement for motion level
     *
     * @return the average values
     */
    public String getMotionFeatures() {

        // calculate the average value
        double averageMotion = calculateAverage(listAcceData);
        return (String.valueOf(averageMotion));
    }
}