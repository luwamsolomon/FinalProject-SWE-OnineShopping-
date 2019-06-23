package edu.mum.cs425.onlineshopping.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import edu.mum.cs425.onlineshopping.entity.ProductInfo;


public interface ProductService {
	
	Page<ProductInfo> findPaginated(List<ProductInfo> list,Pageable pageable);
	
	
	
	
	

    ProductInfo findOne(String productId);

   
    Page<ProductInfo> findAll(Pageable pageable);
    
    
   
    ProductInfo save(ProductInfo productInfo);

   

	List<ProductInfo> findAll();
	
	List<ProductInfo> search(String name);

	
	


}
