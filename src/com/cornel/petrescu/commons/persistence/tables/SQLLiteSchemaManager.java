package com.cornel.petrescu.commons.persistence.tables;

import android.database.sqlite.SQLiteDatabase;

public interface SQLLiteSchemaManager {

	public void onCreate(SQLiteDatabase db);

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
