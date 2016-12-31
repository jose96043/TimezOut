package com.portocarrero.timeout.app;

import android.app.Application;
import com.portocarrero.timeout.TimezOut;

public class SampleApp extends Application {

  @Override public void onCreate() {
    TimezOut.initialize(this, new TimezOut.Configuration(5000));
  }
}
