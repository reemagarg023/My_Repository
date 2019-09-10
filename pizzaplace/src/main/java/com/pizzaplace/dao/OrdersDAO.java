package com.pizzaplace.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pizzaplace.entity.OrderItem;

public interface OrdersDAO {

	public ArrayList<OrderItem> readItems(String filePath) throws IOException;
	
	public boolean writeRecords(List<OrderItem> sortedOrderList, String destinationFile) throws IOException;


}
