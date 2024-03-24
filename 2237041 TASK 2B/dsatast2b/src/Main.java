import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> suggestions;
    public static void main(String[] args) {
        // Creating a new SpellChecker with the file name of the dictionary file
        Trees spellChecker = new Trees("wordDictionary.txt");

        // Getting user input for a word to check
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a word to check or Enter \"esc\" to EXIT: ");
            String word = scanner.next().toLowerCase(); //putting the user input to the variable word
            System.out.println();
            if(!word.equals("esc")){
                // Checking if the word is spelled correctly
                if (Trees.checkWord(word)) {   // if word present we say word is correct
                    System.out.println("Spelling is correct for "+word);

                } else {
                    // Getting suggestions for the misspelled word
                    // and adding the suggestions from the spellchecker list to suggestions list
                    suggestions = Trees.gettingSuggestionsForWord(word);

                    if(suggestions.isEmpty()){ //if we cant find any possible matches
                        System.out.println("Could not find any common words");
                    }

                    else {
                        // Printing out the suggestions
                        System.out.println("The spelling of the word is incorrect. Here are some suggestions:");
                        for (String suggestion : suggestions) {  //printing each element
                            System.out.println(suggestion);
                        }
                    }

                }
            }
            else{ // if the user enters "esc" then the break statement is met and program ends
                System.out.println("Thank you for using the Spell Checker program");
                break;
            }

        }

    }
}
