package modelling;

import modelling.continuous.BoxMullerGenerator;
import modelling.continuous.FisherGenerator;
import modelling.continuous.LogisticGenerator;
import modelling.continuous.MixtureDistributionGenerator;
import modelling.continuous.NormalGenerator;

public class Main {

	public static void main(String[] args) {
		int n = 10000;
		int mean = 0;
		int dispersion = 1;
		int basicValuesNumber = 192; // 192, 48
		NormalGenerator nGenerator = new NormalGenerator(mean, dispersion, basicValuesNumber);
		nGenerator.generate(n);
		//System.out.println(nGenerator.getGeneratedSequence());
		System.out.println(nGenerator.getMathExpectationComparison());
		System.out.println(nGenerator.getDispersionComparison() + '\n');
		
		int a = 2, b = 3;
		LogisticGenerator lGenerator = new LogisticGenerator(a, b);
		lGenerator.generate(n);
	   // System.out.println(lGenerator.getGeneratedSequence());
	    System.out.println(lGenerator.getMathExpectationComparison());
		System.out.println(lGenerator.getDispersionComparison() + '\n');
		
		int l = 5, m = 3;
		FisherGenerator fGenerator = new FisherGenerator(l, m);
		fGenerator.generate(n);
		//System.out.println(fGenerator.getGeneratedSequence());
		System.out.println(fGenerator.getMathExpectationComparison());
		System.out.println(fGenerator.getDispersionComparison() + '\n');
		
		double p = 0.6;
		MixtureDistributionGenerator mGenerator = new MixtureDistributionGenerator(lGenerator, nGenerator, 0.6);
		mGenerator.generate(n);
	   // System.out.println(mGenerator.getGeneratedSequence());
		System.out.println(mGenerator.getMathExpectationComparison());
		System.out.println(mGenerator.getDispersionComparison() + '\n');
		
		BoxMullerGenerator bGenerator = new BoxMullerGenerator();
		bGenerator.generate(n);
		//System.out.println(bGenerator.getGeneratedSequence());
		System.out.println(bGenerator.getMathExpectationComparison());
		System.out.println(bGenerator.getDispersionComparison() + '\n');
	}

}
