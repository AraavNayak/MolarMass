import java.util.Arrays;
import java.util.Scanner;

public class LimitingReactantIdentifier {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String formula = in.next(); //must contain ->
		
		double[] masses = {40, 56};
		
		String[] reactants = formula.substring(0, formula.indexOf("->")).split("\\+");
		double[] moles = new double[reactants.length];
		
		for(int i = 0; i < moles.length; i++) {
			moles[i] = masses[i]/Main.getMolarMass(reactants[i]);
		}
		
		System.out.println(Arrays.toString(moles));
		int ind = 0;
		
		for(int i = 0; i < moles.length; i++) {
			if(moles[i] < moles[ind]) ind = i;
		}
		
		System.out.println("LR: " + reactants[ind]);
		
		
	}
}
