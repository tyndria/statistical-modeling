package modeling.test;

import java.util.List;

import org.apache.commons.math3.distribution.NormalDistribution;

public class MomentsTester {
	List<Double> sample;
	double significanceLevel;
	NormalDistribution nDistribution = new NormalDistribution();

	public MomentsTester(double significanceLevel, List sample) {
		this.sample = sample;
		this.significanceLevel = significanceLevel;
	}
	
	public boolean testFirstMoment() {
		return significanceLevel < this.getFirstMomentPropability();
	}
	
	public boolean testSecondMoment() {
		return significanceLevel < this.getDispersionProbability();
	}
	
	private double getFirstMomentDeviation() {
		double MATH_EXPECTATION = 0.5;
		return this.getAverageValue() - MATH_EXPECTATION;
	}
	
	private double getDispersionDeviation() {
		double averageValue = this.getAverageValue();
		double DISPERSION = (double)1 / 12;
		double x = sample.stream().reduce(0d, (Double prev, Double curr) -> prev + Math.pow(curr - averageValue, 2));
		return x / (sample.size() - 1) - DISPERSION;
	}
	
	private double getAverageValue() {
		return sample.stream().reduce(0d, (Double prev, Double curr) -> prev + curr) / sample.size();
	}
	
	private double getFirstMomentSelectionValue(int n) {
		return Math.sqrt(12 * n);
	}
	
	private double getDispersionSelectionValue(int n) {
		double x = 0.0056 * Math.pow(n, -1) +
				0.0028 * Math.pow(n, -2) -
				0.0083 * Math.pow(n, -3);
		return (n / (n-1)) * Math.pow(x, -0.5);
	}
	
	private double getFirstMomentPropability() {
		double deviation = this.getFirstMomentDeviation();
		double selectionValue = this.getFirstMomentSelectionValue(sample.size());
		return this.getPropability(deviation, selectionValue);
	}
	
	private double getDispersionProbability() {
		double deviation = this.getDispersionDeviation();
		double selectionValue = this.getDispersionSelectionValue(sample.size());
		return this.getPropability(deviation, selectionValue);
	}
	
	private double getPropability(double deviation, double selectionValue) {
		double x = selectionValue * Math.abs(deviation);
		return 2 * (1 - nDistribution.inverseCumulativeProbability(x));
	}
}
