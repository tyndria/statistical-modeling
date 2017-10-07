package modeling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Utils {
	private List<Double> sample;
	
	public Map<Double, Integer> countDistrubution(List<Double> sample) {
		this.sample = sample;
		int barNumber = 10;
		SortedMap<Double, Integer> distribution = new TreeMap<Double, Integer>();
		double period = (double)1 / barNumber;
		for (int i = 0; i < barNumber; i ++) {
			long count = this.count((double)i * period, period);
			distribution.put((double)(i + 1) * period, (int)count);
		}
		
		return distribution;
	}
	
	private long count(double begin, double period) {
		return sample.stream().filter(value -> (value >= begin) && (value <= begin + period)).count();
	}
	
	public void outputDistribution(Map<Double, Integer> distribution, String fileName) {
		try {
			PrintWriter printWriter = new PrintWriter(new File(fileName));
			distribution.keySet().forEach((key) -> {
				printWriter.println(key + "," + distribution.get(key));
			});
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
