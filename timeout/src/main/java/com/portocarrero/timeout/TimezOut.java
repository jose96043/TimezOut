package com.portocarrero.timeout;

import android.content.Context;

public class TimezOut {

  public static void initialize(Context context, Configuration configuration) {
    Timer.Android.initialize(context, configuration.getTimeOutInMillis());
  }

  public static void startTimer() {
    Timer.Android.get().startTimer();
  }

  public static void stopTimer() {
    Timer.Android.get().stopTimer();
  }

  public static void restartTimer() {
    Timer.Android.get().stopTimer();
    Timer.Android.get().startTimer();
  }

  public static void register(TimezOutListener listener) {
    Timer.Android.get().register(listener);
  }

  public static void unregister() {
    Timer.Android.get().unregister();
  }

  public static final class Configuration {
    private int timeOutInMillis;

    public Configuration(int timeOutInMillis) {
      this.timeOutInMillis = timeOutInMillis;
    }

    public int getTimeOutInMillis() {
      return timeOutInMillis;
    }
  }
}
