package edu.mum.cs425.onlineshopping.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@DynamicUpdate
@Table(name = "product_info")
public class ProductInfo{
	
	
	
    @Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", productName=" + productName + "]";
	}



	/**
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Id
	@Length(max = 7)
    private String productId;

    @NotNull
    private String productName;

   
    @NotNull
    private BigDecimal productPrice;

  
    @NotNull
    @Min(0)
    private Integer productStock;

   
    private String productDescription;

   
    private String productIcon;

   

  

 
   
    private String categoryType;
   
    
    @Column(name = "DateMfd", nullable = true)
    @NotNull(message = "*Date Manufactured is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateMfd;
   

   
	public ProductInfo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public BigDecimal getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}



	public Integer getProductStock() {
		return productStock;
	}



	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}



	public String getProductDescription() {
		return productDescription;
	}



	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}



	public String getProductIcon() {
		return productIcon;
	}



	public void setProductIcon(String productIcon) {
		this.productIcon = productIcon;
	}



	



	public String getCategoryType() {
		return categoryType;
	}



	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}



	public LocalDate getDateMfd() {
		return dateMfd;
	}



	public void setDateMfd(LocalDate dateMfd) {
		this.dateMfd = dateMfd;
	}





	




	
}
