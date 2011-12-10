package com.cornel.petrescu.rrr.car.activities;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.cornel.petrescu.rrr.R;
import com.cornel.petrescu.rrr.car.persistence.dto.DispatcherDTO;

public class SendReplyActivity extends Activity {
	private RadioGroup radioGroup;
	private EditText etaEditText;
	private EditText locationEditText;
	private DispatcherDTO dispatcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			// caz eroare, todo.
		}
		dispatcher = (DispatcherDTO) extras.get("dispatcher");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_reply_activity);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		etaEditText = (EditText) findViewById(R.id.editText1);
		locationEditText = (EditText) findViewById(R.id.editText2);

		Button sendReplyBtn = (Button) findViewById(R.id.btnReply);
		sendReplyBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String actionName = "";
				String eta = etaEditText.getText().toString();
				String location = locationEditText.getText().toString();
				switch (radioGroup.getCheckedRadioButtonId()) {
				case R.id.radioAvailable:
					actionName = "Available";
					break;
				case R.id.radioUnavailable:
					actionName = "Unavailable";
					break;
				case R.id.radioAvailableDelayed:
					actionName = "Available Delayed";
					break;
				case R.id.radioResponding:
					actionName = "Responding";
					break;
				case R.id.radioNotResponding:
					actionName = "Not Responding";
					break;
				default:
					actionName = "unknown";
				}
				sendSMSMessage(dispatcher, actionName, eta, location);
				finish();
			}
		});

	}

	public void sendSMSMessage(DispatcherDTO dispatcher, String actionName,
			String eta, String location) {              
		        SmsManager sms = SmsManager.getDefault();
		        String message = actionName;
		        if(eta != null) {
		        	message += ", E.T.A. " + eta;
		        }
		        if(location != null) {
		        	message += ", LOCATION " + location;
		        }
		        sms.sendTextMessage(dispatcher.getTelefon(), null, message, null, null);     
	}

}
