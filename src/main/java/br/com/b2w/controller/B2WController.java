package br.com.b2w.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.b2w.consumer.EndpointConsumer;
import br.com.b2w.utils.HelperUtils;
import br.com.b2w.vo.ItemVO;
import br.com.b2w.vo.ItemsVO;

/**
 * 
 * @author julio
 * @since 2019-03-26
 * @version 1.0.0
 * 
 */
@RestController
public class B2WController {

	private static final Logger logger = Logger.getLogger(B2WController.class.getName());
	
	@Autowired
	EndpointConsumer serviceApp;
	
	@RequestMapping("/challenge-backend")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/challenge-backend/item")
	public String getItemsByRangeDatas(@RequestParam("begindate") String begindate,
			                       @RequestParam("finaldate") String finaldate) {
		logger.info("--- solucao challenge-bakend ---");
		
		ItemsVO itemsoriginal = this.serviceApp.getItemsByEndpoint();
		ItemsVO itemsnovo = new ItemsVO();
		ArrayList<ItemVO> listaitemnovo = new ArrayList<>();
		
		Date databegin = HelperUtils.convertStringtoDate(begindate);
		Date datafinal = HelperUtils.convertStringtoDate(finaldate);
		
		for (ItemVO item : itemsoriginal.getItems()) {
			String dataitem = item.getDate();
			dataitem = HelperUtils.treatmentDate(dataitem);
			Date dataitemconvert = HelperUtils.convertStringtoDate(dataitem);
			if(dataitemconvert.after(databegin) && dataitemconvert.before(datafinal))
				listaitemnovo.add(item);
		}
		itemsnovo.setItems(listaitemnovo);
		
		Gson g = new Gson(); 
		String itemsnovojson = g.toJson(ItemsVO.class);
		return itemsnovojson;
	}
	
}
