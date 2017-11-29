package montecarlo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
		System.out.println("First integral: " + calculatedIntegral + "\n");
		
		// For the second integral
		CauchyGenerator generator = new CauchyGenerator();
		CauchyDistribution cauchyD = new CauchyDistribution();
		generator.generate(N);
		List<Double> rValues = generator.getGeneratedSequence();
		double calculatedSecondIntegral = 
				mc.calcImproperIntegral((x) -> secondIntegral(x), rValues, (x) -> cauchyD.density(x), N); 
		System.out.println("Second integral: " + calculatedSecondIntegral + "\n");
		
		// For the third integral
		List<Double> xValues = utils.generateUniformRV(-1, 1, N);
		List<Double> yValues = utils.generateUniformRV(-1, 1, N);
		double calculatedThirdIntegral = 
				mc.calcDoubleRegionIntegral((x) -> thirdIntegral(x), (x) -> integralRegion(x),
						xValues, yValues, N);
		System.out.println("Third integral: " + calculatedThirdIntegral + "\n");
		
		// Linear system
		/* Real solution: { x = 0.03644144144144146, y = 0.03653153153153154, z = 0.03734234234234233 }.*/
		
		double[][] a = {{-0.35, 0, -0.45}, {-0.6, 0, -0.4}, {-0.333, -0.333, 0}};
		double[] f = {0.066666, 0.07407, 0.061727};	
		
		Double[] linearSystemSolution = mc.calcLinearSystemSolution(a, f, 3).toArray(new Double[3]);
		System.out.println("Linear system solution: " + Arrays.toString(linearSystemSolution));
		double discrepancy = utils.countDiscrepancy(a, f, 3, linearSystemSolution);
		System.out.println("Discrepancy :( " + discrepancy);
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
		return (Math.pow(x[0], 2) + Math.pow(x[1], 2)) < 1;
	}
}
