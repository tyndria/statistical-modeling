package modeling.binomial;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modeling.Moments;
import modeling.geometric.GeometricDRVGenerator;

public class BinomialDRVGenerator implements Constants{
	private List<Long> generatedSequence;
	private GeometricDRVGenerator geometricGenerator;
	private Moments moments = new Moments();
	int n;
	
	public BinomialDRVGenerator() {	
		generatedSequence = new ArrayList<Long>();
		geometricGenerator = new GeometricDRVGenerator();
	}
	
	public void generate(int n) {
		this.n = n;
		generatedSequence.clear();
		
		for (int i = 0; i < n; i ++) {
			generatedSequence.add(getDiscreteRV());
		}
	}
	
	public long get(int index) {
		return generatedSequence.get(index - 1);
	}
	
	public List getGeneratedSequence() {
		return this.generatedSequence;
	}
	
	public double getRealMathExpectation() {
		return M * P;
	}
	
	public double getMathExpectation() {
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getMathExpectation(sequence);
	}
	
	public double getRealDispersion() {
		return M * P * (1 - P);
	}
	
	public double getSkewness() {
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getSkewness(sequence);
	}
	
	public double getRealSkewness() {
		return (1 - 2 * P) / Math.sqrt(M * P * (1 - P));
	}
	
	public double getKurtosis() {
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getKurtosis(sequence);
	}
	
	public double getRealKurtosis() {
		return (1 - 6 * P * (1 - P)) / (M * P * (1 - P));
	}
	
	public double getDispersion() {
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		return moments.getDispersion(sequence, getMathExpectation());
	}
	
	public long getDiscreteRV() {	
		geometricGenerator.setP(P);
		geometricGenerator.generate(n);
		
		List<Long> bufGeneratedSequence = new ArrayList<>();

		long v = n;
		for (int i = 1; i < n + 1; i ++) {
			bufGeneratedSequence.add(geometricGenerator.get(i));
			if (bufGeneratedSequence.stream().mapToLong((e) -> e).sum() >= M) {
				v = i;
				break;
			}
		}
		return v - 1;
	}
}
