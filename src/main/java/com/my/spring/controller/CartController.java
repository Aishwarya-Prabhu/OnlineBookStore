package com.my.spring.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.AdvertDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.DAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.dao.CartDAO;
import com.my.spring.pojo.Advert;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.User;

@Controller
@RequestMapping("/cart/*")
public class CartController extends DAO{

	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;
	
	@Autowired
	ServletContext servletContext;
	
	
	@RequestMapping(value = "/cart/insert", method = RequestMethod.POST)
	   public ModelAndView addCategory(@ModelAttribute("cart") Cart cart, BindingResult result, HttpServletRequest request) throws Exception {
	        HttpSession session = (HttpSession) request.getSession();
	        User u = (User)session.getAttribute("user");
	        System.out.println(u.getUsername()+"---------------------------------------------------");
	        System.out.println(request.getParameter("totalprice")+" "+request.getParameter("id")+"---------------------------------------------------");
	        if (cart!=null)
	            System.out.println("not null"+cart.getId()+cart.getTitle()+"---------------------------------------------------");
	        cart.setUser(u);
	        u.setCart(cart);    
	        
	        Cart cd=null;
//	      Cart c = cartDao.insert(cart);
//	      User user1 = cartDao.update(u);
//	       getSession().update(u);
	         List<Cart> c=cartDao.list();      
	         Advert adv = getSelectedAdvert(request);
	        
	         int i=0;
	         Cart cw = null;
	         for(Cart cc:c){
	             if(u.getPersonID()==cc.getId()){
	                 Set<Advert> cartAdverts = cc.getAdverts();
	                 cartAdverts.add(adv);
	                 cart = cartDao.updateCart(cc);
	                 i=1;
//	               return new ModelAndView("user-cart","c",cw);
	                 return new ModelAndView("user-cart","adverts",cartAdverts);
	             }
	         }
	         
	     Set<Advert> cartAdverts = cart.getAdverts();  
	     if (i==0){
	          System.out.println("i==0"+"---------------------------------------------------");
	          cartAdverts.add(adv);
	          cd = cartDao.insert(cart);
	     }
	    
	        return new ModelAndView("user-cart","adverts",cartAdverts);
	    }
	    
	    private Advert getSelectedAdvert(HttpServletRequest request) {
	        int advID = Integer.parseInt(request.getParameter("id"));
	        return advertDao.getAdvert(advID);
	    }
}
