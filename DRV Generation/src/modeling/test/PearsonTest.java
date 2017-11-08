package modeling.test;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

import modeling.Distribution;
import modeling.Utils;

public class PearsonTest {
	Utils utils = new Utils();
	private Map<Double, Integer> frequencyMap = new TreeMap();
	private ChiSquaredDistribution chiDistribution;
	double e = 0.05;
	
	public boolean run(List<Double> generatedSequence, Distribution distribution) {
		frequencyMap = countDistrubution(generatedSequence);
		int n = generatedSequence.size();
		double sqrtChi = frequencyMap.keySet().stream().mapToDouble((rv) -> {
			int freq = frequencyMap.get(rv);
			double p = distribution.probability((int)Math.round(rv));
			return Math.pow(freq - p * n, 2) / (p * n);
		}).sum();
		
		int degreesOfFreedom = frequencyMap.size() - 1;
		chiDistribution = new ChiSquaredDistribution(degreesOfFreedom);
		return (1 - chiDistribution.cumulativeProbability(sqrtChi)) < e;
	}
	
	public Map<Double, Integer> countDistrubution(List<Double> sample) {
		int barNumber = 100;
		SortedMap<Double, Integer> distribution = new TreeMap<Double, Integer>();
		double period = (double)1 / barNumber;
		for (int i = 0; i < barNumber; i ++) {
			long count = this.count((double)i * period, period, sample);
			distribution.put((double)i * period, (int)count);
		}
		
		return distribution;
	}
	
	private long count(double begin, double period, List<Double> sample) {
		return sample.stream().filter(value -> (value >= begin) && (value <= begin + period)).count();
	}
}

