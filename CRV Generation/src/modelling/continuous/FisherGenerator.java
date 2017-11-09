package modelling.continuous;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.random.RandomGenerator;

import modelling.Moments;

public class FisherGenerator extends Generator {
	RandomGenerator generator;
	double mathExpectation;
	double dispersion;
	int l, m;
	Moments moments = new Moments();
	
	public FisherGenerator(int l, int m) {
		this.l = l;
		this.m = m;
		mathExpectation = m / (m - 2);
		dispersion = m > 4 ? 2 * Math.pow(m, 2) * (l + m - 2) / (l * (m - 4) * Math.pow(m - 2, 2)) : Double.NaN;
	}

	public void generate(int n) {
		generatedSequence = new ArrayList<>();
		for (int i = 0; i < n; i ++) {
			generatedSequence.add(getChiSquaredRV(l) * m / (getChiSquaredRV(m) * l));		
		}
	}
	
	private double getChiSquaredRV(int degreesOfFreedom) {
		Random random = new Random();
		
		double x = 0;
		for (int j = 0; j < degreesOfFreedom; j ++) {
			x += Math.pow(random.nextGaussian(), 2);
		}
		
		return x;
	}
	
	public double getDispersion() {
		if (m <= 4) {
			return Double.NaN;
		}
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getDispersion(sequence, getMathExpectation());
	}
	
	public double getRealMathExpectation() {
		return mathExpectation;
	}
	
	public double getRealDispersion() {
		return dispersion;
	}
}
