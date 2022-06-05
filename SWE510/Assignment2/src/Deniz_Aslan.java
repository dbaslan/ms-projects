/**
 * @author Deniz Baran Aslan
 * @studentID 2021719183
 * @date 05.06.2022
 * 
 * This script gets input from the user, 
 * creates a polynomial with it, 
 * and calculates integral value based on the input
 * Submission for Assignment 2, SWE 510
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

public class Deniz_Aslan {

	public static void main(String[] args) {
		
		ArrayList<Integer> coefficients = new ArrayList<Integer>();
		
		Scanner input = new Scanner(System.in).useLocale(Locale.US); // locale fixes comma/period issue
		
		// Get polynomial degree from user
        System.out.println("Please enter the degree of your polynomial (max 3 for this assignment): ");
        int degree = input.nextInt();
        
        // Get coefficients from user, print out
        for (int i = 0; i <= degree; i++) {
        	System.out.println("Please enter the coefficient of x^" + i + ": ");
        	int c = input.nextInt();
        	coefficients.add(c);
        }
      
        System.out.println("The coefficients entered by user: " + coefficients);
        
        // Get delta value from user, print out
        System.out.println("Please enter the delta value: ");
        double delta = input.nextDouble();
        
        System.out.println("The delta value entered by user: " + delta);
		
        // Get range from user, print out
        System.out.println("Please enter the beginning of the range: ");
        double beginning = input.nextDouble();
        
        System.out.println("Please enter the end of the range: ");
        double end = input.nextDouble();
        
        System.out.println("The range entered by user: [" + beginning + "," + end + "]");
        
        // Close scanner
        input.close();
        
		// Create polynomial object
		Polynomial p = new Polynomial(coefficients);
		p.setDeltaX(delta);
		
		// Calculate integral value, print out
		double result = p.computeIntegral(beginning, end);
        System.out.println("The integral is approximately: " + result);
		
	}
	
}
