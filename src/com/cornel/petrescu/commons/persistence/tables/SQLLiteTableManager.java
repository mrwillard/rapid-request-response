package com.cornel.petrescu.commons.persistence.tables;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public abstract class SQLLiteTableManager implements SQLLiteSchemaManager {

	public abstract String getPkColumnName();

	public abstract String getTableName(); 

	public abstract String[] getColumnNames();
	
	public abstract String getCreateSql();

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = getCreateSql();
		Log.w(this.getClass().getName(), "Creating table...");
		db.execSQL(sql);
		Log.w(this.getClass().getName(), "Table creation successfull");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(this.getClass().getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion);
	}
}
