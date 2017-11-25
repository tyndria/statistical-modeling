package montecarlo;

import java.util.ArrayList;
import java.util.List;

public class MonteCarloMethod {

	public double calcIntegral(UnderIntegralFunction f, List<Double> rValues, double a, double b, int N) {
		double sum = rValues.stream().mapToDouble(x -> f.call(x)).sum();
		return (b - a) * sum / N;
	}
	
	public double calcImproperIntegral(UnderIntegralFunction underF, List<Double> rValues, DensityFunction densityF, int N) {
		double sum = rValues.stream().mapToDouble(x -> underF.call(x) / densityF.call(x)).sum();
		return sum / N;
	}
	
	public double calcDoubleRegionIntegral(UnderIntegralFunction integralF, IntegralRegionFunction regionF, List<Double> xValues, List<Double> yValues, int N) {
		int regionPointsCount = 0;
		double sum = 0;
		
		for (int i = 0; i < N; i ++) {
			if (regionF.call(xValues.get(i), yValues.get(i))) {
				regionPointsCount ++;
				
				sum += integralF.call(xValues.get(i), yValues.get(i));
			}
		}
		
		double coeff = (double)regionPointsCount / N;
		double square = 1d * coeff;
		double mean = sum / regionPointsCount;
		return mean * square;
	}
	
	public List<Double> calcLinearSystemSolution(List<List<Double>> a, List<Double> f) {
		List<Double> solution = new ArrayList<Double>();
		List<Double> initMarkovProb = new ArrayList<Double>();
		ArrayList<ArrayList<Double>> transitionMatrix = new ArrayList<ArrayList<Double>>();
		List<Double> markovChain = new ArrayList<Double>();
		List<Double> statesWeight = new ArrayList<Double>();
		
		
		return solution;
	}
}

interface UnderIntegralFunction {
	double call(double ...x);
}

interface DensityFunction {
	double call(double x);
}

interface IntegralRegionFunction {
	boolean call(double ...x);
}

