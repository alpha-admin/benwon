package com.benwon.shop.bo;

import lombok.Data;

/**
 * 修正庫存數量
 * @author sam
 *
 */
@Data
public class FixProductItemStockQuantityEvent {
	private String productItemName;
	private Integer stockQuantity;
}
