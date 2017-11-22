package montecarlo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

	public List<Double> generateUniformRV(double a, double b, int N) {
		List<Double> values = new ArrayList<Double>();
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < N; i ++) {
			values.add(a + (b - a) * random.nextDouble());
		}
		return values;
	}
	
	
}
