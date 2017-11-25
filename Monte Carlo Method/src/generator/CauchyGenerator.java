package generator;

import java.util.ArrayList;
import java.util.Random;

public class CauchyGenerator extends Generator {

	public void generate(int n) {
		Random r1 = new Random(System.currentTimeMillis());
		Random r2 = new Random(System.currentTimeMillis() + 1000);
		
		generatedSequence = new ArrayList<>();
		for (int i = 0; i < n; i ++) {
			generatedSequence.add(r1.nextGaussian() / r2.nextGaussian());
		}
	}

	@Override
	public double getRealMathExpectation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRealDispersion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
