package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private ItemDAO itemsDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}

	@Override
	public List<Item> readAll() {
		List<Item> items = itemsDAO.readAll();
		for (Item item : items ) {
			LOGGER.info(item);
		}
		return items;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter an item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item price");
		Double price = utils.getDouble();
		Item item = itemsDAO.create(new Item(itemName, price));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the item id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter an item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item price");
		Double price = utils.getDouble();
		Item item = itemsDAO.update(new Item(id, itemName, price));
		LOGGER.info("Item updated");
		return item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the item id of the id you would like to delete");
		Long id = utils.getLong();
		return itemsDAO.delete(id);
	}	

}
