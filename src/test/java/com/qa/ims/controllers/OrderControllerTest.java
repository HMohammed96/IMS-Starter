package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private OrderDAO dao;
	
	@InjectMocks
	private OrderController controller;
	
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
		
		final Order order = new Order (orderId, customer, item);
		
		Mockito.when(utils.getLong()).thenReturn(orderId);
		Mockito.when(utils.getLong()).thenReturn(customerId);
		Mockito.when(utils.getLong()).thenReturn(itemId);
		
		assertEquals(order, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(order);
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
		
		List<Order> order = new ArrayList<>();
		order.add(new Order(orderId, customer, item));
		
		Mockito.when(dao.readAll()).thenReturn(order);
		
		assertEquals(order, controller.readAll());
		
		Mockito.verify(dao, Mockito.times(1)).readAll();
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
		
		Order updated =  new Order(orderId, customer, item);
		
		Mockito.when(this.utils.getLong()).thenReturn(orderId);
		Mockito.when(this.utils.getLong()).thenReturn(updated.getCustomerId());
		Mockito.when(this.utils.getString()).thenReturn(customerFName);
		Mockito.when(this.utils.getString()).thenReturn(customerLName);
		Mockito.when(this.utils.getLong()).thenReturn(itemId);
		Mockito.when(this.utils.getString()).thenReturn(itemName);
		Mockito.when(this.utils.getDouble()).thenReturn(price);
		
		Mockito.when(this.dao.update(updated)).thenReturn(updated);
		
		assertEquals(updated, this.controller.update());		
	}
	
	@Test
	public void testDelete() {
		final long orderId = 1l;

		Mockito.when(utils.getLong()).thenReturn(orderId);
		Mockito.when(dao.delete(orderId)).thenReturn(1);

		assertEquals(1l, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(orderId);
	}
	
	@Test
	public void testDeleteItem() {
		
	}
	
	@Test
	public void testAddItem() {
		
	}
}
