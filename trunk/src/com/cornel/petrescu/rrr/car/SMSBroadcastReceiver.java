package com.cornel.petrescu.rrr.car;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.cornel.petrescu.rrr.car.activities.SendReplyActivity;
import com.cornel.petrescu.rrr.car.persistence.dao.CARDaoFactory;
import com.cornel.petrescu.rrr.car.persistence.dao.DispatcherDao;
import com.cornel.petrescu.rrr.car.persistence.dto.DispatcherDTO;

public class SMSBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		String phoneNumber = null;

		// ---get the SMS message passed in---
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		
		if (bundle != null) {
			// ---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i = 0; i < msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				phoneNumber = msgs[i].getOriginatingAddress();
			}
		}

		if (phoneNumber == null) {
			return;
		}

		DispatcherDao dispatcherDao = CARDaoFactory.getDispatcherDao(context);
		dispatcherDao.open();
		DispatcherDTO dispatcher = dispatcherDao.getByPhoneNumber(phoneNumber);
		dispatcherDao.close();
		if (dispatcher == null) {
			return;
		}

		// // sms is valid, we need to start an activity.
		// CharSequence text = String.format(
		// "Received message from dispatcher %s - %s",
		// dispatcher.getNume(), dispatcher.getTelefon());
		// int duration = Toast.LENGTH_SHORT;
		Intent i = new Intent(context, SendReplyActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra("dispatcher", dispatcher);
		context.startActivity(i);
		// Toast toast = Toast.makeText(context, text, duration);
		// toast.show();
	}
}
