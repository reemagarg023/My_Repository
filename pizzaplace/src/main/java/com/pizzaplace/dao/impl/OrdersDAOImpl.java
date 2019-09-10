package com.pizzaplace.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pizzaplace.dao.OrdersDAO;
import com.pizzaplace.entity.OrderItem;

/**
 *  Implementation Class to read input data and write to output file
 *
 *
 */
@Repository
public class OrdersDAOImpl implements OrdersDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdersDAOImpl.class);

	@Override
	public ArrayList<OrderItem> readItems(String filePath) throws IOException {
		List<OrderItem> orders = new ArrayList<OrderItem>();
		LOGGER.info("**********Starting to read file****************");

		File file = new File(filePath);
		FileInputStream fileStream = new FileInputStream(file);
		InputStreamReader input = new InputStreamReader(fileStream);
		BufferedReader reader = new BufferedReader(input);
		String line;

		while ((line = reader.readLine()) != null) {
			if (line.contains("time")) {
				continue;
			}
			String[] wordList = line.split("\\s+");
			String item = wordList[0];
			String time = wordList[1];
			OrderItem orderItem = new OrderItem(item, time);
			orders.add(orderItem);
		}
		reader.close();
		LOGGER.info("**********Ending to read file****************");
		return (ArrayList<OrderItem>) orders;
	}
	
	
	public boolean writeRecords(List<OrderItem> sortedOrderList, String destinationFile) throws IOException{
		LOGGER.info("**********Starting to write file****************");
		LOGGER.info("Number of records written in output file "+sortedOrderList.size());

		String CSV_SEPARATOR = " , ";
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destinationFile), "UTF-8"));
		StringBuffer oneLine = new StringBuffer();
		oneLine.append("Order Name"+CSV_SEPARATOR+ "Order Date");
		bw.write(oneLine.toString());
		bw.newLine();
        for (OrderItem orderItem : sortedOrderList)
        {
            oneLine = new StringBuffer();
            oneLine.append(orderItem.getItemName() == null ? "" : orderItem.getItemName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(orderItem.getOrderDate() == null ? "" : orderItem.getOrderDate());
            oneLine.append(CSV_SEPARATOR);

            
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
		LOGGER.info("**********Ending to write file****************");
		return true;
	}
	

}
