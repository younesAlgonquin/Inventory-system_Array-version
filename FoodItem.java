import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Assignment number: Assignment 1
 * Date: 6/8/2021
 * purpose of class: This class represents the super class for this project.It declares the commun attribures of all food items.
 * It also declares methods which read , check, update and print states of the food item objects
 * Section #: 303
 * Course: CST8130 - Data Structures
 * @version 1.0
 * 
 */
public class FoodItem {
	
	/**this is the food item identification code*/
	protected int itemCode;
	/**this is the food item name*/
	protected String itemName;
	/**this is the food item price*/
	protected float itemPrice;
	/**this is the available quantity in stock*/
	protected int itemQuantityInStock;
	/**this is the food item cost*/
	protected float itemCost;
	
	/**
	 * This is a no-arg constructor
	 */
	public FoodItem() {
		
	}//end no-arg constructor
	

	/**
	 * This method prompt the user for the food item information
	 * @param scanner This is a scanner object which is declared in the main method
	 * @return this method returns true if the method successfully reads in all fields, otherwise returns false 
	 */
	public boolean addItem(Scanner scanner) {
		
		//Input validation state(true/false)
		boolean valid;
		//Clears the newline character remaining in the scanner variable after reading the integer itemCode value 
		scanner.nextLine();
		//Reads the name of the food item
		System.out.print("Enter the name for the item: ");
		itemName=scanner.nextLine();
		
		//reads the item quantity to be stored. it assigns true to valid if the user enters a valid value otherwise it loops
		do {
			//validates integer input
			try {
			System.out.print("Enter the quantity for the item: ");
			itemQuantityInStock=scanner.nextInt();
			//Validates positive values
			if(itemQuantityInStock<0) {
				System.out.println("Invalid entry");
				valid =false;
			}else
				valid =true;
			}catch(InputMismatchException ex){
				System.out.println("Invalid entry");
				valid=false;
				scanner.nextLine();
				}
				
			}while(!valid || itemQuantityInStock<0);
		
		//reads the item cost. it assigns true to valid if the user enters a valid value otherwise it loops
		do {
			//validates integer input
			try {
			System.out.print("Enter the cost of the item: ");
			itemCost=scanner.nextFloat();
			//Validates positive values
			if(itemCost<0) {
				System.out.println("Invalid entry");
				valid =false;
			}else
				valid =true;
			}catch(InputMismatchException ex){
				System.out.println("Invalid entry");
				valid=false;
				scanner.nextLine();
				}
			}while(!valid || itemCost<0);
			
		//reads the item price. it assigns true to valid if the user enters a valid value otherwise it loops
		do {
			//validates integer input
			try {
			System.out.print("Enter the sales price of the item: ");
			itemPrice=scanner.nextFloat();
			//Validates positive values
			if(itemPrice<0) {
				System.out.println("Invalid entry");
				valid =false;
			}else
				valid =true;
			}catch(InputMismatchException ex){
				System.out.println("Invalid entry");
				valid=false;
				scanner.nextLine();
				}
			}while(!valid || itemPrice<0);
		
		//returns true if the method successfully reads in all fields, otherwise false
		return valid;
	}//end addItem
	
	/**
	 * This method prompt the user for the item code
	 * @param scanner This is a scanner object which is declared in the main method
	 * @return this method returns true if the method successfully reads ihe item code, otherwise returns false
	 */
	public boolean inputCode(Scanner scanner) {
		
		//Input validation state(true/false)
		boolean valid;
		
		//reads the item code. it assigns true to valid if the user enters a valid value otherwise false
		try {
			System.out.print("Enter the code for the item: ");
			itemCode=scanner.nextInt();
			valid =true;
			}catch(InputMismatchException ex){
				valid=false;
				scanner.nextLine();
				}

		//returns true if the method successfully reads the item code, otherwise false
		return valid;
	
	}//end inputCode
	
	/**
	 * This method updates the item quantity in stock after each buying/selling operation
	 * @param amount This is the quantity to buy or sell 
	 * @return returns true if the method successfully updates the quantity, otherwise false
	 */
	public boolean updateItem(int amount) {
		
		//checks if the stock quantity remains positive before updating it
		if(itemQuantityInStock+amount>=0) {
			itemQuantityInStock += amount;
			return true;
			}else {	
				return false;
			}
		
	}//end updateItem
	
	
	/**
	 * 
	 * @param item Is the food item to be compared with the current object
	 * @return returns true if the item object has the same item code of the object that called this method, otherwise false
	 */
	public boolean isEqual(FoodItem item) {
		if(itemCode==item.itemCode) {
			return true;
		}else {
			return false;
		}

	}//end isEqual
	
	/**
	 * This method format the object properties to a string
	 * @return This methods returns the current object's properties in a formated string
	 */
	public String toString() {
		
		return String.format("\nItem: %d %s %d price: $%.2f cost: $%.2f ", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);
	}//end to String

}//end class
