package edu.mum.cs425.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import edu.mum.cs425.onlineshopping.dto.Item;
import edu.mum.cs425.onlineshopping.entity.User;
import edu.mum.cs425.onlineshopping.dto.ItemForm;
import edu.mum.cs425.onlineshopping.service.CartService;
import edu.mum.cs425.onlineshopping.service.ProductService;
import edu.mum.cs425.onlineshopping.service.UserService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
  
    
    
    @GetMapping("/add/{productId}")
    public String showOne(@PathVariable("productId") String productId, Model model) {
    	
    	
    	
    	
    	ItemForm itemForm=new ItemForm(1,productId);
    	
    
    	cartService.addItem(itemForm);
    	
    	
        model.addAttribute(itemForm);
        
     
        
        
        
        return "redirect:/product/list";
        
        
        
        
  
    }
    
    
    
    
    
    
    
    
    
    

    @GetMapping("")
    public String findAll(Model model){
        Collection<Item> items = cartService.findAll();
        
        
        System.out.println(Arrays.toString(items.toArray()));
        BigDecimal total = cartService.getTotal();
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        
        return "cart/productsincart";
        
    }
    
    
    

    
    
    
    
    
    
    
    @PostMapping("")
    public String addToCart(@Valid ItemForm itemForm, BindingResult bindingResult, Model model) {
        
        cartService.addItem(itemForm);
        return "redirect:" + "/cart";
    }

    @GetMapping("/checkout")
    public  String checkout(Model model, Principal principal) {
        User user = userService.findOne(principal.getName());// Email as username
        cartService.checkout(user);

        
       
        return "/cart/success";
    }

   


}
