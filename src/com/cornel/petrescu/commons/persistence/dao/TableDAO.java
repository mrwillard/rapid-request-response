package com.cornel.petrescu.commons.persistence.dao;

import android.database.Cursor;

public interface TableDAO<DTO> {
	
	public long create(DTO dto);

	public boolean update(DTO dto);

	public boolean delete(long pk);

	public Cursor list();

	//public Cursor listFiltered(DTO dto);

	public DTO get(long pk);
}
