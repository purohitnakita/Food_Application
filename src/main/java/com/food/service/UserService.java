package com.food.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.model.Menu_Table;
import com.food.model.Order_Table;
import com.food.repository.MenuTableRepository;
import com.food.repository.OrderTableRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	MenuTableRepository menuTableRepository;
	@Autowired
	OrderTableRepository orderTableRepository;

	public List<Menu_Table> searchMenuList(String search_dish) {
		List<Menu_Table> searchResultList = new ArrayList<Menu_Table>();
		
			List<Menu_Table> menuList = (List<Menu_Table>) menuTableRepository.findAll();
			LocalDate date = LocalDate.now();

			for (Menu_Table dishMenu : menuList) {
				if ((dishMenu.getDish_Name().contains(search_dish))
						&& (dishMenu.getDate_Of_Updation().equals(date.toString()))
						&& (dishMenu.getAvailable_Quantity() > 0))
					searchResultList.add(dishMenu);
			}
		return searchResultList;
	}

	public List<Order_Table> searchOrderList(String search_order, int id) {
		List<Order_Table> searchResultList = new ArrayList<Order_Table>();
		try {
			List<Order_Table> menuAllList = (List<Order_Table>) orderTableRepository.findAll();

			for (Order_Table dishMenu : menuAllList) {
				if ((search_order.contains(Integer.toString(dishMenu.getOrder_id()))
						|| (Integer.toString(dishMenu.getOrder_id()).contains(search_order))
						|| search_order.equalsIgnoreCase("ORD")))
					searchResultList.add(dishMenu);
			}
		} catch (Exception exception) {
			System.out.println("Exception caught in searchOrderList Method :" + exception);
		}
		return searchResultList;
	}

	public void orderSave(Order_Table orderDetails) {
		orderTableRepository.save(orderDetails);
	}

	public List<Order_Table> searchOrderList1(String search_order) {

		List<Order_Table> searchResultList = new ArrayList<Order_Table>();
		try {
			List<Order_Table> orderList = (List<Order_Table>) orderTableRepository.findAll();
			for (Order_Table order : orderList) {
				String orderId = "ord" + (Integer.toString(order.getOrder_id()));
				String orderId1 = "ORD" + (Integer.toString(order.getOrder_id()));
				if ((orderId.contains(search_order) || orderId1.equals(search_order)
						|| search_order.equalsIgnoreCase("ord") || orderId.contentEquals(search_order)))
						
					searchResultList.add(order);
			}
		} catch (Exception exception) {
			System.out.println("Exception caught in searchOrderList1 Method :" + exception);
		}
		return searchResultList;
	}

}

