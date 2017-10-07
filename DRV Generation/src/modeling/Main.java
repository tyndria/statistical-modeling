package modeling;

import modeling.geometric.GeometricDRVGenerator;

/*4) Geometric – G(p), p = 0.3; Binomial – Bi(m,p), m = 4, p = 0.2.*/

public class Main{

	public static void main(String[] args) {
		GeometricDRVGenerator geometricGenerator = new GeometricDRVGenerator();
		geometricGenerator.generate(1000);
		//System.out.println(geometricGenerator.getGeneratedSequence());
		//System.out.println(geometricGenerator.getRealMathExpectation());
		//System.out.println(geometricGenerator.getMathExpectation());
		System.out.println(geometricGenerator.getRealDispersion());
		System.out.println(geometricGenerator.getDispersion());
	}

}
