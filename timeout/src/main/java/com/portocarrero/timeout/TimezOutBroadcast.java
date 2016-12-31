package com.portocarrero.timeout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;

public class TimezOutBroadcast extends BroadcastReceiver {
  static final int TIMEZOUT_BROADCAST_REQUEST_CODE = 0x1990;

  @Override public void onReceive(Context context, Intent intent) {
    ResultReceiver resultReceiver = intent.getParcelableExtra(TimerManager.RESULT_RECEIVER);
    if (resultReceiver != null) {
      resultReceiver.send(0, null);
    }
  }
}
