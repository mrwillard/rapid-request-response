package com.cornel.petrescu.rrr.car.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cornel.petrescu.rrr.R;
import com.cornel.petrescu.rrr.car.persistence.dao.CARDaoFactory;
import com.cornel.petrescu.rrr.car.persistence.dao.DispatcherDao;
import com.cornel.petrescu.rrr.car.persistence.dto.DispatcherDTO;

public class AddEditDispatcherActivity extends Activity {

	protected DispatcherDTO dispatcher;
	protected boolean add;
	protected EditText txtName;
	protected EditText txtPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_edit_dispatcher);

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			// caz eroare, todo.
		}
		dispatcher = (DispatcherDTO) extras.get("dispatcher");
		if (dispatcher == null) {
			add = true;
			dispatcher = new DispatcherDTO();
		} else {
			add = false;
		}

		txtName = (EditText) findViewById(R.id.editTxtDispatcherName);
		txtName.setText(dispatcher.getNume());

		txtPhone = (EditText) findViewById(R.id.editTxtDispatcherPhone);
		txtPhone.setText(dispatcher.getTelefon());

		Button btnSave = (Button) findViewById(R.id.buttonSaveDispatcher);
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				try {
					DispatcherDao dispatcherDao = CARDaoFactory
					.getDispatcherDao(getApplicationContext());
					dispatcherDao.create(dispatcher);
					dispatcherDao.close();
				} catch (Exception e) {
					Log.e(AddEditDispatcherActivity.class.getName(),
							"Error saving dispatcher!", e);

				}
				
				Intent i = new Intent(getBaseContext(), DispatcherListActivity.class);
				startActivity(i);
			}
			
		});

	}
}
