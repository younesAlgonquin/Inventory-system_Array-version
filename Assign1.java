import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Assignment number: Assignment 1
 * Date: 6/8/2021
 * purpose of class: This class declares a method that displays the menu, and the main method which tests the program
 * Section #: 303
 * Course: CST8130 - Data Structures

 * @version 1.0
 * 
 */
public class Assign1 {

	/**
	 * This is the main method which tests the program
	 * @param args
	 */
	public static void main(String[] args) {
		
		//This is the only scanner object declared in this program
		Scanner scanner = new Scanner(System.in).useDelimiter("\r\n");
		//This is an inventory object which defines an array of FoodItem of size 20
		Inventory inventory =new Inventory(20);
		//This is the menu option chosen by the use
		int option = 0;
		//Input validation state(true/false)
		boolean valid ;
		
		//This loop continues until the user chose to exit the program
		while(option != 5) {
			
			valid=false;
			//if the user enters a non integer value the program displays an error message and loops back
			while(!valid) {
				
				displayMenu();
				//validates integer input
				try {
					option = scanner.nextInt();
					valid=true;
			}catch(InputMismatchException ex) {
				System.out.println("Incorrect value entered");
				scanner.nextLine();
			}//end catch
			}//end while
			
			//This switch structure executes the user's choice when it's valid otherwise it displays an error message
			switch(option) {
			
			//adds a new element to the array and reads its properties
			case 1:
				inventory.addItem(scanner);
				break;
			
			//Prints the properties of all existing items
			case 2:
				System.out.println(inventory.toString());
				break;
			
			//Allows the user to buy more of an existing item
			case 3:
				inventory.updateQuantity(scanner, true);
				break;
			
			//Allows the user to sell some quantity of an existing item
			case 4:
				inventory.updateQuantity(scanner, false);
				break;
			//Exits the program	
			case 5:
				System.out.print("Exiting...");
				break;
			
			//displays an error message
			default:
				System.out.println("Incorrect value entered");
				break;
					
				}//end switch
			}//end while

		//Close the scanner object
		scanner.close();

	}//end main
	
	/**
	 * This method displays the menu
	 */
	public static void displayMenu() {
		
		//This stores the menu options 
		String formatSring="\nPlease select one of the following:";
		formatSring+="\n%d: Add Item to Inventory";
		formatSring+="\n%d: Display Current Inventory";
		formatSring+="\n%d: Buy Item(s)";
		formatSring+="\n%d: Sell Item(s)";
		formatSring+="\n%d: To Exit\n";
		
		System.out.print(String.format(formatSring, 1,2,3,4,5)); 	
		
	}//end displayMenu

}//end class
