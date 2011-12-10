package com.cornel.petrescu.rrr.cad.presistence.tables;

import com.cornel.petrescu.commons.persistence.tables.SQLLiteTableManager;

public class WorkerTableManager extends SQLLiteTableManager {

	public static final String TABLE_NAME = "worker";
	public static final String PK_NAME = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_PHONE = "phoneNumber";
	
	private static final String[] COLUMNS = new String[] { PK_NAME, KEY_NAME,
			KEY_PHONE };

	private static final String CREATE_SQL = "create table worker "
			+ "(_id integer primary key autoincrement, "
			+ "name text not null, phoneNumber text not null);";

	@Override
	public String getCreateSql() {
		return WorkerTableManager.CREATE_SQL;
	}

	@Override
	public String getPkColumnName() {
		return PK_NAME;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String[] getColumnNames() {
		return COLUMNS;
	}

}
