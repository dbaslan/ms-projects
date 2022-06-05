/**
 * @author Deniz Baran Aslan
 * @studentID 2021719183
 * @date 05.06.2022
 * 
 * This file defines the Polynomial class, 
 * its attributes and methods
 * Submission for Assignment 2, SWE 510
 */

import java.util.ArrayList;
import java.lang.Math;

public class Polynomial {
	
		// Data fields
		private ArrayList<Integer> coefficients = new ArrayList<>();
		private double delta;
		
		// Constructor
		public Polynomial(ArrayList<Integer> inputCoefficients) {
			coefficients = inputCoefficients;
		}
		
		// Method to calculate value of f(x) at x
		public double valueAt(double x) {
			double value = 0;
			for (int i = 0; i < coefficients.size(); i++)
				value += Math.pow(x, i)*coefficients.get(i);
			return value;
		}
		
		// Method to compute integral value, given range
		public double computeIntegral(double minX, double maxX) {
			double integral = 0;
			double currentX = minX;
			
			while (currentX < maxX) {
				integral += delta * valueAt(currentX);
				currentX += delta;
			}
			return integral;
		}
		
		// Setter for delta
		public void setDeltaX(double deltaX) {
			if (deltaX > 0)
				delta = deltaX;
			else
				System.out.println("Delta must be greater than zero.");
		}
		
}
