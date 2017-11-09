package modelling.continuous;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import modelling.Moments;

public class BoxMullerGenerator extends Generator {
	Moments moments = new Moments();
	
	public void generate(int n) {
		Random random = new Random();
		
		generatedSequence = new ArrayList<>();
		for (int i = 0; i < n; i += 2) {
			double r = random.nextDouble();
			double fi = random.nextDouble();
			
			generatedSequence.add(getValueCos(r, fi));
			generatedSequence.add(getValueSin(r, fi));
		}
	}
	
	private double getValueCos(double r, double fi) {
		return Math.cos(2 * Math.PI * fi) * Math.sqrt(-2 * Math.log(r));
	}
	
	private double getValueSin(double r, double fi) {
		return Math.sin(2 * Math.PI * fi) * Math.sqrt(-2 * Math.log(r));
	}

	@Override
	public double getRealMathExpectation() {
		return 0;
	}

	@Override
	public double getRealDispersion() {
		return 1;
	}
}
