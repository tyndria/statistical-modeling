package generator;

import java.util.List;
import java.util.stream.Collectors;


public abstract class Generator {
	List<Double> generatedSequence;
	Moments moments = new Moments();
	
	public abstract void generate(int n);
	
	public List getGeneratedSequence() {
		return this.generatedSequence;
	}
	
	public double get(int index) {
		return generatedSequence.get(index - 1);
	}
	
	public abstract double getRealMathExpectation();
	
	public abstract double getRealDispersion();
	
	public double getMathExpectation() {
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getMathExpectation(sequence);
	}
	
	public double getDispersion() {
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getDispersion(sequence, getMathExpectation());
	}
	
	public String getMathExpectationComparison() {
		return String.format("%.10f", getMathExpectation()) + ": " + getRealMathExpectation();
	}

	public String getDispersionComparison() {
		return String.format("%.10f", getDispersion()) + ": " + getRealDispersion();
	}
}
