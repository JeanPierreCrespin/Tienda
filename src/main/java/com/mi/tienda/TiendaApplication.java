package com.mi.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
		try {
			MercadoPago.SDK.setAccessToken("APP_USR-4715757626164033-080123-a8743bc5b5921df98b051e7860a9aa6e-800504782");
			MercadoPago.SDK.setIntegratorId("dev_24c65fb163bf11ea96500242ac130004");
		} catch (MPConfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error MP: "+e.getMessage());
		}
	}
	

}
