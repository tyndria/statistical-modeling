package montecarlo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
	
	public List<Double> calcLinearSystemSolution(double[][] a, double[] f, int n) {
		List<Double> solution = new ArrayList<Double>(n);
		double[] initMarkovProb = new double[n];
		double[][] transitionMatrix = new double[n][];
		int[] markovChain = new int[100];
		double[] statesWeight = new double[100];
		ArrayList<Double> h = new ArrayList<Double>(Arrays.asList(1d, 0d, 0d));
		double[] ksi = new double[100];
	
		// initialize markov inital probabilities and transition matrix
		for (int i = 0; i < n; i ++) {
			initMarkovProb[i] = 1d / n;
			
			transitionMatrix[i] = new double[n];
			for (int j = 0; j < n; j ++) {
				transitionMatrix[i][j] = 1d / n;
			}
		}
		
		// generate 100 markov chain with length of 100
		Random random = new Random();
		for (int i = 0; i < 100; i ++) {

			for (int j = 0; j < 100; j ++) {
				double randomValue = random.nextDouble();
				if (randomValue < initMarkovProb[0]) {
					markovChain[j] = 0;
				} else if (randomValue < 2 * initMarkovProb[0]) {
					markovChain[j] = 1;
				} else {
					markovChain[j] = 2;
				}
			}
			
			
			if (initMarkovProb[markovChain[0]] > 0) {
				statesWeight[0] =  h.get(markovChain[0]) / initMarkovProb[markovChain[0]];
			} else {
				statesWeight[0] = 0d;
			}
			
			for (int j = 1; j < 100; j ++) {
				if (transitionMatrix[markovChain[j - 1]][markovChain[j]] > 0) {
					double v1 = statesWeight[j - 1] * a[markovChain[j - 1]][markovChain[j]];
					double v2 = transitionMatrix[markovChain[j - 1]][markovChain[j]];
					double state = v1 / v2;
					statesWeight[j] = state;
				} else {
					statesWeight[j] = 0d;
				}
			}
			
			for (int j = 1; j < 100; j ++) {
				double randomV = ksi[i] + statesWeight[j] * f[markovChain[j]];
				ksi[i] = randomV;
			}
		}
		
		double x = 0;
		
		for(int j = 0; j  < 100; j ++) {
			x = x + ksi[j];
		}
		
		x = x / 100;
		System.out.println(x);
		
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

