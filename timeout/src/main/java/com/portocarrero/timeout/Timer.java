package com.portocarrero.timeout;

import android.content.Context;
import android.util.Log;

class Timer {
  private static final String TAG = Timer.class.getSimpleName();
  private static final Object LOCK = new Object();
  private static Timer instance;

  private Timer() {

  }

  private static void set(Timer timer) {
    synchronized (LOCK) {
      if (instance != null) {
        throw new IllegalStateException("Timer is already initialized");
      }
      instance = timer;
    }
  }

  private static Timer get() {
    synchronized (LOCK) {
      return instance;
    }
  }

  static class Android extends Timer {
    private final TimerManager timerManager;
    private boolean isOnAlarmTriggered = false;
    private TimezOutListener listener;
    private final TimerManager.Listener managerListener = new TimerManager.Listener() {
      @Override public void onAlarmTriggered() {
        Log.d(TAG, "onAlarmTriggered()");
        isOnAlarmTriggered = true;
        executeListenerIfRequired();
      }
    };

    private Android(Context context, int timeoutInMillis) {
      timerManager = new TimerManager(context.getApplicationContext(), timeoutInMillis);
    }

    static void initialize(Context context, int timeoutInMillis) {
      Timer.set(new Android(context, timeoutInMillis));
    }

    static Timer.Android get() {
      return (Timer.Android) Timer.get();
    }

    void startTimer() {
      timerManager.startTimer(managerListener);
    }

    void stopTimer() {
      timerManager.stopTimer();
    }

    void register(TimezOutListener listener) {
      this.listener = listener;
      executeListenerIfRequired();
    }

    void unregister() {
      this.listener = null;
    }

    private void executeListenerIfRequired() {
      if (listener != null && isOnAlarmTriggered) {
        listener.onTimeOut();
        isOnAlarmTriggered = false;
      }
    }
  }
}
