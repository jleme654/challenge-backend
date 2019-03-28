package br.com.b2w.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.vo.ItemsVO;
/**
 * 
 * @author julio
 * @since 2019-03-26
 * @version 1.0.0
 * 
 */
@RestController
public class Controller {

	private static final Logger LOG = Logger.getLogger(Controller.class.getName());
	
	private static ItemsVO items;

	@RequestMapping("/challenge-backend")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
