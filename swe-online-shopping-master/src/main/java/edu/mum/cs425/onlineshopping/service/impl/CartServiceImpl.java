package edu.mum.cs425.onlineshopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs425.onlineshopping.dto.Item;
import edu.mum.cs425.onlineshopping.entity.ProductInfo;
import edu.mum.cs425.onlineshopping.entity.User;
import edu.mum.cs425.onlineshopping.dto.ItemForm;
import edu.mum.cs425.onlineshopping.service.CartService;
import edu.mum.cs425.onlineshopping.service.ProductService;

import java.math.BigDecimal;
import java.util.*;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
   
    private List<Item> cartItems = new ArrayList<>();

    @Override
    public void addItem(ItemForm itemForm) {
        ProductInfo productInfo = productService.findOne(itemForm.getProductId());


        cartItems.add(new Item(productInfo, itemForm.getQuantity()));

       
    }

   

    @Override
    public List<Item> findAll() {
        return cartItems;
    }

    @Override
    @Transactional
    public void checkout(User user) {
       
       
       cartItems.clear();
    }

    @Override
    public BigDecimal getTotal() {
        Collection<Item> items = findAll();
        BigDecimal total = new BigDecimal(0);
        for (Item item : items) {
            BigDecimal price = item.getProductInfo().getProductPrice();
            BigDecimal quantity = new BigDecimal(item.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }
}
