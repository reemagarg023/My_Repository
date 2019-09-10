package com.pizzaplace.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzaplace.service.PizzaService;
/**
 *  Front Controller for generating sorted ordered item list for Pizza Place
 *
 *
 */
@Controller
@Component
public class PizzaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);
	
	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/orders")
	@ResponseBody
	public String generateOutputFilefromURL(@RequestParam(name = "sourceFile", required = false, defaultValue = "\\") String sourceFile,
			@RequestParam(name = "destFile", required = false, defaultValue = "\\") String destFile) throws Exception {
		pizzaService.sortOrders(sourceFile, destFile);
		
		LOGGER.info("File created at location: "+destFile);
		LOGGER.info("PizzaController :: generateOutputFilefromURL : Started");
		return new String("File read from " + sourceFile + " and written to " + destFile);
		
	}
	
	public boolean generateOutputFileFromCmdLine(String sourceFile, String destinationFile) throws Exception {
		LOGGER.info("PizzaController :: generateOutputFileFromCmdLine : Started");
		LOGGER.info("Source: "+sourceFile+" Destination file: "+destinationFile);
		
		pizzaService.sortOrders(sourceFile, destinationFile);
		
		LOGGER.info("PizzaController :: generateOutputFileFromCmdLine : Ended");
		LOGGER.info("Exiting Controller :: Check the output file");
		return true;

	}
}
