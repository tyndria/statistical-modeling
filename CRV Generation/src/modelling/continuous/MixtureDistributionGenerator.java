package modelling.continuous;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.math3.random.RandomGenerator;

import modelling.Moments;

public class MixtureDistributionGenerator extends Generator {
	Generator g1, g2;
	double p;
	
	public MixtureDistributionGenerator(Generator a, Generator b, double p) {
		this.p = p;
		this.g1 = a;
		this.g2 = b;
	}

	public void generate(int n) {
		Random random = new Random();
		g1.generate(n);
		g2.generate(n);
		
		generatedSequence = new ArrayList<>();
		for (int i = 0; i < n; i ++) {
			generatedSequence.add(random.nextDouble() < p ? g1.get(i + 1) : g2.get(i + 1));
		}
	}
	
	public double getRealMathExpectation() {
		return g1.getRealMathExpectation() * p + (1 - p) * g2.getRealMathExpectation();
	}
	
	public double getRealDispersion() {
		double g1M = g1.getRealMathExpectation();
		double g2M = g2.getRealMathExpectation();
		double g1D = g1.getRealDispersion();
		double g2D = g2.getRealDispersion();
		return p * (Math.pow(g1M - getRealMathExpectation(), 2) + g1D) +
                (1 - p) * (Math.pow(g2M - getRealMathExpectation(), 2) + g2D);
	}
}
