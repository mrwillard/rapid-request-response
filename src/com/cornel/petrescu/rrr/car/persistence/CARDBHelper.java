package com.cornel.petrescu.rrr.car.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cornel.petrescu.rrr.cad.presistence.tables.WorkerTableManager;
import com.cornel.petrescu.rrr.car.persistence.tables.DispatcherTableManager;

public class CARDBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "CAR";
	public static final int DATABASE_VERSION = 1;

	public CARDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		new DispatcherTableManager().onCreate(db);
		String insertSql1 = "insert into dispatcher (name, phoneNumber) values('Test Dispatcher 1', '+40727120041');";
		String insertSql2 = "insert into dispatcher (name, phoneNumber) values('Test Dispatcher 2', '+40727120041');";
		String insertSql3 = "insert into dispatcher (name, phoneNumber) values('Test Dispatcher 3', '+40727120041');";
		db.execSQL(insertSql1);
		db.execSQL(insertSql2);
		db.execSQL(insertSql3);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		new WorkerTableManager().onUpgrade(db, oldVersion, newVersion);

	}

}
