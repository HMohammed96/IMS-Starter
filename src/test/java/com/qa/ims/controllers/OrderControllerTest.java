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
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
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
	
	@Mock
	private CustomerDAO customerDAO;
	@Mock
	private ItemDAO itemDAO;
	
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
		
		Mockito.when(utils.getLong()).thenReturn(orderId, customerId, itemId);
		Mockito.when(customerDAO.read(customerId)).thenReturn(customer);
		Mockito.when(itemDAO.read(itemId)).thenReturn(item);
		Mockito.when(dao.create(order)).thenReturn(order);
		
		assertEquals(order, controller.create());
		
		Mockito.verify(utils, Mockito.times(3)).getLong();
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
		
		final Order updated =  new Order(orderId, customer, item);
		
		Mockito.when(utils.getLong()).thenReturn(orderId, customerId, itemId);
		Mockito.when(customerDAO.read(customerId)).thenReturn(customer);
		Mockito.when(itemDAO.read(itemId)).thenReturn(item);
		Mockito.when(dao.update(updated)).thenReturn(updated);
		
		assertEquals(updated, controller.update());		
		
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(dao, Mockito.times(1)).update(updated);
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
