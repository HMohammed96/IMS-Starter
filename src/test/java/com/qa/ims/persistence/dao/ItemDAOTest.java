package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;


public class ItemDAOTest {
	
	private final ItemDAO dao = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(2l, "Air Jordan 3", 99d);
		assertEquals(created, dao.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1l, "Air Jordan 1", 99d));
		assertEquals(expected, dao.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Item(1l, "Air Jordan 1", 99d), dao.readLatest());
	}
	
	@Test
	public void testRead() {
		final long itemId = 1l;
		assertEquals(new Item(itemId, "Air Jordan 1", 99d), dao.read(itemId));
	}
	
	@Test
	public void testUpdate() {
		final Item updated =  new Item(1l, "Air Jordan 1", 99d);
		assertEquals(updated, dao.update(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, dao.delete(1));
	}

}
