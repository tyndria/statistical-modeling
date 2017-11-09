package modelling.continuous;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.math3.random.RandomGenerator;

import modelling.Moments;

public class LogisticGenerator extends Generator {
	RandomGenerator generator;
	double mathExpectation;
	double dispersion;
	int k;
	
	public LogisticGenerator(int u, int k) {
		mathExpectation = u;
		this.k = k;
		dispersion = Math.pow(Math.PI * k, 2) / 3d; 
	}

	public void generate(int n) {
		Random random = new Random(System.currentTimeMillis());
		
		generatedSequence = new ArrayList<>();
		for (int i = 0; i < n; i ++) {
			double rv = random.nextDouble();
			generatedSequence.add(mathExpectation + k * Math.log(rv / (1 - rv)));
		}
	}
	
	public double getRealMathExpectation() {
		return mathExpectation;
	}
	
	public double getRealDispersion() {
		return dispersion;
	}
}
