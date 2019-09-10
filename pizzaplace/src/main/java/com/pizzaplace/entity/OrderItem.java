package com.pizzaplace.entity;

import java.util.Date;
/**
 *  Entity Class for ordered items
 *
 *
 */
public class OrderItem implements Comparable<OrderItem> {
	private String itemName;
	private String itemEpochTime;
	private Date orderDate;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemEpochTime() {
		return itemEpochTime;
	}

	public void setItemEpochTime(String itemEpochTime) {
		this.itemEpochTime = itemEpochTime;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	@Override
	public String toString() {
		return "OrderItem [itemName=" + itemName + ", itemEpochTime=" + itemEpochTime + ", orderDate=" + orderDate
				+ "]";
	}

	public OrderItem(String itemName, String itemEpochTime) {
		this.itemName = itemName;
		this.itemEpochTime = itemEpochTime;
		this.orderDate = new Date(new Long(itemEpochTime) * 1000);
	}

	@Override
	public int compareTo(OrderItem o) {
		return getItemName().compareTo(o.getItemName());
	}

}
