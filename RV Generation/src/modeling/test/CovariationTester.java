package modeling.test;

import java.util.List;

import org.apache.commons.math3.distribution.UniformRealDistribution;

public class CovariationTester {
	int SAMPLE_MEAN = 30;
	double significanceLevel;
	List<Double> sample;
	UniformRealDistribution uDistribution;
	
	public CovariationTester(double significanceLevel, List sample) {
		this.sample = sample;
		this.significanceLevel = significanceLevel;
		this.uDistribution = new UniformRealDistribution();
	}
	
	public boolean test() {
		boolean isPassed = true;
		for (int i = 0; i < SAMPLE_MEAN; i ++) {
			double propability = this.getPropability(i);
			isPassed = significanceLevel < propability;
			System.out.println(i + ": " + isPassed + "; " + propability);
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
		for (int i = 1; i < (n - index); i ++) {
			sum += sample.get(index + 1); // ???
		}
		sum *= sample.get(1);
		sum /= (double)(n - index - 1);
		
		return sum - Math.pow(averageValue, 2) * (double)n / (n - 1);
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
		double distrProbability = uDistribution.cumulativeProbability(distrFuncArg);
		return 2 * (1 - uDistribution.cumulativeProbability(distrFuncArg));
	}
}
