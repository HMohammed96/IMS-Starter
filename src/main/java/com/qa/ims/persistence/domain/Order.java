package com.qa.ims.persistence.domain;

public class Order {
	
	private Long orderId;
	private Customer customer;
	private Item item;
	private int quantity;
	private Double totalCost;
	
	
	public Order(Long orderId, Customer customer, Item item, int quantity, Double totalCost) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.item = item;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}
	
	public Order(Long orderId, Item item, int quantity, Double totalCost) {
		super();
		this.orderId = orderId;
		this.item = item;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}

	public Order(Long orderId, Customer customer, Item item) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.item = item;
	}

	public Order(Long orderId, Item item) {
		super();
		this.orderId = orderId;
		this.item = item;
	}

	public Order(Long orderId, Customer customer) {
		super();
		this.orderId = orderId;
		this.customer = customer;
	}

	public Order(Customer customer, Item item) {
		super();
		this.customer = customer;
		this.item = item;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", item=" + item + ", quantity=" + quantity
				+ ", totalCost=" + totalCost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (quantity != other.quantity)
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}
	
}
