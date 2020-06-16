package application;

public class Util {
	public static int qtTons(int hist[]) {
		
		int qt = 0;
		
		for(int i=0; i<hist.length; i++) {
			if (hist[i] > 0) {
				qt++;
			}
		}
		return qt;	
	}

	public static int[] histogramaAC(int hist[]) {
		int histAC[] = new int[hist.length];
		int histSoma = 0;
		
		for(int i=0; i<hist.length; i++) {
			histSoma += hist[i];
			histAC[i] = histSoma;
		}
		return histAC;
	}
	
	
}
