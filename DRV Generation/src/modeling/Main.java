package modeling;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import modeling.binomial.BinomialDRVGenerator;
import modeling.geometric.GeometricDRVGenerator;
import modeling.test.PearsonTest;
import org.apache.commons.math3.distribution.GeometricDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;

/*4) Geometric - G(p), p = 0.3; Binomial - Bi(m,p), m = 4, p = 0.2.*/

public class Main{

	public static void main(String[] args) {
		PearsonTest test = new PearsonTest();
		GeometricDistribution geometricDistribution = new GeometricDistribution(modeling.geometric.Constants.P);
		BinomialDistribution binomialDistribution = new BinomialDistribution(modeling.binomial.Constants.M, modeling.binomial.Constants.P);
		
		EmpiricalDistribution empiricalDistribution = new EmpiricalDistribution();
		
		GeometricDRVGenerator geomGenerator = new GeometricDRVGenerator();
		BinomialDRVGenerator binomGenerator = new BinomialDRVGenerator();
		geomGenerator.generate(10000);
		List<Long> generatedSequence = geomGenerator.getGeneratedSequence();
		List<Double> geomSequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		
		binomGenerator.generate(10000);
		generatedSequence = binomGenerator.getGeneratedSequence();
		List<Double> binomSequence = generatedSequence.stream().map(Double::valueOf).collect(Collectors.toList());
		
		System.out.println("geometric: ");
		System.out.println(geomGenerator.getGeneratedSequence());
		System.out.println(geomGenerator.getRealDispersion() + " (real dispersion) : " + geomGenerator.getDispersion());
		System.out.println(geomGenerator.getRealMathExpectation() + " (real expectation) : " + geomGenerator.getMathExpectation());
		System.out.println(geomGenerator.getRealSkewness() + " (real skewness) : " + geomGenerator.getSkewness());
		System.out.println(geomGenerator.getRealKurtosis() + " (real kurtosis) : " + geomGenerator.getKurtosis());
		
		
		System.out.println("\n binomial: ");
		System.out.println(binomGenerator.getGeneratedSequence());
		System.out.println(binomGenerator.getRealDispersion() + " (real dispersion) : " + binomGenerator.getDispersion());
		System.out.println(binomGenerator.getRealMathExpectation() + " (real expectation) : " +  binomGenerator.getMathExpectation());
		System.out.println(binomGenerator.getRealSkewness() + " (real skewness) : " + binomGenerator.getSkewness());
		System.out.println(binomGenerator.getRealKurtosis() + " (real kurtosis) : " + binomGenerator.getKurtosis());
		
		System.out.println("\n Pearson tests:");
		System.out.println("Geometric: " + test.run(geomSequence, 
				(double v) -> geometricDistribution.probability((int)v)));
		System.out.println("Binomial: " + test.run(binomSequence, 
				(double v) -> binomialDistribution.probability((int)v)));

		
		int[] sample = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		Map<Integer, Double> probabilities = new TreeMap();
		for(int i = 0; i < sample.length; i ++) {
			//probabilities.put(sample[i], binomialDistribution.cumulativeProbability(sample[i]));
			probabilities.put(sample[i], empiricalDistribution.count((double)sample[i], binomSequence));
		}
		System.out.println(probabilities);
	}

}
 