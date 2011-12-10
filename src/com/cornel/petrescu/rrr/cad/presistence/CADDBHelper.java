package com.cornel.petrescu.rrr.cad.presistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cornel.petrescu.rrr.cad.presistence.tables.WorkerTableManager;

public class CADDBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "CAD";
	public static final int DATABASE_VERSION = 1;

	public CADDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		new WorkerTableManager().onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		new WorkerTableManager().onUpgrade(db, oldVersion, newVersion);

	}

}
