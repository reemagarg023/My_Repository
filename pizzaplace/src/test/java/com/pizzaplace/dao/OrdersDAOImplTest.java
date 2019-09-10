package com.pizzaplace.dao;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pizzaplace.dao.impl.OrdersDAOImpl;
import com.pizzaplace.entity.OrderItem;
import com.pizzaplace.service.impl.PizzaServiceImpl;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersDAOImplTest extends TestCase{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaServiceImpl.class);
	private OrdersDAO ordersDAO;
	private String sourceFile = "sample_data_ordered.txt";
	private String testDestFile = "test_data_ordered.csv";

	@Before
	public void setup() {
		ordersDAO = new OrdersDAOImpl();
	}

	@Test
	public void testReadItems() {
		setup();
		try {
			URL url = new OrdersDAOImplTest().getClass().getClassLoader().getResource(sourceFile);
			String filepath = url.getFile();
			List<OrderItem> orderList = ordersDAO.readItems(filepath);
			LOGGER.info("result ::" + orderList.size());
			assertTrue(orderList.size() >0);
			assertEquals("Incorrect Size Returned",9,orderList.size());

		} catch (Exception e) {
			LOGGER.error("Error in readItems:: " + e.getMessage());
			e.printStackTrace();
			fail("Test method failed with exceptions");
		}

	}
	
	@Test
	public void testWriteRecords() {
		setup();
		try {
			URL url = new OrdersDAOImplTest().getClass().getClassLoader().getResource(sourceFile);
			String filepath = url.getFile();
			List<OrderItem> orderList = ordersDAO.readItems(filepath);
			URL destFileUrl = new OrdersDAOImplTest().getClass().getClassLoader().getResource(testDestFile);
			String destFile = destFileUrl.getFile();
			boolean testWrite = ordersDAO.writeRecords(orderList, destFile);
			assertTrue(testWrite);
		} catch (Exception e) {
			LOGGER.error("Error in writeRecords():: " + e.getMessage());
			e.printStackTrace();
		}

	}
}