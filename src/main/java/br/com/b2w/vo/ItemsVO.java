package br.com.b2w.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author julio
 * @since 2019-03-26
 * @version 1.0.0
 * 
 */
public class ItemsVO {

	@JsonProperty("") 
	private ItemVO[] items;

	public ItemVO[] getItems() {
		return items;
	}

	public void setItems(ItemVO[] items) {
		this.items = items;
	}

}
