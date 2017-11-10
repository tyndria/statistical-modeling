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
	
	public double getCorrelationCoefficient(List<Double> s1, List<Double> s2) {
		int n = s1.size();
		double a = 0;
		
		double s1Mean = s1.stream().mapToDouble(e -> e).sum() / n;
		double s2Mean = s2.stream().mapToDouble(e -> e).sum() / n;
		
		for (int i = 0; i < n; i ++) {
			a += (s1.get(i) - s1Mean) * (s2.get(i) - s2Mean);
		}
		
		double sum1 = s1.stream().mapToDouble(e -> Math.pow(e - s1Mean, 2)).sum();
		double sum2 = s2.stream().mapToDouble(e -> Math.pow(e - s2Mean, 2)).sum();
		
		return a / Math.sqrt(sum1 * sum2);
	}
}
