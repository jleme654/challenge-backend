package br.com.b2w.consumer;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.vo.ItemsVO;
/**
 * 
 * @author julio
 * @since 2019-03-26
 * @version 1.0.0
 * 
 */
@Component
public class EndpointConsumer {

	public ItemsVO getItemsByEndpoint() {
		RestTemplate template = new RestTemplate();
		return template.getForObject("http://www.mocky.io/v2/5817803a1000007d01cc7fc9", ItemsVO.class);
	}

}
