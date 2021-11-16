package com.food.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.food.model.Menu_Table;
import com.food.repository.MenuTableRepository;

@Service
public class UserCartService {
	private static final int ZERO = 0;
	@Autowired
	MenuTableRepository menuTableRepository;
	@Transactional
	public List<Menu_Table> viewMenu1() {
		List<Menu_Table> menuList = new ArrayList<Menu_Table>();
			for (Menu_Table menu : menuTableRepository.findAll()) {

				if ((menu.getAvailable_Quantity() > ZERO)
						&& (menu.getDate_Of_Updation().equals(LocalDate.now().toString())))
					menuList.add(menu);
			}
		return menuList;
	}
	@Transactional
	public List<Menu_Table> viewMenu() {
		List<Menu_Table> menuList = new ArrayList<Menu_Table>();
		menuList = (List<Menu_Table>) menuTableRepository.findAll();
		return menuList;
	}
	@Transactional
	public void saveOrUpdateEmployee(Menu_Table menu) {
		
			menuTableRepository.save(menu);
	}
}