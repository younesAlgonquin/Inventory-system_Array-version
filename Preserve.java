import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Younes Boutaleb
 * Student Number: 041019068 
 * Assignment number: Assignment 1
 * Date: 6/8/2021
 * purpose of class: This class inherits the FoodItem class variables and methods and declares and reads the size of the jar.
 * Section #: 303
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 * @author Younes Boutaleb.
 * @version 1.0
 * 
 */
public class Preserve extends FoodItem {
	
	/**This is the size of the jar*/
	private int jarSize;
	
	/**
	 * This is a no-arg constructor
	 */
	public Preserve() {
			
	}//no-arg constructor
	
	/**
	 * This method prompt the user for the size of the jar 
	 * @return returns true if the program successfully reads in all fields, otherwise false
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		
		//Input validation state(true/false)
		boolean valid =super.addItem(scanner);
		
		//reads the size of the jar. it assigns true to valid if the user enters a valid value otherwise it loops
		do {
			//validates integer input
			try {
				System.out.print("Enter the size of the jar in millilitres: ");
				jarSize=scanner.nextInt();
				//Validates positive values
				if(jarSize<0) {
					System.out.println("Invalid entry");
					valid =false;
				}else
					valid =true;
			}catch(InputMismatchException ex){
				System.out.println("Invalid entry");
				valid=false;
				scanner.nextLine();
			}
		}while(!valid || jarSize<0);
		
		//returns true if the program successfully reads in all fields, otherwise false
		return valid;
	}
	
	/**
	 * This method format the object properties to a string
	 * @return This methods returns the current object's properties in a formated string
	 */
	@Override
	public String toString() {
			
		return String.format(super.toString() + "size: %dmL", jarSize);
	}//end toString

}//end class
