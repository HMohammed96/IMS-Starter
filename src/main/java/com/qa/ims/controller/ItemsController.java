package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

public class ItemsController implements CrudController<Items> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private ItemsDAO itemsDAO;
	private Utils utils;
	
	public ItemsController(ItemsDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}

	@Override
	public List<Items> readAll() {
		List<Items> items = itemsDAO.readAll();
		for (Items item : items ) {
			LOGGER.info(item);
		}
		return items;
	}

	@Override
	public Items create() {
		LOGGER.info("Please enter an item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item price");
		Double price = utils.getDouble();
		Items item = itemsDAO.create(new Items(itemName, price));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public Items update() {
		LOGGER.info("Please enter the item id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter an item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item price");
		Double price = utils.getDouble();
		Items item = itemsDAO.create(new Items(id, itemName, price));
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
