package com.food.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import com.food.model.Menu_Table;
import com.food.model.Order_Table;
import com.food.model.User_Table;
import com.food.repository.MenuTableRepository;
import com.food.service.AdminUpdateService;
import com.food.service.UserService;
import com.food.service.UserTableService;

@Controller
@RequestMapping("/user")
public class UserDaoController {
	private static final int ONE = 1;
	private static final int ZERO = 0;
	@Autowired
	AdminUpdateService adminUpdateService;
	@Autowired
	UserService userService;
	@Autowired
	UserTableService userTableService;
	@Autowired
	MenuTableRepository menuTableRepository;
/*	@RequestMapping("/logout")
	public String login(@SessionAttribute("user") User_Table user) {
		try {
			if (user.getUser_id() != ZERO)
				user.setUser_id(ZERO);
		} catch (Exception exception) {
			System.out.println("Exception caught in login Method :" + exception);
		}
		return "login";
	}*/

	public List<Menu_Table> todaysMenu() {
		List<Menu_Table> todaysMenuList = new ArrayList<Menu_Table>();
		try {
			LocalDate date = LocalDate.now();
			List<Menu_Table> dishMenuList = adminUpdateService.menuList();
			System.out.println(dishMenuList);
			for (Menu_Table dishMenu : dishMenuList) {
				if (dishMenu.getDate_Of_Updation().equals(date.toString()) && (dishMenu.getAvailable_Quantity() > ZERO))
					todaysMenuList.add(dishMenu);
				System.out.println(todaysMenuList);
			}
		} catch (Exception exception) {
			System.out.println("Exception caught in todaysMenu Method :" + exception);
		}
		return todaysMenuList;
	}

	@GetMapping("/userHomePage")
	@ResponseBody
	public List<Menu_Table> userHomePage() {
		//ModelAndView model = new ModelAndView("userhomepage");
		List<Menu_Table> menu=todaysMenu();
			System.out.println("todays_menu"+ todaysMenu());
			//model.addObject("path", "../Images/");
//			if (user.getUser_id() == ZERO)
//				model.addObject("cust", null);
//			else {
//				User_Table userInfo = userTableService.FetchUserName(user.getUser_id());
//				model.addObject("cust", userInfo.getUser_name());
//			}

		return menu;
	}


	@GetMapping("/searchDishOpr/{dish_Name}")
	@ResponseBody
	public List<Menu_Table> searchDishOperation(@PathVariable("dish_Name") String dish_Name) {
		//ModelAndView model = new ModelAndView("searchDish");
		
			List<Menu_Table> dishMenuList = userService.searchMenuList(dish_Name);
			System.out.println("SearchRes"+ dishMenuList);
			System.out.println("search_key"+ dish_Name);
			
			if (dishMenuList.isEmpty()) {
				System.out.println( "No Result Found for Search ");
			}
		
		return dishMenuList;
	}

}