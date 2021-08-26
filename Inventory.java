import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Younes Boutaleb
 * Student Number: 041019068 
 * Assignment number: Assignment 1
 * Date: 6/8/2021
 * purpose of class: This class declares an array of FoodItem Object then declares methods that reads the object's properties,
 * update stock quantity and display the food item properties in a string format
 * Section #: 303
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 * @author Younes Boutaleb.
 * @version 1.0
 * 
 */
public class Inventory {
	
	/**This is an array of FoodItem objects*/
	private FoodItem[] inventory;
	/**This is the number of items entered by the user*/
	private int numItems;
	
	/**
	 * This is a parameterized constructor that initialize the inventory array
	 * @param size this is the size of the inventory array
	 */
	public Inventory(int size) {
		
		inventory=new FoodItem[size];
		
	}//end parameterized constructor 
	
	
	/**
	 * This method checks if the item object exists already in the array
	 * @param item this is the food item to be compared to the array elements
	 * @return Returns the index of a FoodItem in the inventory array with the same itemCode the item object else returns -1
	 */
	public int alreadyExists(FoodItem item) {
		
		//This loop breaks when it finds the item object or when it checks all entered items
		for(int i=0; i<numItems; i++) {
			
			if(item.isEqual(inventory[i])){
				return i;
			}//end for
			
		}//end alreadyExists
		
		return -1;
	}//end alreadyExists
	
	
	/**
	 * This method adds the food item chosen by the user to the inventory array 
	 * then, then calls the addItem method to read the object properties
	 * @param scanner this is a scanner object which is declared in the main method
	 * @return returns true if the program successfully reads in all fields, otherwise false
	 */
	public boolean addItem(Scanner scanner) {
		
		//This is the user input for the food item type
		String key="";
		//this is the first character of the user input
		char type = ' ';
		
		//The method returns false if the array is full
		if(numItems==inventory.length) {
			System.out.print("Array is full ..... ");
			return false;	
		}
		
		//clear the scanner as it has the integer option
		scanner.nextLine();
		
		//The while loop continues until the user enters a valid food type
		while(type != 'f' && type != 'v' && type != 'p') {
			
			//Prompts the user for the food type
			System.out.println("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
			key=scanner.nextLine();
	
			//The if statement checks whether the user entered a valid input or not
			if(key.length()!=1) {
				System.out.println("Invalid entry");
			}else {
				//if the user enters a character it assigns it to the type variable
				type=key.charAt(0);
				
				//Switch structure validates the user's choice then adds the a new food item to the array
				switch(type) {
				
				//Adds a fruit item to the array
				case 'f':
					inventory[numItems] = new Fruit();
					break;
				
				//Adds a fruit item to the array
				case 'v':
					inventory[numItems] = new Vegetable();
					break;
				
				//Adds a fruit item to the array	
				case 'p':
					inventory[numItems] = new Preserve();
					break;
				
				//Displays an error message if the entered character doesn't match any food item type
				default:
					System.out.println("Invalid entry");
					break;
				}//end switch
			}//end else
		}//end while
		
	
		//Loops until it reads a valid item code
		while(!inventory[numItems].inputCode(scanner)) {
			
			System.out.println("Invalid code");	
		}//end while
		
		//The addItem returns false if the entered code correspond to an existing item otherwise it continues
		if(alreadyExists(inventory[numItems])!=-1) {
			System.out.print("Item code already exists");
			//Clear the inventory[numItems] object from the array
			inventory[numItems]=null;
			return false;			
		}//end if
		
		//Reads the properties of the added food item 
		inventory[numItems].addItem(scanner);
		//increments the number of items in the array
		numItems++;
		//if the method executes all the above steps successfully it returns true
		return true;
		
	}//end addItem
	
	
	/**
	 * This class prompts for the item code and the buying/selling quantity then validates the inputs and updates the stock quantity
	 * In case of a non valid condition the method returns false and breaks immediately
	 * @param scanner This is the scanner object declared in the main method
	 * @param buyOrSell This variable defines the type of operation demanded by the user
	 * @return if the method executes successfully it returns true, otherwise false
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		
		//This is the item code
		int code = 0;
		//This is the update quantity
		int quantity = 0;
		//This is the index of the item having entered code
		int index;
		//This is the operation demanded by the user
		String choice;
		//Declare a FoodItem object
		FoodItem item = new FoodItem();
		
		//defines the type of operation selected by the user then assign it to choice
		if(buyOrSell) {
			choice="buy";
		}else {
			choice="sell";
		}//end if
		
		//The method outputs an error message and returns false if the array is empty 
		if(numItems==0) {
			System.out.printf("Error...could not %s item", choice);	
			return false;
		}//end if

		//The method outputs an error message and returns false if the array is empty
		if(!item.inputCode(scanner)){
			System.out.printf("Code not found in inventory...\nError...could not %s item", choice);
			return false;
		}//end if
		
		/*The method outputs an error message and returns false if the entered code doesn't correspond to any of the array elements
		 * This part has to execute only if the earlier steps are successfully executed 
		 * */
		index =alreadyExists(item);
		if(index==-1) {
			System.out.printf("Code not found in inventory...\nError...could not %s item", choice);
			return false;
		}

		//reads and checks whether the update quantity input is a positive integer or not
		try {
		System.out.printf("Enter valid quantity to %s: ", choice);
		quantity=scanner.nextInt();
		}catch(InputMismatchException ex){
			System.out.printf("Invalid quantity...\nError...could not %s item\n", choice);
			scanner.nextLine();
			return false;
			}
		//Validates positive values
		if(quantity<0) {
			System.out.printf("Invalid quantity...\nError...could not %s item\n", choice);
			return false;
		}

		//if the user chose to sell the item then quantity becomes negative
		if(!buyOrSell)
			quantity *= -1;
		
		//
		boolean quantityUpdateResult = inventory[index].updateItem(quantity);
		
		//checks if the stock quantity will remain positive before updating it then returns true or false
		if(!quantityUpdateResult)
			System.out.println("Insufficient stock in inventory...\nError...could not sell item\n");
			
		return quantityUpdateResult;
		}//end if
	
	
	/**
	 * This method format the objects' properties to a string
	 * @return This methods returns all the existing objects' properties in formated strings
	 */
	public String toString() {
		
		//This is the item's properties formated to a string, which override its content after each loop
		String formatString="Inventory:";
		
		for(int i=0; i<inventory.length && inventory[i]!=null; i++) {
			
			formatString +=inventory[i].toString();
		}
		return String.format(formatString);
	}//end toString

}//end class
