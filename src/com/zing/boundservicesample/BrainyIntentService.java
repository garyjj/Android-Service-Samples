package com.zing.boundservicesample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class BrainyIntentService extends IntentService {
  private static final String TAG = BrainyIntentService.class.getSimpleName();
  private int counter = 1;

  public BrainyIntentService() {
    super(null);
    Log.d(TAG, "BrainyIntentService()");
  }

  @Override public void onCreate() {
    super.onCreate();
    Log.d(TAG, "onCreate()");
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d(TAG, "onStartCommand(Intent intent, int flags, int startId)");
    return super.onStartCommand(intent, flags, startId);
  }

  @Override protected void onHandleIntent(Intent intent) {
    Log.d(TAG, "onHandleIntent(Intent intent)");
    try {
      Thread.sleep(3000);
      Log.d(TAG, "counter = " + counter++);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override public void onDestroy() {
    Log.d(TAG, "onDestroy()");
    super.onDestroy();
  }

}
