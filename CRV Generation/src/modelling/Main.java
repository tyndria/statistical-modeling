package modelling;

import modelling.continuous.FisherGenerator;
import modelling.continuous.LogisticGenerator;
import modelling.continuous.NormalGenerator;

public class Main {

	public static void main(String[] args) {
		int n = 10000;
		int mean = 0;
		int dispersion = 1;
		int basicValuesNumber = 192; // 192, 48
		NormalGenerator nGenerator = new NormalGenerator();
		nGenerator.generate(n, mean, dispersion, basicValuesNumber);
		System.out.println(nGenerator.getGeneratedSequence());
		System.out.println(String.format("%.10f", nGenerator.getMathExpectation()));
		System.out.println(String.format("%.10f", nGenerator.getDispersion()));
		
		int a = 2, b = 3;
		LogisticGenerator lGenerator = new LogisticGenerator(a, b);
		lGenerator.generate(n);
		System.out.println(lGenerator.getGeneratedSequence());
		System.out.println(String.format("%.10f", lGenerator.getMathExpectation()) + ": " + lGenerator.getRealMathExpectation());
		System.out.println(String.format("%.10f", lGenerator.getDispersion()) + ": " + lGenerator.getRealDispersion());
		
		// Not working yet, dispersion in wrong
		int l = 5, m = 3;
		FisherGenerator fGenerator = new FisherGenerator(l, m);
		fGenerator.generate(n);
		System.out.println(fGenerator.getGeneratedSequence());
		System.out.println(String.format("%.10f", fGenerator.getMathExpectation()) + ": " + fGenerator.getRealMathExpectation());
		System.out.println(String.format("%.10f", fGenerator.getDispersion()) + ": " + fGenerator.getRealDispersion());
	}

}
