package modeling.geometric;

import java.util.ArrayList;
import java.util.List;

import modeling.mcgenerator.MCGenerator;

public class GeometricDRVGenerator implements Constants{
	private List<Long> generatedSequence;
	private MCGenerator mcGenerator;
	
	public GeometricDRVGenerator() {	
		generatedSequence = new ArrayList<Long>();
		mcGenerator = new MCGenerator(modeling.Constants.A_1, modeling.Constants.C_1);
	}
	
	public void generate(int n) {
		generatedSequence.clear();
		mcGenerator.generate(n);

		for (int i = 0; i < n; i ++) {
			generatedSequence.add(getDiscreteRV(mcGenerator.get(i + 1)));
		}
	}
	
	public long get(int index) {
		return generatedSequence.get(index - 1);
	}
	
	public List getGeneratedSequence() {
		return this.generatedSequence;
	}
	
	public double getRealMathExpectation() {
		return 1d / P;
	}
	
	public double getMathExpectation() {
		return (double)generatedSequence.stream().reduce((long)0, (Long prev, Long curr) -> prev + curr) / generatedSequence.size();
	}
	
	public double getRealDispersion() {
		return (double)Q / Math.pow(P, 2);
	}
	
	public double getDispersion() {
		long sqrtSum = generatedSequence.stream()
		         .mapToLong(v -> (long) Math.pow(v, 2))
		         .sum();
		double sqrtMathExpectation = (double)sqrtSum / generatedSequence.size();
		return (double)sqrtMathExpectation - Math.pow(getMathExpectation(), 2);
	}
	
	private long getDiscreteRV(double rv) {
		return Math.round(Math.log10(rv) / Math.log10(Q));
	}
	
}
