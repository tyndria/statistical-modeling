package modeling;

import java.util.List;
import java.util.stream.Collectors;

import modeling.binomial.BinomialDRVGenerator;
import modeling.geometric.GeometricDRVGenerator;
import modeling.test.PearsonTest;

/*4) Geometric - G(p), p = 0.3; Binomial - Bi(m,p), m = 4, p = 0.2.*/

public class Main{

	public static void main(String[] args) {
		PearsonTest test = new PearsonTest();
		
		GeometricDRVGenerator generator = new GeometricDRVGenerator();
		BinomialDRVGenerator binomialGenerator = new BinomialDRVGenerator();
		generator.generate(1000);
		List<Long> generatedSequence = generator.getGeneratedSequence();
		List<Double> sequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		System.out.println(test.run(sequence));
		//System.out.println(geometricGenerator.getGeneratedSequence());
		//System.out.println(binomialGenerator.getRealDispersion());
		//System.out.println(binomialGenerator.getDispersion());
	}

}
