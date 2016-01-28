package com.zing.boundservicesample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BrainyActivity extends Activity implements OnClickListener, ServiceConnection {
  private Intent intent;
  private Intent workIntent;
  private Intent bindIntent;

  private BrainyService brainyService;

  private Messenger messenger;

  private Button startService;
  private Button stopService;
  private Button startIntentService;
  private Button stopIntentService;
  private Button boundService;
  private Button unboundService;
  private Button messageService;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_brainy);

    intent = new Intent(getApplicationContext(), BrainyService.class);
    workIntent = new Intent(getApplicationContext(), BrainyIntentService.class);

    startService = (Button) findViewById(R.id.start);
    startService.setOnClickListener(this);

    stopService = (Button) findViewById(R.id.stop);
    stopService.setOnClickListener(this);

    startIntentService = (Button) findViewById(R.id.startIntentService);
    startIntentService.setOnClickListener(this);

    stopIntentService = (Button) findViewById(R.id.stopIntentService);
    stopIntentService.setOnClickListener(this);

    boundService = (Button) findViewById(R.id.bindService);
    boundService.setOnClickListener(this);

    unboundService = (Button) findViewById(R.id.unbindService);
    unboundService.setOnClickListener(this);

    messageService = (Button) findViewById(R.id.messageService);
    messageService.setOnClickListener(this);
  }

  public void startService() {
    startService(intent);
  }

  public void stopService() {
    stopService(intent);
  }

  public void startIntentService() {
    startService(workIntent);
  }

  public void stopIntentService() {
    stopService(workIntent);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
    case R.id.start:
      startService();
      break;

    case R.id.stop:
      stopService();
      break;

    case R.id.startIntentService:
      startIntentService();
      break;

    case R.id.stopIntentService:
      stopIntentService();
      break;

    case R.id.bindService:
      bindService();
      break;

    case R.id.unbindService:
      unbindService();
      break;

    case R.id.messageService:
      try {
        messageService();
      } catch (RemoteException e) {
        e.printStackTrace();
      }
      break;

    default:
      break;
    }
  }

  private void bindService() {
    bindIntent = new Intent(getApplicationContext(), BrainyService.class);
    bindService(bindIntent, this, Context.BIND_AUTO_CREATE);
  }

  private void unbindService() {
    unbindService(this);
  }

  private void messageService() throws RemoteException {
    Message message = Message.obtain(null, BrainyService.HANDLER_CALL, 0, 0);

    messenger.send(message);
  }

  /*
   * @Override public void onServiceConnected(ComponentName name, IBinder
   * service) { BrainyBinder binder = (BrainyBinder) service; brainyService =
   * binder.getService(); brainyService.printUsage(); }
   */

  @Override public void onServiceConnected(ComponentName name, IBinder service) {
    messenger = new Messenger(service);
  }

  @Override public void onServiceDisconnected(ComponentName name) {

  }
}
