package com.qa.ims.persistence.dao;

import org.junit.Before;

import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO dao = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

}
