package com.cornel.petrescu.rrr.car.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.cornel.petrescu.rrr.R;
import com.cornel.petrescu.rrr.car.persistence.dao.CARDaoFactory;
import com.cornel.petrescu.rrr.car.persistence.dao.DispatcherDao;
import com.cornel.petrescu.rrr.car.persistence.dto.DispatcherDTO;
import com.cornel.petrescu.rrr.car.persistence.tables.DispatcherTableManager;

public class DispatcherListActivity extends ListActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dispatcher_list);

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		
		DispatcherDao dispatcherDao = CARDaoFactory
				.getDispatcherDao(getApplicationContext());
		dispatcherDao.open();
		Cursor dispatcherCursor = dispatcherDao.list();
		startManagingCursor(dispatcherCursor);

		// the desired columns to be bound
		String[] columns = new String[] { DispatcherTableManager.KEY_NAME,
				DispatcherTableManager.KEY_PHONE,
				DispatcherTableManager.KEY_NAME };
		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.dispatcherList_name_entry,
				R.id.dispatcherList_number_entry,
				R.id.dispatcherList_deleteButton };
		final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
				R.layout.dispatcher_list_entry, dispatcherCursor,
				columns, to) {

			public void bindView(View view, android.content.Context context,
					final Cursor cursor) {
				String name = cursor.getString(cursor
						.getColumnIndex(DispatcherTableManager.KEY_NAME));
				String phone = cursor.getString(cursor
						.getColumnIndex(DispatcherTableManager.KEY_PHONE));

				final long id =  cursor.getLong(cursor
						.getColumnIndex(DispatcherTableManager.PK_NAME));

				TextView name_text = (TextView) view
						.findViewById(R.id.dispatcherList_name_entry);
				if (name_text != null) {
					name_text.setText(name);
				}
				TextView phone_text = (TextView) view
						.findViewById(R.id.dispatcherList_number_entry);
				if (phone_text != null) {
					phone_text.setText(phone);
				}
				ImageButton deleteButton = (ImageButton) view
						.findViewById(R.id.dispatcherList_deleteButton);
				deleteButton.setClickable(true);
				deleteButton.setFocusable(true);
				final SimpleCursorAdapter cAdapter = this;
				deleteButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						DispatcherDao dispatcherDao = CARDaoFactory
								.getDispatcherDao(getApplicationContext());
						dispatcherDao.open();

						dispatcherDao.delete(id);
						cursor.requery();
						cAdapter.notifyDataSetChanged();
						dispatcherDao.close();
					}
				});
				
				ImageButton modifyButton = (ImageButton) view.findViewById(R.id.dispatcherList_modifyButton);
				modifyButton.setClickable(true);
				modifyButton.setClickable(true);
				modifyButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						DispatcherDao dispatcherDao = CARDaoFactory
								.getDispatcherDao(getApplicationContext());
						dispatcherDao.open();

						DispatcherDTO dto = dispatcherDao.get(id);
						dispatcherDao.close();
						
						Intent i = new Intent(getBaseContext(), AddEditDispatcherActivity.class);
						i.putExtra("dispatcher", dto);
						startActivity(i);
					}
				});
			};
		};

		setListAdapter(cursorAdapter);

	}
}
