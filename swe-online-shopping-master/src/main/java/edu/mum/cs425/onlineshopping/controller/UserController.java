package edu.mum.cs425.onlineshopping.controller;


import edu.mum.cs425.onlineshopping.entity.User;
import edu.mum.cs425.onlineshopping.repository.UserRepository;
import edu.mum.cs425.onlineshopping.service.RoleService;
import edu.mum.cs425.onlineshopping.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private RoleService roleService;
   
  

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/")
    public String Home() {
       
            return "/home/index";
        

    }
    
    @PostMapping("/login")
    public String checkLogin(@RequestParam("email") String email,@RequestParam("password") String password,Model model) {
    	

    	model.addAttribute("user",new User());
    	
    
   
    	
    	
    	return "product/index";
    	
    }
    
    
    
    

    @GetMapping("/register")
    public String showForm(Model model) {
    	
    	model.addAttribute("user", new User());
    	model.addAttribute("roles", roleService.getRoles());
        return "user/signup";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        
    	
    
    	
    	
    
        if (bindingResult.hasErrors()) {
        	
           
            redirectAttributes.addFlashAttribute("user", user);
            return "register";
        }
        userService.save(user);
        return "redirect:/login";
    }

    

   

}
