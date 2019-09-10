package com.pizzaplace.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaplace.dao.OrdersDAO;
import com.pizzaplace.entity.OrderItem;
import com.pizzaplace.service.PizzaService;

/**
 * Implementation Class to to sort file data
 *
 *
 */

@Service
public class PizzaServiceImpl implements PizzaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaServiceImpl.class);

	@Autowired
	OrdersDAO ordersDAO;

	public boolean sortOrders(String sourceFile, String destinationFile) throws Exception {

		LOGGER.info("PizzaServiceImpl :: sortOrders : Started");
		boolean result = false;
		List<OrderItem> orderList = ordersDAO.readItems(sourceFile);

		LOGGER.info("**********Sorting started****************");

		if (orderList != null && !(orderList.isEmpty())) {
			LOGGER.info("Number of records to be sorted: " + orderList.size());
			Collections.sort(orderList);
			LOGGER.info("**********Sorting ended****************");

			result = ordersDAO.writeRecords(orderList, destinationFile);
		} else {
			LOGGER.error("OOOPS!! The input file is a bummer!!");
		}

		LOGGER.info("PizzaServiceImpl :: sortOrders : Ended");
		return result;
	}

}
