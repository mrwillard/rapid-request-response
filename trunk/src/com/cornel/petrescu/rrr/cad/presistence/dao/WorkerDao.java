package com.cornel.petrescu.rrr.cad.presistence.dao;

import com.cornel.petrescu.commons.persistence.dao.SQLiteTableDao;
import com.cornel.petrescu.commons.persistence.tables.SQLLiteTableManager;
import com.cornel.petrescu.rrr.cad.presistence.dto.WorkerDTO;
import com.cornel.petrescu.rrr.cad.presistence.tables.WorkerTableManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkerDao extends SQLiteTableDao<WorkerDTO> {

	public WorkerDao(Context context,
			Class<? extends SQLiteOpenHelper> clazz) {
		super(context, clazz);
	}

	@Override
	protected ContentValues createContentValues(WorkerDTO dto) {
		ContentValues cv = new ContentValues();
		cv.put(WorkerTableManager.PK_NAME, dto.getId());
		cv.put(WorkerTableManager.KEY_NAME, dto.getNume());
		cv.put(WorkerTableManager.KEY_PHONE, dto.getTelefon());

		return cv;
	}

	@Override
	protected long getPkValue(WorkerDTO dto) {
		return dto.getId();
	}

	@Override
	protected WorkerDTO createFromCursor(Cursor cursor) {
		WorkerDTO dto = new WorkerDTO();
		dto.setId(cursor.getLong(cursor
				.getColumnIndex(WorkerTableManager.PK_NAME)));

		dto.setNume(cursor.getString(cursor
				.getColumnIndex(WorkerTableManager.KEY_NAME)));

		dto.setTelefon(cursor.getString(cursor
				.getColumnIndex(WorkerTableManager.KEY_PHONE)));

		return dto;
	}

	@Override
	protected SQLLiteTableManager getTableManager() {
		return new WorkerTableManager();
	}

}
