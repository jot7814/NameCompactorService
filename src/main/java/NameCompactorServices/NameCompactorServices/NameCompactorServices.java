package NameCompactorServices.NameCompactorServices;

import java.text.Normalizer;
import java.util.regex.Pattern;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;


public class NameCompactorServices {
	
				// To support multi language add NORMALIZER method

	public static String normalizeName(String name)
	{
				// Normalize the name to NFKD form
		String normalized = Normalizer.normalize(name, Normalizer.Form.NFKD);
				// Remove diacritical marks
		Pattern pattern = Pattern.compile("\\p{M}");
		return pattern.matcher(normalized).replaceAll("");
	}
	
	
	
				// Method to calculate match percentage using Jaro-Winkler similarity
	public static double calculateMatchPercentage(String name1, String name2)
	{
		
		// Normalize the names before comparison
        name1 = normalizeName(name1);
        name2 = normalizeName(name2);
		
		
		JaroWinklerSimilarity jaroWinkler = new JaroWinklerSimilarity();
		return jaroWinkler.apply(name1, name2) * 100; // Convert to percentage
	}

	
	
	public static void main(String[] args) {
		
				// Example names to compare
				//Single value
		//String name1 = "John";
		//String name2 = "man";
		
		
				//Multi Name values
		String[][] namePairs = {
				{"John", "Jon"},
				{"Aman", "Raman"},
				{"ਅਲੈਕਸ", "ਅਮਨ"},
				{"موسى", "موسي"},
				{"ਅਮਨ", "ਅਮਨ"}
		};
		
		
				//For multi values
				// Loop through each pair of names and calculate match percentage
		for(String[] pair : namePairs)
		{
			String name1 = pair[0];
			String name2 = pair[1];
			
				//Calculate the match percentage
			double matchPercentage = calculateMatchPercentage(name1, name2);
			
			
				// Output the result
			System.out.printf("Comparing \"%s\" and \"%s\"%n", name1, name2);
	        System.out.printf("Match Percentage: %.2f%%%n", matchPercentage);
	        System.out.println(" "); // Blank
			
		}
		
		
		 		// FOR Single value  
				//Calculate the match percentage
		//double matchPercentage = calculateMatchPercentage(name1, name2);
		
		
				// Output the result
		//System.out.printf("Comparing \"%s\" and \"%s\"%n", name1, name2);
        //System.out.printf("Match Percentage: %.2f%%%n", matchPercentage);
        
        
        
        

	}

}
