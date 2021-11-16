package com.food.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.food.model.Cart_Table;
import com.food.model.Menu_Table;
import com.food.model.Order_Table;
import com.food.model.User_Table;
import com.food.repository.CartTableRepository;
import com.food.repository.MenuTableRepository;
import com.food.repository.OrderTableRepository;
import com.food.service.UserService;

@Controller
@RequestMapping("/user")
public class UserOrderDaoController 
{
	private static final int ONE = 1;
	private static final int ZERO = 0;
	private static final int FOUR = 4;
	@Autowired
	CartTableRepository cartTableRepository;
	@Autowired
	OrderTableRepository orderTableRepository;
	@Autowired
	MenuTableRepository menuTableRepository;
	@Autowired
	UserService userService;
	LocalDate date = LocalDate.now();

	@GetMapping("/placeOrder")
	@ResponseBody
	public List<Cart_Table>  placeOrder() {
		ModelAndView model = new ModelAndView("payment");
		
			List<Cart_Table> cartAllList = (List<Cart_Table>) cartTableRepository.findAll();
			List<Cart_Table> cartList = new ArrayList<Cart_Table>();
			for (Cart_Table cart : cartAllList) {
				if ((cart.getDate_of_insert().equals(date.toString())))
					cartList.add(cart);
			}

			float totalPrice = ZERO;
			for (Cart_Table cart : cartList)
				totalPrice = totalPrice + cart.getPrice();
			System.out.println("tp"+ totalPrice);
			System.out.println("listUser"+ cartList);
			//model.addObject("path", "../../Images/");

		return cartList;
	}
	@GetMapping("/proceedPayment/{amount}")
	@ResponseBody
	public float proceedPayment(@PathVariable("amount") float amount) {
		//ModelAndView model = new ModelAndView("proceedPayment");
		try {
			System.out.println("amount"+ amount);
		} catch (Exception exception) {
			System.out.println("Exception caught in proceedPayment Method :" + exception);
		}
		return amount;
	}
	
	@PostMapping("/confirmBooking")
	@ResponseBody
	public List<Order_Table>  confirmBooking(@RequestBody User_Table userDetails) {
		//ModelAndView model = new ModelAndView("myOrder");
		
			List<Cart_Table> cartAllList = (List<Cart_Table>) cartTableRepository.findAll();

			List<Cart_Table> cartList = new ArrayList<Cart_Table>();
			for (Cart_Table cart : cartAllList) {
				if ((cart.getDate_of_insert().equals(date.toString())))
					cartList.add(cart);
			}
			System.out.println("cart" + cartList);
			for (Cart_Table cart : cartList) {
				Order_Table orderDetails = new Order_Table();
				orderDetails.setAddress(userDetails.getAddress());
				orderDetails.setDate_of_order(date.toString());
				orderDetails.setEmail(userDetails.getEmail());
				orderDetails.setMode_of_payment("COD");
				orderDetails.setPh_number(userDetails.getPh_number());
				//orderDetails.setUser_id(user.getUser_id());
				orderDetails.setUser_name(userDetails.getUser_name());
				orderDetails.setDish_name(cart.getDish_name());
				//orderDetails.setDish_img(cart.getDish_img());
				orderDetails.setPrice(cart.getPrice());
				orderDetails.setQuantity(cart.getQuantity());
				orderTableRepository.save(orderDetails);
				cartTableRepository.deleteById(cart.getCart_id());
				Menu_Table menuDetails = menuTableRepository.findById(cart.getDish_name()).get();
				menuDetails.setAvailable_Quantity(menuDetails.getAvailable_Quantity() - cart.getQuantity());
				menuTableRepository.save(menuDetails);
			}

			List<Order_Table> orderAllList = (List<Order_Table>) orderTableRepository.findAll();
			List<Order_Table> orderList = new ArrayList<Order_Table>();
			for (Order_Table order : orderAllList) {
			
					orderList.add(order);
			}
			List<Order_Table> orderList1 = new ArrayList<Order_Table>();
			int x = ZERO;
			System.out.println(orderList.size());
			for (int j = orderList.size() - ONE; j >= ZERO; j--) {
				orderList1.add(orderList.get(j));
				if (++x > FOUR)
					break;
			}
			System.out.println("order"+ orderList1);
            return orderList1;
	}
	
	@GetMapping("/myOrder")
	@ResponseBody
	public List<Order_Table> displayOrder() {
		//ModelAndView model = new ModelAndView("myOrder");
		
			List<Order_Table> orderAllList = (List<Order_Table>) orderTableRepository.findAll();
			List<Order_Table> orderList = new ArrayList<Order_Table>();
			for (Order_Table order : orderAllList) {
				//if (order.getUser_id() == user.getUser_id())
					orderList.add(order);
			}
			List<Order_Table> orderList1 = new ArrayList<Order_Table>();
			int x = ZERO;
			System.out.println(orderList.size());
			for (int j = orderList.size() - ONE; j >= ZERO; j--) {
				orderList1.add(orderList.get(j));
				if (++x > FOUR)
					break;
			}
			System.out.println("order"+ orderList1);
		return orderList;
	}


}
