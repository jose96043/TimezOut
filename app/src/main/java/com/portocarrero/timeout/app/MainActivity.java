package com.portocarrero.timeout.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import com.portocarrero.timeout.TimezOut;
import com.portocarrero.timeout.TimezOutListener;

  public class MainActivity extends AppCompatActivity implements TimezOutListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TimezOut.startTimer();
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      TimezOut.restartTimer();
    }
    return super.onTouchEvent(event);
  }

  @Override protected void onStart() {
    super.onStart();
    TimezOut.register(this);
  }

  @Override protected void onPause() {
    super.onPause();
    TimezOut.unregister();
  }

  @Override public void onTimeOut() {
    Log.d("TAG", "onTimeOut");
  }
}
