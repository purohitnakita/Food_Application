package com.food.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_table")
public class Menu_Table {
	@Id
	private String dish_Name;
	private String ingredients;
	private int available_Quantity;
	private float price;
	private String date_Of_Updation;
	

	public Menu_Table() {
		super();
	}

	public Menu_Table(String dish_Name, String ingredients, int available_Quantity, float price) {
		super();
		this.dish_Name = dish_Name;
		this.ingredients = ingredients;
		this.available_Quantity = available_Quantity;
		this.price = price;
		
	}

	@Override
	public String toString() {
		return "Menu_Table [dish_Name=" + dish_Name + ", ingredients=" + ingredients + ", available_Quantity=" + available_Quantity + ", price=" + price
				+ ", date_Of_Updation=" + date_Of_Updation + "]";
	}


	public float getPrice() {
		return price;
	}

	public String getDish_Name() {
		return dish_Name;
	}

	public void setDish_Name(String dish_Name) {
		this.dish_Name = dish_Name;
	}

	public int getAvailable_Quantity() {
		return available_Quantity;
	}

	public void setAvailable_Quantity(int available_Quantity) {
		this.available_Quantity = available_Quantity;
	}

	public String getDate_Of_Updation() {
		return date_Of_Updation;
	}

	public void setDate_Of_Updation(String date_Of_Updation) {
		this.date_Of_Updation = date_Of_Updation;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

}