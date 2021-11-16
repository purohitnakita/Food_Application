package com.food.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.food.model.Menu_Table;
import com.food.repository.MenuTableRepository;
import com.food.service.AdminUpdateService;

@Controller
public class AdminDaoController {
	@Autowired
	AdminUpdateService adminUpdateService;
	@Autowired
	MenuTableRepository menuTableRepository;

	@GetMapping("/")
	@ResponseBody
	public String updateAdminPage() {
		return "Admin home page";
	}

	@PostMapping("/createdish")
	@ResponseBody
	public String postAdminUpdate(@RequestBody Menu_Table adminUpdate) {
		 adminUpdateService.saveMenuDetails(adminUpdate);
		 return adminUpdate.getDish_Name();
		
	}

	
	@GetMapping("/alldish")
	@ResponseBody
	public List<Menu_Table> all()
	{
		List<Menu_Table> menu=(List<Menu_Table>)adminUpdateService.menuList();
		return menu;
	}
	
//	@DeleteMapping("/delete")
//	@ResponseBody
//	public String  deleteDish(@RequestBody Menu_Table delete) {
//			adminUpdateService.deletedish(delete);
//			return "Successfully deleted single record";
//	}
//	
	@DeleteMapping("/delete/{dish_Name}")
	@ResponseBody
	public String deleteDish(@PathVariable("dish_Name") String dish_Name) {
		//ModelAndView model = new ModelAndView("index");
		
			menuTableRepository.deleteById(dish_Name);
			System.out.println("Items"+menuTableRepository.findAll());
		return "Successfully deleted single record"+dish_Name;
	}

//	@PutMapping("/edit")
//	@ResponseBody
//	public Menu_Table editDish(@RequestBody Menu_Table adminUpdate) {
//		adminUpdateService.saveDish(adminUpdate);
//			return adminUpdate;
//	}
	
	@PutMapping("/edit/{dish_Name}/{ingredients}/{available_Quantity}/{price}")
	@ResponseBody
	public Menu_Table editDish(@PathVariable("dish_Name") String dish_Name,
			@PathVariable("ingredients") String ingredients,
			@PathVariable("available_Quantity") int available_Quantity, @PathVariable("price") float price) {
		
			Menu_Table dish = new Menu_Table(dish_Name, ingredients, available_Quantity, price);
			adminUpdateService.saveDish(dish);
			System.out.println("Items"+ menuTableRepository.findAll());

		return dish;
	}
			
}