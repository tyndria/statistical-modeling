package montecarlo;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Utils utils = new Utils();
		MonteCarloMethod mc = new MonteCarloMethod();
		int N = 5000;
		// For the first integral
		List<Double> values = utils.generateUniformRV(0, Math.PI, N);
		double calculatedIntegral = 
				mc.calcIntegral((x) -> firstIntegral(x), values, 0, Math.PI, N);
		System.out.println(calculatedIntegral);
		
		// For the third integral
		List<Double> xValues = utils.generateUniformRV(-1, 1, N);
		List<Double> yValues = utils.generateUniformRV(-1, 1, N);
		int regionPointsCount = 0;
		for (int i = 0; i < N; i ++) {
			if (Math.pow(xValues.get(i), 2) + Math.pow(yValues.get(i), 2) < 1) {
				regionPointsCount ++;
			}
		}
		
		double coeff = (double)regionPointsCount / N;
		
		double square = 1 * coeff;
		
		double sum = 0;
		for (int i = 0; i < N; i ++) {
			if (Math.pow(xValues.get(i), 2) + Math.pow(yValues.get(i), 2) < 1) {
				sum += thirdIntegral(xValues.get(i), yValues.get(i));
			}
		}
		double mean = sum / regionPointsCount;
		
		System.out.println(mean * square);
		
	}
	
	public static double firstIntegral(double x) {
		return Math.pow(x * Math.cos(x), 2);
	}
	
	public static double secondIntegral(double x) {
		return 1d / Math.pow(1 + x + x * x, 2);
	}
	
	public static double thirdIntegral(double x, double y) {
		return Math.log(1 / Math.sqrt(x * x + y *y));
	}
}
