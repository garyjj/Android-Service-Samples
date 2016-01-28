package com.zing.boundservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class BrainyService extends Service {
  private static final String TAG = BrainyService.class.getSimpleName();
  
  private final IBinder binder = new BrainyBinder();
  
  public static final int HANDLER_CALL = 0;
  
  private final Messenger messenger = new Messenger(new BrainyHandler());
  
  public class BrainyBinder extends Binder {
    public BrainyService getService() {
      return BrainyService.this;
    }
  }
  
  public class BrainyHandler extends Handler {
    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      
      switch (msg.what) {
      case HANDLER_CALL:
        Log.d(TAG, "handleMessage(Message msg) + HANDLER_CALL");
        break;

      default:
        Log.d(TAG, "handleMessage(Message msg) + default");
        break;
      }
    }
  }

  public BrainyService() {
    Log.d(TAG, "BrainyService()");
  }

  @Override public void onCreate() {
    super.onCreate();
    Log.d(TAG, "In onCreate()");
  }

  //For Binder
  /*@Override public IBinder onBind(Intent intent) {
    Log.d(TAG, "onBind(Intent intent)");
    return binder;
  }*/
  
  // For Messenger
  @Override public IBinder onBind(Intent intent) {
    Log.d(TAG, "onBind(Intent intent)");
    return messenger.getBinder();
  }
  
  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d(TAG, "onStartCommand(Intent intent, int flags, int startId)");
    return super.onStartCommand(intent, flags, startId);
  }

  @Override public boolean onUnbind(Intent intent) {
    Log.d(TAG, "onUnbind(Intent intent)");
    return super.onUnbind(intent);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "onDestroy()");
  }

  public void printUsage() {
    Log.d(TAG, "printUsage()");
  }

}