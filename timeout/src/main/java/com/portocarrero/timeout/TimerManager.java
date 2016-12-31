package com.portocarrero.timeout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;

class TimerManager {
  static final String RESULT_RECEIVER = "result_receiver";
  private static final String TAG = TimerManager.class.getSimpleName();
  private final int timeoutInMillis;
  private final Context context;
  private final AlarmManager alarmManager;
  private final Intent intent;
  private Listener listener;

  TimerManager(Context context, int timeoutInMillis) {
    this.context = context;
    this.timeoutInMillis = timeoutInMillis;
    alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    intent = new Intent(context, TimezOutBroadcast.class);
    intent.putExtra(RESULT_RECEIVER, new ResultReceiver(new Handler()) {
      @Override protected void onReceiveResult(int resultCode, Bundle resultData) {
        Log.d(TAG, "onReceiveResult()");
        listener.onAlarmTriggered();
      }
    });
  }

  void startTimer(Listener listener) {
    this.listener = listener;
    PendingIntent pendingIntent =
        PendingIntent.getBroadcast(context, TimezOutBroadcast.TIMEZOUT_BROADCAST_REQUEST_CODE,
            intent, PendingIntent.FLAG_CANCEL_CURRENT);
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
        SystemClock.elapsedRealtime() + timeoutInMillis, pendingIntent);
  }

  void stopTimer() {
    PendingIntent pendingIntent =
        PendingIntent.getBroadcast(context, TimezOutBroadcast.TIMEZOUT_BROADCAST_REQUEST_CODE,
            intent, PendingIntent.FLAG_CANCEL_CURRENT);
    alarmManager.cancel(pendingIntent);
    pendingIntent.cancel();
  }

  interface Listener {
    void onAlarmTriggered();
  }
}
