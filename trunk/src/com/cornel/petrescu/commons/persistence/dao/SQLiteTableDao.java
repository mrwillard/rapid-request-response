package com.cornel.petrescu.commons.persistence.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cornel.petrescu.commons.persistence.tables.SQLLiteTableManager;

public abstract class SQLiteTableDao<DTO> implements TableDAO<DTO> {
	protected Context context;
	protected SQLiteDatabase db;
	protected SQLiteOpenHelper dbHelper;
	protected Class<? extends SQLiteOpenHelper> openHelperClass;

	public SQLiteTableDao(Context context,
			Class<? extends SQLiteOpenHelper> openHelperClass) {
		this.context = context;
		this.openHelperClass = openHelperClass;
	}

	public SQLiteTableDao<DTO> open() throws SQLException {
		try {
			Constructor<? extends SQLiteOpenHelper> c = openHelperClass
					.getConstructor(Context.class);
			dbHelper = c.newInstance(context);
		} catch (SecurityException e) {
			Log.e(this.getClass().getName(), "", e);
		} catch (NoSuchMethodException e) {
			Log.e(this.getClass().getName(), "", e);
		} catch (IllegalArgumentException e) {
			Log.e(this.getClass().getName(), "", e);
		} catch (InstantiationException e) {
			Log.e(this.getClass().getName(), "", e);
		} catch (IllegalAccessException e) {
			Log.e(this.getClass().getName(), "", e);
		} catch (InvocationTargetException e) {
			Log.e(this.getClass().getName(), "", e);
		}

		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * * Create a new todo If the todo is successfully created return the new *
	 * rowId for that note, otherwise return a -1 to indicate failure.
	 */
	@Override
	public long create(DTO dto) {
		ContentValues values = createContentValues(dto);
		return db.insert(getTableName(), null, values);
	};

	@Override
	public boolean update(DTO dto) {
		ContentValues values = createContentValues(dto);

		return db.update(getTableName(), values, getPkColumnName() + "="
				+ getPkValue(dto), null) > 0;
	}

	@Override
	public boolean delete(long rowId) {
		return db.delete(getTableName(), getPkColumnName() + "=" + rowId, null) > 0;
	}

	@Override
	public Cursor list() {
		return db.query(getTableName(), getColumnNames(), null, null, null,
				null, null);
	}

	@Override
	public DTO get(long pk) {
		Cursor mCursor = db.query(true, getTableName(), getColumnNames(),
				getPkColumnName() + "=" + pk, null, null, null, null, null);
		if (mCursor.moveToFirst()) {
			DTO dto = createFromCursor(mCursor);
			mCursor.close();
			return dto;
		} else {
			mCursor.close();
			return null;
		}
		
	}

	protected abstract ContentValues createContentValues(DTO dto);

	protected abstract SQLLiteTableManager getTableManager();

	protected String getPkColumnName() {
		return getTableManager().getPkColumnName();
	}

	protected String getTableName() {
		return getTableManager().getTableName();
	}

	protected String[] getColumnNames() {
		return getTableManager().getColumnNames();
	}

	protected abstract long getPkValue(DTO dto);

	protected abstract DTO createFromCursor(Cursor cursor);
}
