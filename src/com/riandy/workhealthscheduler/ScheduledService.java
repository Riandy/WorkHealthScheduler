package com.riandy.workhealthscheduler;

import com.riandy.flexiblealertscheduling.Notif;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ScheduledService extends IntentService {

	public ScheduledService() {
		super("My service");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// Do something, fire a notification or whatever you want to do here
		//Toast.makeText(getBaseContext(), "Time to check your whatsapp!", Toast.LENGTH_LONG).show();
		Notif notif = new Notif(getBaseContext());
		notif.setAppToRun("com.whatsapp");
		notif.set_title("Running whatsapp");
		notif.set_content("Service is running!!");
		notif.setNotification();		
	}
}