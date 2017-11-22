package montecarlo;

import java.util.List;

public class MonteCarloMethod {

	public double calcIntegral(UnderIntegralFunction f, List<Double> rValues, double a, double b, int N) {
		double sum = rValues.stream().mapToDouble(x -> f.call(x)).sum();
		return (b - a) * sum / N;
	}
}

interface UnderIntegralFunction {
	double call(double x);
}
