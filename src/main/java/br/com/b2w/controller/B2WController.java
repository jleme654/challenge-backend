package br.com.b2w.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.b2w.consumer.EndpointConsumer;
import br.com.b2w.utils.HelperUtils;
import br.com.b2w.vo.ItemVO;

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
	public ResponseEntity<List<ItemVO>> getItemsByRangeDatas(@RequestParam("begindate") String begindate,
			                       @RequestParam("finaldate") String finaldate) {
		logger.info("--- B2W SOLUCAO CHALLENGE BACKEND ---");
		
		String jsonString = this.serviceApp.getItemsByEndpoint();
		
		Gson gson = new Gson();
		ItemVO[] itemsoriginal = gson.fromJson(jsonString, ItemVO[].class);
		List<ItemVO> listaItemsOriginals = Arrays.asList(itemsoriginal);
		List<ItemVO> listaitemnovo = new ArrayList<>();
		//logger.info("--- LISTA ITEMS ORIGINAL: " + listaItemsOriginals);
		
		Date databegin = HelperUtils.convertStringtoDate(begindate);
		Date datafinal = HelperUtils.convertStringtoDate(finaldate);
		
		//logger.info("--- datas: databegin: "+databegin + " datafinal: " + datafinal );
		for (ItemVO item : listaItemsOriginals) {
			String dataitem = item.getDate();
			//logger.info("--- datas: dataitem: " + dataitem);
			dataitem = HelperUtils.treatmentDate(dataitem);
			Date dataitemconvert = HelperUtils.convertStringtoDate(dataitem);
			if(dataitemconvert.after(databegin) && dataitemconvert.before(datafinal))
				listaitemnovo.add(item);
		}
		//logger.info("--- lista result: " + listaitemnovo.toString());
		HttpStatus status = HttpStatus.ACCEPTED;
		return new ResponseEntity<List<ItemVO>>(listaitemnovo, status);
	}
	
}
