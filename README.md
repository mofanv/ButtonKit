# ButtonKit Library

An Android library that provides trace of touching button in your applications. 


## Supported Features

This version currently supports to get features of Screen, Button, Motion, and Tap, when users touch a listened button.
___
**Screen features of the device**
* id
* rotation
* width pixels
* height pixels
* density
* density dpi
* x-axis dpi
* y-axis dpi
* screen Inches

**Button features of the target button**
* id
* height
* width
* text size
* x coordinate on the screen
* y coordinate on the screen

**Motion features**
* motion levels that measured by norm accelerometer sensor data

**Tap features**
* x coordinate of tap down
* y coordinate of tap down
* timestamp of tap down
* x coordinate of tap up
* y coordinate of tap up
* timestamp of tap up


## Configuring the Library

- Create an app/libs directory inside your project and copy the generated ButtonKit/build/outputs/aar/SensingKitLib-release.aar (or the equivalent debug) file there.

- Edit your app/build.gradle file and add a flatDir entry within **android{}** as shown bellow:

```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```


- In the same app/build.gradle file, add ButtonKitLib as a dependency within **dependencies{}** as shown below:

```
compile(name:'buttonkitlib-release', ext:'aar')
```


## How to Use this Library

- The imports you will need are: 

```java
import com.fanvincentmo.buttonkitlib.MotionListener;
import com.fanvincentmo.buttonkitlib.getFeatures;
```
- init MotionListener class and getFeatures class when the activity starts

```java
MotionListener ml = new MotionListener();
ml.callMotionListener(MainActivity.this); // you may need to change to your activity name

final getFeatures tf = new getFeatures();
System.out.println(tf.getCsvHeader()); // get the features' name in string
```
- after you **OnTouchListener** to a button, add codes within **onTouch{}**

```java
// you may need to change to your activity, button, and event name
String tfOut = tf.getTapFeatures(MainActivity.this, clickButton, event);
if (tfOut != "null") { System.out.println(tfOut);} // output if it is not null
```

**As a contrast**
- So, if your have code at the beginning like this

```java
import ...

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

- after add ButtonKit, it will looks like this

```java
import ...
import com.fanvincentmo.buttonkitlib.MotionListener;
import com.fanvincentmo.buttonkitlib.getFeatures;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MotionListener ml = new MotionListener();
        ml.callMotionListener(MainActivity.this);

        final getFeatures tf = new getFeatures();
        System.out.println(tf.getCsvHeader());

        final Button clickButton = findViewById(R.id.button);
        clickButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String tfOut = tf.getTapFeatures(MainActivity.this, clickButton, event);
                if (tfOut != "null") { System.out.println(tfOut);}
                return true;
            }
        });
    }
}
```


## License

```
MIT License

Copyright (c) 2018. Fan Vincent Mo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.