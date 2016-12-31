# TimezOut
Android implementation for timeout event. This library was born after not been able to find how other people have implemented inactivity timers on their applications. Hopefully this can help someone having to develop something along those lines.

This library can be used in many scenarios such as an inactivity timeout when combined with onTouchEvent. Thanks to the callback interface, scenarios such as logout because of inactvity can be handled. 

## Usage

#### Setup

Before using the library fist you will need to initialize it. The library has two dependencies. The first one is context and the second one is the a configuration object which contains the timeout period in milliseconds.

On this example we have set the timeout to 5000 milliseconds or 5 seconds.

```java
public class SampleApp extends Application {

  @Override public void onCreate() {
    TimezOut.initialize(this, new TimezOut.Configuration(5000));
  }
}
```

Make sure the following class TimezOutBroadcast is registered on the Manifest.xml as a receiver. Without this, you will not be able to know when a timeout happened.

```xml

<application>
	....
    
  	<receiver android:name="com.portocarrero.timeout.TimezOutBroadcast"/>
	
  	....
</application>
```



#### Methods

To start the timer you'll need to call the following

```java
TimezOut.startTimer();
```

In order to listen for a timeout event you will need to implement the TimezOutListener interface. Then register it to the TimezOut library using the register method and pass the listener. Once a timeout event occurs, the onTimeOut method will be called.

```java
public class MainActivity extends AppCompatActivity implements TimezOutListener {
  ....
 @Override protected void onStart() {
    super.onStart();
    TimezOut.register(this);
 }
  
 @Override public void onTimeOut() {
    Log.d("TAG", "onTimeOut");
  } 
}
```

You can also unregister the listener by calling:

```java
TimezOut.unregister();
```

Other methods available are:

```java
TimezOut.stopTimer();
TimezOut.restartTimer();
```

Please refer to the sample application and please feel free to let me know if anything can be improved :)  

Planning to add other things later on once I have time.
