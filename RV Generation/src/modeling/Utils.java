package modeling;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Utils {
	private List<Double> sample;
	
	public Map<Double, Integer> countDistrubution(List<Double> sample) {
		this.sample = sample;
		SortedMap<Double, Integer> distribution = new TreeMap<Double, Integer>();
		double period = (double)1 / 10;
		for (int i = 0; i < 10; i ++) {
			long count = this.count((double)i * period, period);
			distribution.put((double)(i + 1) * period, (int)count);
		}
		
		return distribution;
	}
	
	private long count(double begin, double period) {
		return sample.stream().filter(value -> (value >= begin) && (value <= begin + period)).count();
	}
}
