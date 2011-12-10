package com.cornel.petrescu.rrr.car.persistence.dao;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.cornel.petrescu.rrr.car.persistence.CARDBHelper;

public class CARDaoFactory {
	private static Class<? extends SQLiteOpenHelper> clazz = CARDBHelper.class;

	public static DispatcherDao getDispatcherDao(Context context) {
		return new DispatcherDao(context, clazz);
	}
}
