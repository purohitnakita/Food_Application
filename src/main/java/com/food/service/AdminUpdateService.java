package com.food.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.food.model.Menu_Table;
import com.food.repository.MenuTableRepository;



@Service
public class AdminUpdateService {
	@Autowired
	MenuTableRepository menuTableRepository;
	@Transactional
	public Menu_Table saveMenuDetails(Menu_Table adminUpdate) {
		
			LocalDate date = LocalDate.now();
			System.out.println(date);
			String d = date.toString();
			adminUpdate.setDate_Of_Updation(d);
			return menuTableRepository.save(adminUpdate);
	}
//	public List<Menu_Table> saveMenuDetails(List<Menu_Table> menulist) {
//		Menu_Table tab=new Menu_Table();
//		LocalDate date = LocalDate.now();
//		String d = date.toString();
//		tab.setDateOfUpdation(d);
//		menulist.add(tab);
//		List<Menu_Table> response = (List<Menu_Table>) menuTableRepository.save(menulist);
//		return response;
//	}
	@Transactional
	public List<Menu_Table> menuList() {
		List<Menu_Table> menuList = new ArrayList<Menu_Table>();
			menuList = (List<Menu_Table>) menuTableRepository.findAll();
		return menuList;
	}

//	public void menuList1(List<Menu_Table> menuList ) {
//		menuTableRepository.delete(menuList);
//	}
	
	@Transactional
	public void deletedish(Menu_Table menuList) {
		
			menuTableRepository.delete(menuList);
	}
	
	@Transactional
	public void deletedish1(String dish_Name) {
	
			menuTableRepository.deleteById(dish_Name);

	}
//	@Transactional
//	public Menu_Table saveDish(Menu_Table dish) {
//		LocalDate date = LocalDate.now();
//		System.out.println(date);
//		String d = date.toString();
//		dish.setDate_Of_Updation(d);
//		return menuTableRepository.save(dish);
//		
//	}
	
	@Transactional
	public void saveDish(Menu_Table dish_Name) {
		LocalDate date = LocalDate.now();
		System.out.println(date);
		String d = date.toString();
		dish_Name.setDate_Of_Updation(d);
		 menuTableRepository.save(dish_Name);
		
	}
//	public List<Menu_Table> saveDish(List<Menu_Table> menulist) {
//		LocalDate date = LocalDate.now();
//		String d = date.toString();
//		((Menu_Table) menulist).setDateOfUpdation(d);
//		List<Menu_Table> response = (List<Menu_Table>) menuTableRepository.save(menulist);
//		return response;
//	}
}
