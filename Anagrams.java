import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Anagrams {
    // using word alphabetically sorted by characters as anagram key
    // maps anagram key to list of anagrams from the dictionary file
    private static HashMap<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();

    private static void processDictionary(ArrayList<String> dictionaryArray) {
        for(String word : dictionaryArray) {
            // use word alphabetically sorted by characters as anagram key
            char[] wordArray = word.toCharArray();
            Arrays.sort(wordArray);
            String anagramKey = new String(wordArray);

            // if key does not yet exists in dictionary, initialize new arraylist
            if(dictionary.get(anagramKey) == null) {
                ArrayList<String> anagrams = new ArrayList<String>();
                anagrams.add(word);
                dictionary.put(anagramKey, anagrams);

            // else append to existing list of anagrams
            }else {
                ArrayList<String> anagrams = dictionary.get(anagramKey);
                sortedAnagrams(anagrams, word);
            }
        }
    }

    private static void findAnagrams(String word){
        //exit the program if no word is supplied
        if (word.isEmpty()){
            System.exit(1);
        }
        char[] wordArray = word.toCharArray();
        Arrays.sort(wordArray);
        String anagramKey = new String(wordArray);

        // no anagrams found, print "-"
        if (dictionary.get(anagramKey) == null) {
            System.out.println("-");

        //print out all anagrams from dictionary
        }else{
            ArrayList<String> anagrams = dictionary.get(anagramKey);
            for (int i = 0; i < anagrams.size()-1; i++){
                System.out.print(anagrams.get(i) + " ");
            }
            System.out.print(anagrams.get(anagrams.size() - 1) + "\r\n");
        }
    }

    private static void sortedAnagrams(ArrayList<String> anagrams, String newWord){
        //insert new word into the right lexicographical position
        int index = Collections.binarySearch(anagrams, newWord);
        if (index < 0) {
            index = -index - 1;
        }
        anagrams.add(index, newWord);
    }

    public static void main(String[] args) throws FileNotFoundException{
        if (args.length == 0) {
            System.out.println("Please supply dictionary file");
        }else {
            try {
                String filepath = args[0];
                File dictionaryFile = new File(filepath);

                Scanner scanner = new Scanner(dictionaryFile);
                ArrayList<String> dictionaryArray = new ArrayList<String>();
                while(scanner.hasNextLine()){
                    String newWord = scanner.nextLine().toLowerCase();
                    dictionaryArray.add(newWord);
                }
                processDictionary(dictionaryArray);
                System.out.println("Enter word to find anagrams: ");
                Scanner wordInput = new Scanner(System.in);
                while(wordInput.hasNextLine()) {
                    String word = wordInput.nextLine().toLowerCase();
                    findAnagrams(word);
                }

            } catch (FileNotFoundException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
