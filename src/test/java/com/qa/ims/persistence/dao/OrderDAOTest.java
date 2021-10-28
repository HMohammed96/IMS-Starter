package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO dao = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Long orderId = 1l;
		
		final Long customerId = 1l;
		final String customerFName = "jordan";
		final String customerLName = "harrison";
		final Customer customer = new Customer (customerId, customerFName, customerLName);
		
		final Long itemId = 1l;
		final String itemName = "Air Jordan 1";
		final Double price = 99d;
		final Item item = new Item (itemId, itemName, price);
		
		final Order created = new Order(orderId, customer, item);
		
		assertEquals(created, dao.create(created));
	}
	
	@Test
	public void testReadAll() {
		final Long orderId = 1l;
		
		final Long customerId = 1l;
		final String customerFName = "jordan";
		final String customerLName = "harrison";
		final Customer customer = new Customer (customerId, customerFName, customerLName);
		
		final Long itemId = 1l;
		final String itemName = "Air Jordan 1";
		final Double price = 99d;
		final Item item = new Item (itemId, itemName, price);
		
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(orderId, customer, item));
		assertEquals(expected, dao.readAll());
	}
	
	@Test
	public void testReadLatest() {
		final Long orderId = 1l;
		
		final Long customerId = 1l;
		final String customerFName = "jordan";
		final String customerLName = "harrison";
		final Customer customer = new Customer (customerId, customerFName, customerLName);
		
		final Long itemId = 1l;
		final String itemName = "Air Jordan 1";
		final Double price = 99d;
		final Item item = new Item (itemId, itemName, price);
		
		assertEquals(new Order(orderId, customer, item), dao.readLatest());
	}
	
	@Test
	public void testRead() {
		final Long orderId = 1l;
		
		final Long customerId = 1l;
		final String customerFName = "jordan";
		final String customerLName = "harrison";
		final Customer customer = new Customer (customerId, customerFName, customerLName);
		
		final Long itemId = 1l;
		final String itemName = "Air Jordan 1";
		final Double price = 99d;
		final Item item = new Item (itemId, itemName, price);
		
		assertEquals(new Order(orderId, customer, item), dao.read(orderId));
		
	}
	
	@Test
	public void testUpdate() {
		final Long orderId = 1l;
		
		final Long customerId = 1l;
		final String customerFName = "jordan";
		final String customerLName = "harrison";
		final Customer customer = new Customer (customerId, customerFName, customerLName);
		
		final Long itemId = 1l;
		final String itemName = "Air Jordan 1";
		final Double price = 99d;
		final Item item = new Item (itemId, itemName, price);
		
		final Order updated = new Order(orderId, customer, item);
		assertEquals(updated, dao.update(updated));
		
	}
	
	@Test
	public void testDelete() {
		assertEquals(1l, dao.delete(1l));
	}
}
