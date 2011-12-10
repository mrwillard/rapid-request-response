package com.cornel.petrescu.rrr.car.persistence.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.cornel.petrescu.commons.persistence.dao.SQLiteTableDao;
import com.cornel.petrescu.commons.persistence.tables.SQLLiteTableManager;
import com.cornel.petrescu.rrr.car.persistence.dto.DispatcherDTO;
import com.cornel.petrescu.rrr.car.persistence.tables.DispatcherTableManager;

public class DispatcherDao extends SQLiteTableDao<DispatcherDTO> {

	public DispatcherDao(Context context,
			Class<? extends SQLiteOpenHelper> clazz) {
		super(context, clazz);
	}

	@Override
	protected ContentValues createContentValues(DispatcherDTO dto) {
		ContentValues cv = new ContentValues();
		cv.put(DispatcherTableManager.PK_NAME, dto.getId());
		cv.put(DispatcherTableManager.KEY_NAME, dto.getNume());
		cv.put(DispatcherTableManager.KEY_PHONE, dto.getTelefon());

		return cv;
	}

	@Override
	protected long getPkValue(DispatcherDTO dto) {
		return dto.getId();
	}

	public DispatcherDTO getByPhoneNumber(String phoneNumber) {

		String query = "Select _id, name, phoneNumber from dispatcher Where(phoneNumber = '%s')";
		Cursor mCursor = db.rawQuery(String.format(query, phoneNumber), null);
		// Cursor mCursor = db.query(true, getTableName(), getColumnNames(),
		// DispatcherTableManager.KEY_PHONE + "=" + phoneNumber, null,
		// null, null, null, null);
		if (mCursor != null) {
			if (mCursor.moveToFirst()) {
				DispatcherDTO dto = createFromCursor(mCursor);
				mCursor.close();
				return dto;
			} else {
				mCursor.close();
				return null;
			}

		} else {
			return null;
		}
	}

	@Override
	protected DispatcherDTO createFromCursor(Cursor cursor) {
		DispatcherDTO dto = new DispatcherDTO();
		dto.setId(cursor.getLong(cursor
				.getColumnIndex(DispatcherTableManager.PK_NAME)));

		dto.setNume(cursor.getString(cursor
				.getColumnIndex(DispatcherTableManager.KEY_NAME)));

		dto.setTelefon(cursor.getString(cursor
				.getColumnIndex(DispatcherTableManager.KEY_PHONE)));

		return dto;
	}

	@Override
	protected SQLLiteTableManager getTableManager() {
		return new DispatcherTableManager();
	}

}
