package modelling.continuous;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.math3.random.RandomGenerator;

import modelling.Moments;

public class NormalGenerator {
	RandomGenerator generator;
	int mathExpectation;
	int dispersion;
	List<Double> generatedSequence;
	Moments moments = new Moments();

	public void generate(int n, int mathExpectation, int dispersion, int basicValuesNumber) {
		Random random = new Random();
		
		generatedSequence = new ArrayList<>();
		for (int i = 0; i < n;) {
			double sum = 0;
			for (int j = 0; j < basicValuesNumber; j ++) {
				sum += random.nextGaussian() * (double) dispersion + mathExpectation;
			}
			i += basicValuesNumber;
			generatedSequence.add(((sum - basicValuesNumber * mathExpectation) / Math.sqrt(dispersion * basicValuesNumber)));
		}
	}

	public List getGeneratedSequence() {
		return this.generatedSequence;
	}
	
	public double get(int index) {
		return generatedSequence.get(index - 1);
	}
	
	public double getMathExpectation() {
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getMathExpectation(sequence);
	}
	
	public double getDispersion() {
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getDispersion(sequence, getMathExpectation());
	}
}
