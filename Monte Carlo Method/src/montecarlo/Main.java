package montecarlo;

import java.util.List;

import org.apache.commons.math3.distribution.CauchyDistribution;

import generator.CauchyGenerator;

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
		
		// For the second integral
		CauchyGenerator generator = new CauchyGenerator();
		CauchyDistribution cauchyD = new CauchyDistribution();
		generator.generate(N);
		List<Double> rValues = generator.getGeneratedSequence();
		double calculatedSecondIntegral = 
				mc.calcImproperIntegral((x) -> secondIntegral(x), rValues, (x) -> cauchyD.density(x), N); 
		System.out.println(calculatedSecondIntegral);
		
		// For the third integral
		List<Double> xValues = utils.generateUniformRV(-1, 1, N);
		List<Double> yValues = utils.generateUniformRV(-1, 1, N);
		double calculatedThirdIntegral = 
				mc.calcDoubleRegionIntegral((x) -> thirdIntegral(x), (x) -> integralRegion(x),
						xValues, yValues, N);
		System.out.println(calculatedThirdIntegral);
		
	}
	
	public static double firstIntegral(double[] x) {
		return Math.pow(x[0] * Math.cos(x[0]), 2);
	}
	
	public static double secondIntegral(double[] x) {
		return 1d / Math.pow(1 + x[0] + x[0] * x[0], 2);
	}
	
	public static double thirdIntegral(double[] x) {
		return Math.log(1 / Math.sqrt(x[0] * x[0] + x[1] * x[1]));
	}
	
	public static boolean integralRegion(double[] x) {
		return Math.pow(x[0], 2) + Math.pow(x[1], 2) < 1;
	}
}
