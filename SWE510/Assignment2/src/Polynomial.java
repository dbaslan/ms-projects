import java.util.ArrayList;
import java.lang.Math;

public class Polynomial {
	
		// Data fields
		private ArrayList<Integer> coefficients = new ArrayList<>();
		private double delta;
		
		// Constructors
		public Polynomial(ArrayList<Integer> inputCoefficients) {
			coefficients = inputCoefficients;
		}
		
		public Polynomial() {
			
		}

		// Class Methods
		public double valueAt(double x) {
			double value = 0;
			for (int i = 0; i < coefficients.size(); i++)
				value += Math.pow(x, i)*coefficients.get(i);
			return value;
		}
		
		public double computeIntegral(double minX, double maxX) {
			double integral = 0;
			double currentX = minX;
			
			while (currentX < maxX) {
				integral += delta*valueAt(currentX);
				currentX += delta;
			}
			return integral;
		}
		
		// Setter
		public void setDelta(double deltaX) {
			if (deltaX > 0)
				delta = deltaX;
			else
				System.out.println("Delta must be greater than zero.");
		}
		
}
