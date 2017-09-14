package modeling.mmgenerator;

import java.util.ArrayList;
import java.util.List;

import modeling.Constants;
import modeling.mcgenerator.MCGenerator;

public class MMGenerator implements Constants{
	private MCGenerator mcGenerator1;
	private MCGenerator mcGenerator2;
	
	private List<Double> generatedSequence;
	private List<Double> randomValues;
	private int RANDOM_VALUES_LENGTH;
	
	public MMGenerator(long a1, long c1, long a2, long c2, int k, int n) {
		mcGenerator1 = new MCGenerator(a1, c1);
		mcGenerator2 = new MCGenerator(a2, c2);
		RANDOM_VALUES_LENGTH = k;
		
		mcGenerator1.generate(RANDOM_VALUES_LENGTH + n);
		randomValues = new ArrayList<Double>();
		for (int i = 0; i < RANDOM_VALUES_LENGTH; i ++) {
			randomValues.add(mcGenerator1.get(i));
		}
		
		mcGenerator2.generate(n);
		
		generatedSequence = new ArrayList<Double>();
	}
	
	public void generate(int n) {
		generatedSequence.clear();
		for (int i = 0; i < n; i ++) {
			int randomValuesIndex = (int)Math.floor(mcGenerator2.get(i) * RANDOM_VALUES_LENGTH);
			generatedSequence.add(randomValues.get(randomValuesIndex));
			
			randomValues.set(randomValuesIndex, mcGenerator1.get(i + RANDOM_VALUES_LENGTH));
		}
	}
	
	public List getGeneratedSequence() {
		return this.generatedSequence;
	}
	
	public double get(int index) {
		return generatedSequence.get(index);
	}
}
