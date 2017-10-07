package modeling.binomial;

import java.util.ArrayList;
import java.util.List;

import modeling.geometric.GeometricDRVGenerator;

public class BinomialDRVGenerator implements Constants{
	private List<Long> generatedSequence;
	private GeometricDRVGenerator geometricGenerator;
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
	
	/*public double getRealMathExpectation() {
		
	}
	
	public double getMathExpectation() {
		
	}
	
	public double getRealDispersion() {
		
	}
	
	public double getDispersion() {
		
	}*/
	
	public long getDiscreteRV() {	
		geometricGenerator.setP(P);
		geometricGenerator.generate(n);
		
		List<Long> bufGeneratedSequence = new ArrayList<>();

		long v = n;
		for (int i = 0; i < n; i ++) {
			bufGeneratedSequence.add(geometricGenerator.get(i + 1));
			if (bufGeneratedSequence.stream().mapToLong((e) -> e).sum() > M) {
				v = i;
				break;
			}
		}
		return v - 1;
	}
}
