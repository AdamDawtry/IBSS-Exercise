package words;

import java.util.HashSet;
import java.util.List;

public class Words {
    public static List<String> getUniqueWordsFromSentence(String sentence) {
    	// Assuming all test cases verify words with lower case
    	String lowerS = sentence.toLowerCase();
    	
    	// Split on all sequences of non-alphabetical characters, barring apostrophes
    	String[] splitWords = lowerS.split("[^a-zA-Z']+");
    	
    	// Convert to intermediate HashSet to remove duplicates
    	HashSet<String> a = new HashSet<String>(List.of(splitWords));
    	
    	// Convert back to a List for final return value
    	List<String> ans = List.of(a.toArray(new String[0]));
        return ans;
    }
}
