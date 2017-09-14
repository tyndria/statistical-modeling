package modeling.mcgenerator;

import java.util.ArrayList;
import java.util.List;

import modeling.Constants;

public class MCGenerator implements Constants {
	private List<Double> generatedSequence;
	
	private long CURR_A;
	private long PREV_A;
	private long B;
	
	public MCGenerator(long a, long c) {
		CURR_A = a;
		PREV_A = a;
		B = Math.max(c, M - c);
		
		generatedSequence = new ArrayList<Double>();
	}
	
	public void generate(int n) {
		generatedSequence.clear();
		for (int i = 0; i < n; i ++) {
			generatedSequence.add(generateRandomValue());
		}
	}
	
	public double get(int index) {
		return generatedSequence.get(index);
	}
	
	public List getGeneratedSequence() {
		return this.generatedSequence;
	}
	
	private double generateRandomValue() {
		CURR_A = this.mod(PREV_A * B);
		PREV_A = CURR_A;
		return (double) CURR_A / M;
	}
	
	private long mod(long x) {
		return x - M * (x / M);
	}
}
