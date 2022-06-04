/**
 * @author Deniz Baran Aslan
 * @studentID 2021719183
 * @date 05.06.2022
 * 
 * This script reads ...
 * Submission for Assignment 2, SWE 510
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

public class Deniz_Aslan {

	public static void main(String[] args) {
		
		ArrayList<Integer> coefficients = new ArrayList<Integer>();
		
		Scanner input = new Scanner(System.in).useLocale(Locale.US); //locale
		
        System.out.println("Please enter the degree of your polinomial (4 for this assignment):");
        int degree = input.nextInt();
        
        for (int i = 0; i <= degree; i++) {
        	System.out.println("Please enter the coefficient of x^"+i+": ");
        	int c = input.nextInt();
        	coefficients.add(c);
        }
        
        // Displaying the numbers
        System.out.println("The coefficients entered by user: ");
        
        for (int i = 0; i < coefficients.size(); i++) { 		      
        	System.out.println(coefficients.get(i)); 		
        }   
        
        System.out.println("Please enter the delta value: ");
        double delta = input.nextDouble();
        
        System.out.println("The delta value entered by user: "+delta);
		
        System.out.println("Please enter the beginning of the range: ");
        double beginning = input.nextDouble();
        
        System.out.println("Please enter the end of the range: ");
        double end = input.nextDouble();
        
        System.out.println("The range entered by user: ["+beginning+","+end+"]");
        
        // Closing Scanner after the use
        input.close();
        
		// Create polynomial object
		Polynomial p = new Polynomial(coefficients);
		p.setDelta(delta);
		double result = p.computeIntegral(beginning, end);
        System.out.println("The integral is approximately: "+result);
		
	}
	
}
