package words;

import java.util.HashSet;
import java.util.List;

public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {
    	// Assuming all test cases verify words with lower case
    	String lowerS = sentence.toLowerCase();
    	
    	// Splits on all non alphabetical characters - will break up contracted words with
    	// apostrophes, e.g. "Isn't" or any possessive, e.g. "Adam's"; unsure if
    	// this is desired behaviour for the problem
    	String[] splitWords = lowerS.split("\\P{Alpha}+");
    	
    	// Convert to intermediate HashSet to remove duplicates
    	HashSet<String> a = new HashSet<String>(List.of(splitWords));
    	
    	// Convert back to a List for final return value
    	List<String> ans = List.of(a.toArray(new String[0]));
        return ans;
    }
}
