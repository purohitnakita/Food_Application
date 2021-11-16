package com.food.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.food.model.Cart_Table;
import com.food.model.Menu_Table;
import com.food.model.User_Table;
import com.food.repository.CartTableRepository;
import com.food.repository.MenuTableRepository;
import com.food.repository.OrderTableRepository;
import com.food.service.UserCartService;

@Controller
@RequestMapping("/user")
public class UserCartDaoController 
{
	private static final int ONE = 1;
	private static final int ZERO = 0;
	@Autowired
	UserCartService userTableService;
	@Autowired
	MenuTableRepository menuRepository;
	@Autowired
	CartTableRepository cartRepository;
	@Autowired
	OrderTableRepository orderRepository;
	LocalDate date = LocalDate.now();
	@GetMapping("/viewmenu")
	@ResponseBody
	public  List<Menu_Table> viewMenu() {
			List<Menu_Table> menuList = (List<Menu_Table>)userTableService.viewMenu1();	
		    return menuList;
	}
	@GetMapping("/addwithQuant/{dish_Name}")
	@ResponseBody
	public Menu_Table addQuant(@PathVariable("dish_Name") String dish_Name) {	
			Menu_Table dishMenu = menuRepository.findById(dish_Name).get();
			return dishMenu;
	}

	@PostMapping("/addToCart/{dish_Name}/{quantity}")
	@ResponseBody
	public  List<Cart_Table> addToCart(@PathVariable("dish_Name") String dish_Name, @PathVariable("quantity") int quantity) {
		//ModelAndView model1 = new ModelAndView("cart");
		
			Menu_Table dishMenu = menuRepository.findById(dish_Name).get();

			float totalPrice = ZERO;
			Cart_Table cartDetails = new Cart_Table();
//			cartDetails.setDish_img(dishMenu.getDish_img());
			cartDetails.setDish_name(dish_Name);
			cartDetails.setPrice(dishMenu.getPrice() * quantity);
			cartDetails.setQuantity(quantity);
			
			cartDetails.setDate_of_insert(date.toString());
			cartRepository.save(cartDetails);
			List<Cart_Table> cartList = (List<Cart_Table>) cartRepository.findAll();
			List<Cart_Table> cartList1 = new ArrayList<Cart_Table>();

			for (Cart_Table cart : cartList) {
				if ((cart.getDish_name().equals(cartDetails.getDish_name()))
						&& (cart.getPrice() == cartDetails.getPrice())
						&& (cart.getQuantity() == cartDetails.getQuantity()))
					//totalPrice = totalPrice + cart.getPrice();
					System.out.println("Successfully Updated in cart for Cart Id:" + cart.getCart_id());
					//System.out.println("tp"+ totalPrice);
					//System.out.println("list"+ cartList);
				if ((cart.getDate_of_insert().equals(date.toString())))
					cartList1.add(cart);
			}
			for (Cart_Table cart : cartList1)
				totalPrice = totalPrice + cart.getPrice();
			System.out.println("tp"+ totalPrice);
			System.out.println("list"+ cartList1);
//			model1.addObject("path", "../../../Images/");
	
		//return model1;
		return cartList1;
	}
	
	@GetMapping("/viewCart")
	@ResponseBody
	public List<Cart_Table> viewCart() {
		
			List<Cart_Table> cartList = (List<Cart_Table>) cartRepository.findAll();
			//if (dishMenu.getDate_Of_Updation().equals(date.toString()) && (dishMenu.getAvailable_Quantity() > ZERO))

			List<Cart_Table> cartList1 = new ArrayList<Cart_Table>();
			float totalPrice = ZERO;
//
			for (Cart_Table cart : cartList) {
				if ((cart.getDate_of_insert().equals(date.toString())))
					cartList1.add(cart);
			}
			for (Cart_Table cart : cartList1)
				totalPrice = totalPrice + cart.getPrice();
			System.out.println("tp"+ totalPrice);
			System.out.println("list"+ cartList1);
		return cartList1;
	}
	
	@DeleteMapping("/deleteCart/{cart_id}")
	@ResponseBody
	public String deleteCartId(@PathVariable("cart_id") int cart_id) {
		//ModelAndView model = new ModelAndView("cart");
			cartRepository.deleteById(cart_id);
			float totalPrice = ZERO;
			List<Cart_Table> cartList = (List<Cart_Table>) cartRepository.findAll();
			List<Cart_Table> cartList1 = new ArrayList<Cart_Table>();

			for (Cart_Table cart : cartList) {
				if ((cart.getDate_of_insert().equals(date.toString())))
					cartList1.add(cart);
			}
			for (Cart_Table i : cartList1)
				totalPrice = totalPrice + i.getPrice();
			//model.addObject("list", cartList1);
			System.out.println("tp"+ totalPrice);
			System.out.println("list"+cartList1);
			//model.addObject("path", "../../../Images/");
		
		return "Successfully deleted cart item" +cart_id;
	}
	
	@PutMapping("/editCartQuantOpr/{cart_id}/{dish_name}/{quantity}")
	@ResponseBody
	public String editCartOperation( @PathVariable("cart_id") int cart_id,
			@PathVariable("dish_name") String dish_name,@PathVariable("quantity") int quantity) {
		//ModelAndView model = new ModelAndView("cart");
	
			float totalPrice = ZERO;
			Menu_Table dishMenu = menuRepository.findById(dish_name).get();

			if ((dishMenu.getAvailable_Quantity() >= quantity) && (quantity > ZERO)) {
				Cart_Table cartDetails = cartRepository.findById(cart_id).get();
				cartDetails.setQuantity(quantity);
				cartDetails.setPrice(dishMenu.getPrice() * quantity);
				cartRepository.save(cartDetails);
				System.out.println("Quantity Successfully Updated");
			} else
			{
				System.out.println("Quantity Not Updated Out of Range");
			}
		List<Cart_Table> cartList = (List<Cart_Table>) cartRepository.findAll();
		List<Cart_Table> cartList1 = new ArrayList<Cart_Table>();
			for (Cart_Table cart : cartList) {
			if ((cart.getDate_of_insert().equals(date.toString())))
				cartList1.add(cart);
			}
			for (Cart_Table cart : cartList1) {
				totalPrice = totalPrice + cart.getPrice();
			System.out.println("list"+ cartList1);
			System.out.println("tp"+totalPrice);
			//model.addObject("path", "../../../Images/");
			}
			return "Successfully edited cart item" +dish_name;
	}
}
