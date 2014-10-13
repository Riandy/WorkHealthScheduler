package com.riandy.workhealthscheduler;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;

import com.riandy.flexiblealertscheduling.*;
import com.riandy.workhealthscheduler.Helper.SaveSharedPreference;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * Testing scheduled notification
		 */
		/*AlarmManager alarmMgr;
		PendingIntent alarmIntent;
		alarmMgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
		alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		        SystemClock.elapsedRealtime() +
		        60 * 1000, alarmIntent)
		*/
		// check if the user has already logged in. If not, redirect to Login Page
		if( !isLoggedIn() ) {
			Intent in = new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(in);
		}
	
		findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(getApplicationContext(),AlarmListActivity1.class);
				startActivity(in);				
			}
		});
		
		/*
		 * Test to create notifications
		 */
		Notif notif = new Notif(getApplicationContext());
		notif.setAppToRun("com.whatsapp");
		notif.set_title("Hello");
		notif.set_content("We are the world");
		notif.setNotification();

		//Toast.makeText(getApplicationContext(), notif.getAppToRun(), Toast.LENGTH_LONG).show();
		PInfo pinfo = new PInfo(getApplicationContext());
		pinfo.getPackages();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private boolean isLoggedIn() {
		// TODO : implement the log in check feature. add facebook, google account API
		if(SaveSharedPreference.getUserName(MainActivity.this).length() == 0)
			return false;
		else
			return true;
	}
	
	public void setNotification(Context ctx,Class<?> callback) {
		
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(ctx)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("My notification")
		        .setContentText("Hello World!");
       // .setSmallIcon(R.drawable.notification_icon)

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(ctx, callback);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(callback);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		int mId = 01234;
		mNotificationManager.notify(mId, mBuilder.build());		
		
	}	
}
