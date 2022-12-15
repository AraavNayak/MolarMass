package workInProgress;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MolarMass {

	static Scanner s;
	static String str;
	static boolean invalidInput = false;
	static ArrayList<String> items;
	static String[] elements;
	static int[] coeffs;
	static double[] molarMassOfIndividElements;
	static boolean hasParens = false;


	public static void main(String[] args) {
		double mass = 0;

		s = new Scanner(System.in);
		String str1 = s.nextLine(); 
		str = withoutParens(str1);
		hasParens = hasParens(str1);

		if(validParens(str)) {
			items = new ArrayList<>();
			for(int i = 0; i < str.length(); i++) {
				if(isUppercase(str.charAt(i))) {
					//System.out.println(stringUntilNextUppercase(str, i));
					items.add(stringUntilNextUppercase(str, i));
				}
			}
			if(items.size() == 0) invalidInput = true;

			elements = getElements(items);
			coeffs = getCoeffs(items);
			molarMassOfIndividElements = new double[elements.length];

			String fileName = "BBB.txt";

			File file;
			Scanner in;

			try {
				file = new File(fileName);

				for(int i = 0; i < items.size(); i++) {
					boolean eleFound = false;
					in = new Scanner(file);
					while(in.hasNext()) {
						String s1 = in.nextLine();
						if(s1.substring(0, s1.indexOf(" ")).equals(elements[i])) {
							molarMassOfIndividElements[i] = Double.parseDouble(s1.substring(s1.indexOf(" ")));
							eleFound = true;
							break;
						}
					}
					if(!eleFound) {
						System.out.println("Bruh...that's not even an element smh");
						invalidInput = true;
					}
				}

				for(int i = 0; i < molarMassOfIndividElements.length; i++) {
					mass += molarMassOfIndividElements[i] * coeffs[i];
				}

				
				//else System.out.println("Invalid input");

				//NEED A WAY TO DEAL WITH THE PARENS!!!!
				if(hasParens) {
					//get contents of parentheses --> NO3
					//process contents
					//output resulst
					//for(int j = str1.indexOf("(") + 1; j < str1.indexOf(")"); j++) {
					String s2 = str1.substring(str1.indexOf("(")+1, str1.indexOf(")"));






					double mass2 = 0;
					ArrayList<String> items2 = new ArrayList<>();
					for(int i = 0; i < s2.length(); i++) {
						if(isUppercase(s2.charAt(i))) {
							//System.out.println(stringUntilNextUppercase(str, i));
							items2.add(stringUntilNextUppercase(s2, i));
						}
					}
					//if(items.size() == 0) invalidInput = true;

					elements = getElements(items2);
					coeffs = getCoeffs(items2);
					molarMassOfIndividElements = new double[elements.length];




					file = new File(fileName);

					for(int i = 0; i < items2.size(); i++) {
						boolean eleFound = false;
						in = new Scanner(file);
						while(in.hasNext()) {
							String s1 = in.nextLine();
							if(s1.substring(0, s1.indexOf(" ")).equals(elements[i])) {
								molarMassOfIndividElements[i] = Double.parseDouble(s1.substring(s1.indexOf(" ")));
								eleFound = true;
								break;
							}
						}
						if(!eleFound) {
							System.out.println("Bruh...that's not even an element smh");
							invalidInput = true;
						}
					}

					for(int i = 0; i < molarMassOfIndividElements.length; i++) {
						mass2 += molarMassOfIndividElements[i] * coeffs[i];
					}



					
					mass += (numAfterParens(str1)-1) * mass2;



				
			}
				
			if(!invalidInput) System.out.println(mass);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	} else System.out.println("Invalid input: parentheses must match!");

}

public double getMass() {
	//returns the mass of a compound
	return 0;
}



public static boolean isUppercase(char c) {
	return c>=65 && c<=90;
}

public static String stringUntilNextUppercase(String str, int startIndex) {
	String ele = "" + str.charAt(startIndex);

	//H2SO4
	for(int i = startIndex+1; i < str.length(); i++) {
		if(isUppercase(str.charAt(i))) break;
		else {
			ele += "" + str.charAt(i);
		} 
	}
	return ele;
}


public static String[] getElements(ArrayList<String> str) {
	String[] elements = new String[str.size()];

	for(int i = 0; i < str.size(); i++) {
		String s = "";
		for(int j = 0; j < str.get(i).length(); j++) {
			if(Character.isDigit(str.get(i).charAt(j))) {
				break;
			} 
			s += "" + str.get(i).charAt(j);
		}
		elements[i] = s;
	}
	return elements;
}

public static int[] getCoeffs(ArrayList<String> str) {
	int[] coeffs = new int[str.size()];
	boolean cFound = false;

	for(int i = 0; i < str.size(); i++) {
		cFound = false;
		String s = "";
		for(int j = 0; j < str.get(i).length(); j++) {
			if(Character.isDigit(str.get(i).charAt(j))) {
				cFound = true;
				s += "" + str.get(i).substring(j);
				break;
			}
		}
		if(!cFound) coeffs[i] = 1;
		else coeffs[i] = Integer.parseInt(s);
	}
	return coeffs;
}

public static boolean validParens(String s) {
	int count = 0;
	boolean isBad = false;

	for(int i = 0; i < s.length(); i++) {
		if(s.charAt(i) == '(') count++;
		else if(s.charAt(i) == ')') count--;
		if(count < 0) isBad = true;
	} if(count != 0) isBad = true;

	return !isBad;
}

public static boolean hasParens(String str) {
	for(int i = 0; i < str.length(); i++) {
		if(str.charAt(i) == '(' || str.charAt(i) == ')') return true;
	} return false;
}

public static String withoutParens(String str) {
	String s = "";
	for(int i = 0; i < str.length(); i++) {
		if(!(str.charAt(i) == ')' || str.charAt(i) == '(')) {

			s += "" + str.charAt(i);
		} else if(str.charAt(i) == ')') break;

	}
	//System.out.println(s);
	return s;
}

public static int numAfterParens(String str) {
	for(int i = 0; i < str.length(); i++) {
		if(str.charAt(i) == ')') return Integer.parseInt(str.substring(i+1));
	}
	return 0;
}

}
