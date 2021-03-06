package montecarlo;

import java.util.ArrayList;
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
	
	public double calcDoubleRegionIntegral(UnderIntegralFunction integralFunction, IntegralRegionFunction isInsideRegion, List<Double> xValues, List<Double> yValues, int N) {
		int regionPointsCount = 0;
		double sum = 0;
		
		for (int i = 0; i < N; i ++) {
			if (isInsideRegion.call(xValues.get(i), yValues.get(i))) {
				regionPointsCount ++;
				
				sum += integralFunction.call(xValues.get(i), yValues.get(i));
			}
		}
		
		double coeff = (double)regionPointsCount / N;
		double square = 4d * coeff;
		double mean = sum / regionPointsCount;
		return mean * square;
	}
	
	public List<Double> calcLinearSystemSolution(double[][] a, double[] f, int n) {
		ArrayList<Double> solution = new ArrayList();
		double[][] h = {{1d, 0d, 0d}, {0d, 1d, 0d}, {0d, 0d, 1d}};
		for (int i = 0; i < n; i ++) {
			double x = calcVectorElement(a, f, n, h[i]);
			solution.add(x);
		}
		
		return solution;
	}
	
	private double calcVectorElement(double[][] a, double[] f, int n, double[] h) {
		int m = 1000;
		double[] initMarkovProb = new double[n];
		double[][] transitionMatrix = new double[n][];
		int[] markovChain = new int[m];
		double[] statesWeight = new double[m];
		double[] ksi = new double[m];

		// initialize markov inital probabilities and transition matrix
		for (int i = 0; i < n; i ++) {
			initMarkovProb[i] = 1d / n;
			
			transitionMatrix[i] = new double[n];
			for (int j = 0; j < n; j ++) {
				transitionMatrix[i][j] = 1d / n;
			}
		}
		
		// generate 1000 markov chain with length of 1000
		Random random = new Random();
		for (int i = 0; i < m; i ++) {

			for (int j = 0; j < m; j ++) {
				double randomValue = random.nextDouble();
				if (randomValue < initMarkovProb[0]) {
					markovChain[j] = 0;
				} else if (randomValue < (2 * initMarkovProb[0])) {
					markovChain[j] = 1;
				} else {
					markovChain[j] = 2;
				}
			}
			
			
			if (initMarkovProb[markovChain[0]] > 0) {
				statesWeight[0] = h[markovChain[0]] / initMarkovProb[markovChain[0]];
			} else {
				statesWeight[0] = 0d;
			}
			
			for (int j = 1; j < m; j ++) {
				if (transitionMatrix[markovChain[j - 1]][markovChain[j]] > 0) {
					double v1 = statesWeight[j - 1] * a[markovChain[j - 1]][markovChain[j]];
					double v2 = transitionMatrix[markovChain[j - 1]][markovChain[j]];
					double state = v1 / v2;
					statesWeight[j] = state;
				} else {
					statesWeight[j] = 0d;
				}
			}
			
			for (int j = 0; j < m; j ++) {
				double randomV = ksi[i] + statesWeight[j] * f[markovChain[j]];
				ksi[i] = randomV;
			}
		}
		
		double x = 0;
		
		for(int j = 0; j  < m; j ++) {
			x = x + ksi[j];
		}
		
		x = x / m;
		
		return x;
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

