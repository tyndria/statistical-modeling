package modelling.continuous;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.math3.random.RandomGenerator;

import modelling.Moments;

public class NormalGenerator extends Generator {
	RandomGenerator generator;
	int mathExpectation;
	int dispersion;
	int basicValuesNumber;
	
	public NormalGenerator(int m, int d, int n) {
		this.mathExpectation = m;
		this.dispersion = d;
		this.basicValuesNumber = n;
	}

	public void generate(int n) {
		Random random = new Random();
		
		generatedSequence = new ArrayList<>();
		for (int i = 0; i < n * basicValuesNumber; i += basicValuesNumber) {
			double sum = 0;
			for (int j = 0; j < basicValuesNumber; j ++) {
				sum += random.nextGaussian() * (double) dispersion + mathExpectation;
			}
			generatedSequence.add(((sum - basicValuesNumber * mathExpectation) / Math.sqrt(dispersion * basicValuesNumber)));
		}
	}

	@Override
	public double getRealMathExpectation() {
		return this.mathExpectation;
	}

	@Override
	public double getRealDispersion() {
		return this.dispersion;
	}
}
