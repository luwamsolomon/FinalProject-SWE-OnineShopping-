package edu.mum.cs425.onlineshopping.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import edu.mum.cs425.onlineshopping.entity.ProductInfo;
import edu.mum.cs425.onlineshopping.repository.ProductInfoRepository;
import edu.mum.cs425.onlineshopping.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository productInfoRepository;

   

    @Override
    public ProductInfo findOne(String productId) {
        ProductInfo productInfo = productInfoRepository.findFirstByProductId(productId);
        return productInfo;
    }

   

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAllByOrderByProductId(pageable);
    }

   

   

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

   

	@Override
	public List<ProductInfo> findAll() {
		// TODO Auto-generated method stub
		return productInfoRepository.findAll();
	}

	@Override
	public List<ProductInfo> search(String name) {
		// TODO Auto-generated method stub
		return productInfoRepository.search(name);
	}



	@Override
	public Page<ProductInfo> findPaginated(List<ProductInfo> products,Pageable pageable) {
		//List<ProductInfo> products=findAll();
		
		
		
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ProductInfo> list;
 
        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }
      
 
        Page<ProductInfo> bookPage
          = new PageImpl<ProductInfo>(list, PageRequest.of(currentPage, pageSize), products.size());
 
        return bookPage;
		
			
	}
	
	


}
