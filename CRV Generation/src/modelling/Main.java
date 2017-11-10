package modelling;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("Normal distribution");
		//System.out.println(nGenerator.getGeneratedSequence());
		System.out.println(nGenerator.getMathExpectationComparison());
		System.out.println(nGenerator.getDispersionComparison() + '\n');
		
		int a = 2, b = 3;
		LogisticGenerator lGenerator = new LogisticGenerator(a, b);
		lGenerator.generate(n);
		System.out.println("Logistic distribution");
	   // System.out.println(lGenerator.getGeneratedSequence());
	    System.out.println(lGenerator.getMathExpectationComparison());
		System.out.println(lGenerator.getDispersionComparison() + '\n');
		
		int l = 5, m = 3;
		FisherGenerator fGenerator = new FisherGenerator(l, m);
		fGenerator.generate(n);
		System.out.println("Fisher distribution");
		//System.out.println(fGenerator.getGeneratedSequence());
		System.out.println(fGenerator.getMathExpectationComparison());
		System.out.println(fGenerator.getDispersionComparison() + '\n');
		
		double p = 0.6;
		MixtureDistributionGenerator mGenerator = new MixtureDistributionGenerator(lGenerator, nGenerator, 0.6);
		mGenerator.generate(n);
		System.out.println("Mixture distribution");
	   // System.out.println(mGenerator.getGeneratedSequence());
		System.out.println(mGenerator.getMathExpectationComparison());
		System.out.println(mGenerator.getDispersionComparison() + '\n');
		
		BoxMullerGenerator bGenerator = new BoxMullerGenerator();
		bGenerator.generate(n);
		System.out.println("Box-Muller distribution");
		//System.out.println(bGenerator.getGeneratedSequence());
		System.out.println(bGenerator.getMathExpectationComparison());
		System.out.println(bGenerator.getDispersionComparison() + '\n');
		
		
		List<Double> s1 = new ArrayList<>();
		List<Double> s2 = new ArrayList<>();
		
		List<Double> seq = bGenerator.getGeneratedSequence();
		for (int i = 0; i < seq.size(); i ++) {
			if (i % 2 == 0) {
				s1.add(seq.get(i));
			} else {
				s2.add(seq.get(i));
			}
		}
		
		System.out.println("Correlation Coefficient: " + bGenerator.getCorrelationCoefficient(s1, s2));
	}

}
