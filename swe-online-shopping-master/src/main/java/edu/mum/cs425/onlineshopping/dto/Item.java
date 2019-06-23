package edu.mum.cs425.onlineshopping.dto;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import edu.mum.cs425.onlineshopping.entity.ProductInfo;





public class Item {
	
	
    private ProductInfo productInfo;

    private Integer quantity;

    @Override
	public String toString() {
		return "Item [productInfo=" + productInfo + ", quantity=" + quantity + "]";
	}

	public Item(ProductInfo productInfo, Integer quantity) {
        this.productInfo = productInfo;
        this.quantity = quantity;
    }

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
}
