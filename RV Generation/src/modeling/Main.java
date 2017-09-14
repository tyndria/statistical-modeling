package modeling;

import modeling.mcgenerator.MCGenerator;
import modeling.mmgenerator.MMGenerator;
import modeling.test.CovariationTester;
import modeling.test.MomentsTester;

public class Main implements Constants{

	public static void main(String[] args) {
		MCGenerator mcGenerator = new MCGenerator(A_1, C_1);
		mcGenerator.generate(SEQUENCE_LENGTH);
		System.out.printf("multiplicative congruential method: %f, %f, %f", mcGenerator.get(100), mcGenerator.get(900), mcGenerator.get(1000));
		System.out.println("\n");
		
		MMGenerator mmGenerator = new MMGenerator(A_1, C_1, A_2, C_2, K, SEQUENCE_LENGTH);
		mmGenerator.generate(SEQUENCE_LENGTH);
		System.out.printf("MacLaren-Marsaglia method: %f, %f, %f", mmGenerator.get(100), mmGenerator.get(900), mmGenerator.get(1000));
		System.out.println("\n");
		
		MomentsTester momentsTester1 = new MomentsTester(SIGNIFICANCE_LEVEL, mmGenerator.getGeneratedSequence());
		System.out.println("First moment test for multiplicative congruential method: is passed? " + momentsTester1.testFirstMoment());
		System.out.println("Second moment test for multiplicative congruential method: is passed? " + momentsTester1.testSecondMoment() + "\n");
		
		MomentsTester momentsTester2 = new MomentsTester(SIGNIFICANCE_LEVEL, mcGenerator.getGeneratedSequence());
		System.out.println("First moment test for MacLaren-Marsaglia method: is passed? " + momentsTester2.testFirstMoment());
		System.out.println("Second moment test for MacLaren-Marsaglia method: is passed? " + momentsTester2.testSecondMoment() + "\n");
		
		CovariationTester covariationTester1 = new CovariationTester(SIGNIFICANCE_LEVEL, mcGenerator.getGeneratedSequence());
		System.out.println("Covariation test for multiplicative congruential method:");
		System.out.println(covariationTester1.test() + "\n");
		
		CovariationTester covariationTester2 = new CovariationTester(SIGNIFICANCE_LEVEL, mmGenerator.getGeneratedSequence());
		System.out.println("Covariation test for MacLaren-Marsaglia method:");
		System.out.println(covariationTester2.test() + "\n");
	}

}
