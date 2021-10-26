package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order>{
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("order_id");
		Date orderDate = resultSet.getDate("order_date");
		
		Long customerId = resultSet.getLong("id");
		String customerFName = resultSet.getString("first_name");
		String customerLName = resultSet.getString("surname");
		Customer customer = new Customer(customerId, customerFName, customerLName);
		
		Long itemId =  resultSet.getLong("item_id");
		String itemName = resultSet.getString("item_name");
		Double price = resultSet.getDouble("price");
		Item item = new Item(itemId, itemName, price);
		
		return new Order(orderId, orderDate, customer, item);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("Select * FROM orders");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));	
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return new ArrayList<>();
	
	}
	
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order read(Long OrderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");) {
			statement.setLong(1, OrderId);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders (order_date, fk_id, fk_item_id) VALUES (?, ?, ?)");) {
			statement.setDate(1, t.getOrderDate());
			statement.setLong(2, t.getCustomer().getId());
			statement.setLong(3, t.getItem().getId());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET order_date = ?, fk_id = ?, fk_item_id WHERE id = ?");) {
			statement.setLong(1, t.getOrderId());
			statement.setDate(2, t.getOrderDate());
			statement.setDouble(3, t.getCustomer().getId());
			statement.setDouble(3, t.getItem().getId());
			statement.executeUpdate();
			return read(t.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}		
		return null;
	}

	@Override
	public int delete(long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, orderId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	// this is a method that will delete an item in an order
	public Order deleteItemFromOrder (Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("Delete FROM orders WHERE fk_item_id = ? and order_id = ?");) {
			statement.setDouble(1, t.getItem().getId());
			statement.setLong(2, t.getOrderId());
			statement.executeUpdate();
			return read(t.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}		
		return null;
	}
	
	// this is a method that will add an item to an order
	public Order addItemToOrder (Order t) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders (fk_item_id) VALUES (?) WHERE order_id = ?");) {
			statement.setDouble(1, t.getOrderId());
			statement.setDouble(2, t.getItem().getId());
			statement.executeUpdate();
			return read(t.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}		
		return null;
	}
	
	public Order orderCost (Order t) {
		
		Long orderId;
		Double price;
		int quantity;
		int totalCost;
		
		return null;
	}
}
