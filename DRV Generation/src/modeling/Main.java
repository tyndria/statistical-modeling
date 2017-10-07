package modeling;

import modeling.binomial.BinomialDRVGenerator;
import modeling.geometric.GeometricDRVGenerator;

/*4) Geometric - G(p), p = 0.3; Binomial - Bi(m,p), m = 4, p = 0.2.*/

public class Main{

	public static void main(String[] args) {
		BinomialDRVGenerator binomialGenerator = new BinomialDRVGenerator();
		binomialGenerator.generate(1000);
		//System.out.println(geometricGenerator.getGeneratedSequence());
		//System.out.println(geometricGenerator.getRealMathExpectation());
		//System.out.println(geometricGenerator.getMathExpectation());
		System.out.println(binomialGenerator.getGeneratedSequence());
	}

}
