package edu.mum.cs425.onlineshopping.service;

import edu.mum.cs425.onlineshopping.dto.Item;
import edu.mum.cs425.onlineshopping.entity.User;
import edu.mum.cs425.onlineshopping.dto.ItemForm;

import java.math.BigDecimal;
import java.util.List;


public interface CartService {
    void addItem(ItemForm itemForm);
   

    List<Item> findAll();

    void  checkout(User user);

    BigDecimal getTotal();

}
