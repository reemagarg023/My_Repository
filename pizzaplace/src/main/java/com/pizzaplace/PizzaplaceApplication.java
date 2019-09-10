package com.pizzaplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pizzaplace.controller.PizzaController;

@SpringBootApplication
public class PizzaplaceApplication implements CommandLineRunner{

	@Autowired
	PizzaController controller;
	
	public static void main(String[] args)  {
		SpringApplication.run(PizzaplaceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello, Welcome to the PizzaPlace!!!!");
		String sourceFile = null;
		String destFile = null;
		if (args.length > 0) {
			if (args.length >= 1) {
				sourceFile = args[0];
			}
			if (args.length > 1) {
				destFile = args[1];
			}
			controller.generateOutputFileFromCmdLine(sourceFile, destFile);
		}
	}
}
