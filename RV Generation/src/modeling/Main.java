package modeling;

import modeling.mcgenerator.MCGenerator;
import modeling.mmgenerator.MMGenerator;
import modeling.test.MomentsTester;

public class Main implements Constants{

	public static void main(String[] args) {
		MMGenerator mmGenerator = new MMGenerator(A_1, C_1, A_2, C_2, K, SEQUENCE_LENGTH);
		mmGenerator.generate(SEQUENCE_LENGTH);
		
		MomentsTester momentsTester = new MomentsTester(SIGNIFICANCE_LEVEL, mmGenerator.getGeneratedSequence());
		System.out.println(momentsTester.testSecondMoment());
	}

}
