package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;
	private ItemDAO itemDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, CustomerDAO customerDAO, ItemDAO itemDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.customerDAO = customerDAO;
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
	

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders ) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Order create() {
		
		LOGGER.info("Please enter an order id");
		Long orderId = utils.getLong();
		
		LOGGER.info("Please enter an id");
		Long customerId = utils.getLong();
		Customer customer = this.customerDAO.read(customerId);
		
		LOGGER.info("Please enter an item id");
		Long itemId = utils.getLong();
		Item item = this.itemDAO.read(itemId);

		Order order = orderDAO.create(new Order(orderId, customer, item));
		LOGGER.info("Order created");
		return order;
	}



	@Override
	public Order update() {
		LOGGER.info("Please enter the order id of the order you would like to update");
		Long orderId = utils.getLong();
		
		LOGGER.info("Please enter an id");
		Long customerId = utils.getLong();
		Customer customer = this.customerDAO.read(customerId);
		
		LOGGER.info("Please enter an item id");
		Long itemId = utils.getLong();
		Item item = this.itemDAO.read(itemId);
		
		Order order = orderDAO.update(new Order(orderId, customer, item));
		LOGGER.info("Order updated");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the order id of the order you would like to delete");
		Long orderId = utils.getLong();
		LOGGER.info("Order deleted");
		return orderDAO.delete(orderId);
	}
	
	public Order deleteItemFromOrder() {
		LOGGER.info("Please enter the order id of the order you would like to delete the item from");
		Long orderId = utils.getLong();
		
		LOGGER.info("Please enter the item id of the item you would like to delete from the order");
		Long itemId = utils.getLong();
		String itemName = utils.getString();
		Double price = utils.getDouble();
		Item item = new Item(itemId, itemName, price);
		
		Order order = orderDAO.deleteItemFromOrder(new Order(orderId, item));
		LOGGER.info("Item deleted from the order");
		
		return order;
	}
	
	public Order addItemToOrder() {
		LOGGER.info("Please enter the order id of the order you would like to add the item to");
		Long orderId = utils.getLong();
		
		LOGGER.info("Please enter the item id of the item you would like to add to the order");
		Long itemId = utils.getLong();
		String itemName = utils.getString();
		Double price = utils.getDouble();
		Item item = new Item(itemId, itemName, price);
		
		LOGGER.info("Please enter the item quantity");
		int quantity = utils.getInt();
		
		LOGGER.info("Please enter item total cost");
		Double totalCost = utils.getDouble();
		
		Order order = orderDAO.addItemToOrder(new Order(orderId, item, quantity, totalCost));
		LOGGER.info("Item added to the order");
		
		return order;
	}
	
	
	
	

}
