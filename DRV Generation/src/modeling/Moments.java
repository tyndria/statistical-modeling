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
	
	public double getSkewness(List<Double> generatedSequence) {
		double mean = this.getMathExpectation(generatedSequence);
		double dispersion = this.getDispersion(generatedSequence, mean);
		double nonCentSecondMoment = getNonCentralMoment(generatedSequence, 2);
		double nonCentThirdMoment = getNonCentralMoment(generatedSequence, 3);
		
		return (nonCentThirdMoment - 3 * mean * nonCentSecondMoment + 2 * Math.pow(mean, 3)) / Math.pow(dispersion, (double)3/2);
	}
	
	public double getNonCentralMoment(List<Double> generatedSequence, int power) {
		double sum = generatedSequence.stream()
		         .mapToDouble(v -> Math.pow(v, power))
		         .sum();
		return (double) sum / generatedSequence.size();
	}
	
	public double getKurtosis(List<Double> generatedSequence) {
		double mean = this.getMathExpectation(generatedSequence);
		double dispersion = this.getDispersion(generatedSequence, mean);
		double nonCentSecondMoment = getNonCentralMoment(generatedSequence, 2);
		double nonCentThirdMoment = getNonCentralMoment(generatedSequence, 3);
		double nonCentForthMoment = getNonCentralMoment(generatedSequence, 4);
		
		double kurtosis = (nonCentForthMoment - 4 * mean * nonCentThirdMoment + 6 * Math.pow(mean, 2) * nonCentSecondMoment - 3 * Math.pow(mean, 4)) / Math.pow(dispersion, 2);
		return kurtosis - 3;
	}
}
