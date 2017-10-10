package modeling;

import java.util.List;

public class EmpiricalDistribution {

	public double count(double x, List<Double> sample) {
		return sample.stream().mapToInt((v) -> I(v, x)).sum() / (double)sample.size();
	}
	
	private int I(double currentValue, double value) {
		return currentValue < value ? 1 : 0;
	}
}
