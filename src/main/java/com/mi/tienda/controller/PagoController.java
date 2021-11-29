package com.mi.tienda.controller;



import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Address;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.PaymentMethods;
import com.mercadopago.resources.datastructures.preference.Phone;
import com.mi.tienda.model.ProductoModel;

@RestController
@RequestMapping("/pago")
public class PagoController {
    @PostMapping("/create_preference")
	public Preference pago(@RequestBody ProductoModel pM) throws MPException{

    	Preference preference = new Preference();

    	Item item = new Item();
    	item
    	    .setId("123")
    	    .setTitle(pM.getP_name())
    	    .setPictureUrl(pM.getImg_url())
    	    .setDescription(pM.getDescription())
    	    .setQuantity(pM.getQuantity())
    	    .setUnitPrice((float) pM.getPrice());
    	preference.appendItem(item);
  
    	preference.setExternalReference("crespinpierre98@gmail.com");
    	
    	Payer payer = new Payer();
    	payer.setName("Lalo")
    	     .setSurname("Landa")
    	     .setEmail("test_user_63274575@testuser.com")
    	     .setPhone(new Phone()
    	        .setAreaCode("11")
    	        .setNumber("22223333"))
    	     .setAddress(new Address()
    	    	        .setStreetName("Cuesta Miguel Armendáriz")
    	    	        .setStreetNumber(123)
    	    	        .setZipCode("1111"));
    	
    	preference.setPayer(payer);
    	
    	PaymentMethods paymentMethods = new PaymentMethods();
    	paymentMethods.setExcludedPaymentMethods("amex");
    	paymentMethods.setExcludedPaymentTypes("atm");
    	paymentMethods.setInstallments(6);

    	preference.setPaymentMethods(paymentMethods);
    	
    	BackUrls backUrls = new BackUrls(
                "https://tienda-de-celulares.herokuapp.com/pago/success",
                "https://tienda-de-celulares.herokuapp.com/pago/pending",
                "https://tienda-de-celulares.herokuapp.com/pago/failure");
       
       preference.setBackUrls(backUrls);
       preference.setNotificationUrl("https://tienda-de-celulares.herokuapp.com/pago/notification");
       preference.getAutoReturn();
      
       Preference resulset = preference.save();
 
		return resulset;
	}
    @PostMapping("/notification")
    public ResponseEntity<String> noficaciones(@RequestParam String data_id, @RequestParam String type) {

    	System.out.println("DATA_ID: "+data_id);
        System.out.println("TYPE: "+type);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    
    @GetMapping("/pending")
    public ModelAndView pending(@RequestParam String collection_id,
    		@RequestParam String collection_status, 
    		@RequestParam String payment_id, 
    		@RequestParam String status, 
    		@RequestParam String external_reference, 
    		@RequestParam String payment_type, 
    		@RequestParam String merchant_order_id, 
    		@RequestParam String preference_id,
    		@RequestParam String site_id, 
    		@RequestParam String processing_mode,
    		@RequestParam String merchant_account_id) {
    	ModelAndView mav = new ModelAndView("respuesta");
    	mav.addObject("estado", "PENDING");
    	mav.addObject("collection_id", collection_id);
    	mav.addObject("collection_status", collection_status);
    	mav.addObject("payment_id", payment_id);
    	mav.addObject("status", status);
    	mav.addObject("external_reference", external_reference);
    	mav.addObject("payment_type", payment_type);
    	mav.addObject("merchant_order_id", merchant_order_id);
    	mav.addObject("preference_id", preference_id);
    	mav.addObject("site_id", site_id);
    	mav.addObject("processing_mode", processing_mode);
    	mav.addObject("merchant_account_id", merchant_account_id);
    	return mav;
    }
    @GetMapping("/failure")
    public ModelAndView failure(@RequestParam String collection_id,
    		@RequestParam String collection_status, 
    		@RequestParam String payment_id, 
    		@RequestParam String status, 
    		@RequestParam String external_reference, 
    		@RequestParam String payment_type, 
    		@RequestParam String merchant_order_id, 
    		@RequestParam String preference_id,
    		@RequestParam String site_id, 
    		@RequestParam String processing_mode,
    		@RequestParam String merchant_account_id) {
    	ModelAndView mav = new ModelAndView("respuesta");
    	mav.addObject("estado", "FAILURE");
    	mav.addObject("collection_id", collection_id);
    	mav.addObject("collection_status", collection_status);
    	mav.addObject("payment_id", payment_id);
    	mav.addObject("status", status);
    	mav.addObject("external_reference", external_reference);
    	mav.addObject("payment_type", payment_type);
    	mav.addObject("merchant_order_id", merchant_order_id);
    	mav.addObject("preference_id", preference_id);
    	mav.addObject("site_id", site_id);
    	mav.addObject("processing_mode", processing_mode);
    	mav.addObject("merchant_account_id", merchant_account_id);
    	return mav;
    }
    
    @GetMapping("/success")
    public ModelAndView success(@RequestParam String collection_id,
    		@RequestParam String collection_status, 
    		@RequestParam String payment_id, 
    		@RequestParam String status, 
    		@RequestParam String external_reference, 
    		@RequestParam String payment_type, 
    		@RequestParam String merchant_order_id, 
    		@RequestParam String preference_id,
    		@RequestParam String site_id, 
    		@RequestParam String processing_mode,
    		@RequestParam String merchant_account_id) {
    	
    	ModelAndView mav = new ModelAndView("respuesta");
    	mav.addObject("estado", "SUCCESSS");
    	mav.addObject("collection_id", collection_id);
    	mav.addObject("collection_status", collection_status);
    	mav.addObject("payment_id", payment_id);
    	mav.addObject("status", status);
    	mav.addObject("external_reference", external_reference);
    	mav.addObject("payment_type", payment_type);
    	mav.addObject("merchant_order_id", merchant_order_id);
    	mav.addObject("preference_id", preference_id);
    	mav.addObject("site_id", site_id);
    	mav.addObject("processing_mode", processing_mode);
    	mav.addObject("merchant_account_id", merchant_account_id);
    	return mav;
    }
}
