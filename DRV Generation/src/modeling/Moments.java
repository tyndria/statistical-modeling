package modeling;

import java.util.List;

public class Moments {
	public double getMathExpectation(List<Double> generatedSequence) {
		return (double)generatedSequence.stream().reduce(0d, (Double prev, Double curr) -> prev + curr) / generatedSequence.size();
	}
	
	public double getDispersion(List<Double> generatedSequence, double mathExpectation) {
		double sqrtSum = generatedSequence.stream()
		         .mapToDouble(v -> Math.pow(v, 2))
		         .sum();
		double sqrtMathExpectation = (double)sqrtSum / generatedSequence.size();
		return (double)sqrtMathExpectation - Math.pow(mathExpectation, 2);
	}
}
