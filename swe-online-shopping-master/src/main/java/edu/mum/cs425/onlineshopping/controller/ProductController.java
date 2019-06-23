package edu.mum.cs425.onlineshopping.controller;

import edu.mum.cs425.onlineshopping.entity.ProductInfo;
import edu.mum.cs425.onlineshopping.fileupload.StorageService;
import edu.mum.cs425.onlineshopping.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller

public class ProductController {
  
    @Autowired
    ProductService productService;
   
    
    private final StorageService storageService;
	 
	  @Autowired
	    public ProductController(StorageService storageService) {
	        this.storageService = storageService;
	    }
    
    

    @GetMapping(value = "/product/list")
    public String lisProducts(
    	      Model model, 
    	      @RequestParam("page") Optional<Integer> page, 
    	      @RequestParam("size") Optional<Integer> size) {
    	        int currentPage = page.orElse(1);
    	        int pageSize = size.orElse(12);
    	        List<ProductInfo> list=productService.findAll();
    	        //Collections.shuffle(list);
    	 
    	        Page<ProductInfo> productPage = productService.findPaginated(list,PageRequest.of(currentPage - 1, pageSize));
    	 
    	        model.addAttribute("productPage",productPage);
    	 
    	        int totalPages = productPage.getTotalPages();
    	        if (totalPages > 0) {
    	            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
    	                .boxed()
    	                .collect(Collectors.toList());
    	            model.addAttribute("pageNumbers", pageNumbers);
    	        }
    	 
    	        return "product/index";
    	    }
    	

      
    
    
    @GetMapping(value = "/search")
    public String search(Model model, String searchword,
    @RequestParam("page") Optional<Integer> page, 
    @RequestParam("size") Optional<Integer> size) {
      int currentPage = page.orElse(1);
      int pageSize = size.orElse(12);
    	
    List<ProductInfo> list=productService.search(searchword);

        Page<ProductInfo> productPage = productService.findPaginated(list,PageRequest.of(currentPage - 1, pageSize));
   	 
        model.addAttribute("productPage",productPage);
 
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
 
        return "product/index";
    }
    
    
    
    
    
    @GetMapping(value = "/product/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new ProductInfo());
        return "product/new";
    }
    
    
    
    
    @PostMapping(value = "/product/new")
    public String addNewProduct(@Valid @ModelAttribute("product") ProductInfo productInfo,
    		@RequestParam("file") MultipartFile file,
        BindingResult bindingResult, Model model) {
    	
    	
    	
    	
    	
    	
        if (bindingResult.hasErrors()) {
        	
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "product/new";
        }
        if(productInfo.getProductIcon().equalsIgnoreCase(",")) {
        	productInfo.setProductIcon("no-image-available.jpg");
        }
        
        if(!file.isEmpty()) {
        storageService.store(file);
        
        productInfo.setProductIcon(file.getOriginalFilename());
        }
       
        	
        
       productInfo = productService.save(productInfo);
       
      
        return "redirect:/product/list";
    }
    
    
    
    
    
    
    

}
