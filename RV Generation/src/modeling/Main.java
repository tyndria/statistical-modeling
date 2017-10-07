package modeling;

import java.util.List;

import modeling.mcgenerator.MCGenerator;
import modeling.mmgenerator.MMGenerator;
import modeling.test.CovariationTester;
import modeling.test.MomentsTester;

public class Main implements Constants{

	public static void main(String[] args) {
		Utils utils = new Utils();
		
		MCGenerator mcGenerator = new MCGenerator(A_1, C_1);
		mcGenerator.generate(SEQUENCE_LENGTH);
		List mcGeneratedSequence = mcGenerator.getGeneratedSequence();
		System.out.printf("Multiplicative congruential method: %f, %f, %f \n", mcGenerator.get(1), mcGenerator.get(15), mcGenerator.get(1000));
		System.out.println("Distribution: " + utils.countDistrubution(mcGeneratedSequence) + "\n");
		utils.outputDistribution(utils.countDistrubution(mcGeneratedSequence), "mc.txt");
		
		MMGenerator mmGenerator = new MMGenerator(A_1, C_1, A_2, C_2, K, SEQUENCE_LENGTH);
		mmGenerator.generate(SEQUENCE_LENGTH);
		List mmGeneratedSequence = mmGenerator.getGeneratedSequence();
		System.out.printf("MacLaren-Marsaglia method: %f, %f, %f \n", mmGenerator.get(1), mmGenerator.get(15), mmGenerator.get(1000));
		System.out.println("Distribution: " + utils.countDistrubution(mmGeneratedSequence) + "\n");
		utils.outputDistribution(utils.countDistrubution(mmGeneratedSequence), "mm.txt");
		
		MomentsTester momentsTester = new MomentsTester(SIGNIFICANCE_LEVEL);
		System.out.println("First moment test for multiplicative congruential method: is passed? " + momentsTester.testFirstMoment(mcGeneratedSequence));
		System.out.println("Second moment test for multiplicative congruential method: is passed? " + momentsTester.testSecondMoment(mcGeneratedSequence) + "\n");
		
		System.out.println("First moment test for MacLaren-Marsaglia method: is passed? " + momentsTester.testFirstMoment(mmGeneratedSequence));
		System.out.println("Second moment test for MacLaren-Marsaglia method: is passed? " + momentsTester.testSecondMoment(mmGeneratedSequence) + "\n");
		
		CovariationTester covariationTester = new CovariationTester(SIGNIFICANCE_LEVEL);
		System.out.println("Covariation test for multiplicative congruential method:");
		System.out.println(covariationTester.test(mcGeneratedSequence) + "\n");
		
		System.out.println("Covariation test for MacLaren-Marsaglia method:");
		System.out.println(covariationTester.test(mmGeneratedSequence) + "\n");
	}

}
