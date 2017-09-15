package modeling.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.distribution.NormalDistribution;


public class CovariationTester {
	int SAMPLE_MEAN = 30;
	double significanceLevel;
	List<Double> sample;
	NormalDistribution uDistribution;
	
	List<Double> covariations  = new ArrayList<Double>();
	
	public CovariationTester(double significanceLevel) {
		this.significanceLevel = significanceLevel;
		this.uDistribution = new NormalDistribution();
	}
	
	public boolean test(List sample) {
		this.sample = sample;
		boolean isPassed = true;
		for (int i = 0; i < SAMPLE_MEAN; i ++) {
			double propability = this.getPropability(i);
			isPassed = significanceLevel < propability;
			if (!isPassed ) {
				System.out.println("Failed: index = " + i);
			}
		}
		
		return isPassed;
	}
	
	private double getAverageValue() {
		return sample.stream().reduce(0d, (Double prev, Double curr) -> prev + curr) / sample.size();
	}
	
	private double getCovariationEstimation(int index) {
		double sum = 0;
		int n = sample.size();
		double averageValue = this.getAverageValue();
		for (int i = 0; i <= (n - index - 1); i ++) {
			double production = sample.get(index + i) * sample.get(i);
			sum += production;
		}
		sum /= (double)(n - index - 1);
		
		double res = sum - Math.pow(averageValue, 2) * (double)n / (n - 1);
		covariations.add(res);
		return res;
	}
	
	private double getCovaritationSelectionValue(int index) {
		return index >= 1 ? 1 : Math.sqrt(2);
	}
	
	private double getCovariation(int index) {
		return index > 0 ? 0 : (double)1 / 12;
	}
	
	private double getPropability(int index) {
		double covariationEstimation = this.getCovariationEstimation(index) - this.getCovariation(index);
		double distrFuncArg = 12 * Math.sqrt(sample.size() - 1) * Math.abs(covariationEstimation) / this.getCovaritationSelectionValue(index);
		return 2 * (1 - uDistribution.cumulativeProbability(distrFuncArg));
	}
}
