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

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private ItemDAO dao;
	
	@InjectMocks
	private ItemController controller;
	
	@Test
	public void testCreate() {
		final String itemName = "Air Jordan 1";
		final Double price = 99d;
		final Item item = new Item (itemName, price);
		
		Mockito.when(utils.getString()).thenReturn(itemName);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(dao.create(item)).thenReturn(item);
		
		assertEquals(item, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).create(item);	
	}
	
	@Test
	public void testReadAll() {
		List<Item> item = new ArrayList<>();
		item.add(new Item(1l, "Air Jordan 1", 99d));
		
		Mockito.when(dao.readAll()).thenReturn(item);
		
		assertEquals(item, controller.readAll());
		
		Mockito.verify(dao, Mockito.times(1)).readAll();
		
	}
	
	@Test
	public void testUpdate() {
		Item updated = new Item(1l, "Air Jordan 3", 109d);
		
		Mockito.when(this.utils.getLong()).thenReturn(1l);
		Mockito.when(this.utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(this.utils.getDouble()).thenReturn(updated.getPrice());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);
		
		assertEquals(updated, this.controller.update());
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);	
	}
	
	@Test
	public void testDelete() {
		final long itemId = 1l;

		Mockito.when(utils.getLong()).thenReturn(itemId);
		Mockito.when(dao.delete(itemId)).thenReturn(1);

		assertEquals(1l, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(itemId);
	}
}
