import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

    public class Trees {

        static ArrayList<String> suggestions = new ArrayList<>();

        private static Node root; //the root node

        // Binary search tree node
        private static class Node {
            String data;
            Node left, right;

            public Node(String data) {
                this.data = data;
                left = right = null;
            }
        }

        //============================================================================
        // Insert words into binary search tree
        private Node insert(Node node, String data) {
            if (node == null) {
                node = new Node(data);
                return node;
            }
            if (data.compareTo(node.data) < 0)
                node.left = insert(node.left, data);
            else if (data.compareTo(node.data) > 0)
                node.right = insert(node.right, data);
            return node;
        }

        //==========================================================================
        public Trees(String filename) {
            // Load words from file into binary search tree
            try {
                Scanner scanner = new Scanner(new File(filename));
                while (scanner.hasNext()) {
                    String word = scanner.next().toLowerCase();
                    root = insert(root, word);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found");
            }
        }

        //============================================================================
        // Check if word is spelled correctly
        public static boolean checkWord(String word) {
            return search(root, word.toLowerCase());
        }

        //============================================================================
        // Search for word in binary search tree
        private static boolean search(Node node, String data) {
            if (node == null)
                return false;
            if (data.equals(node.data))
                return true;
            else if (data.compareTo(node.data) < 0)
                return search(node.left, data);
            else
                return search(node.right, data);
        }

        //============================================================================
        // Check if a word can be formed by adding one letter to another word
        public static ArrayList<String> gettingSuggestionsForWord(String word) {
            suggestions.clear();
            // Generating Soundex code for the misspelled word
            String soundexCodeForUserWord = Soundex.gettingCodeForTheWord(word);

            // Generate suggestions by checking with the dictionary Soundex code for all the words
            Node node = root;
            while (node != null) {
                String dictionWord = node.data;
                // Generating Soundex code for the word in the dictionary file
                String dictionaryWordSoundex = Soundex.gettingCodeForTheWord(dictionWord);
                // If the Soundex codes match, that means common words and added to the list
                if (soundexCodeForUserWord.equals(dictionaryWordSoundex)) {
                    suggestions.add(dictionWord);
                }
                // If the Soundex code of the misspelled word is less than the Soundex code of the dictionary word, search the left subtree
                if (soundexCodeForUserWord.compareTo(dictionaryWordSoundex) < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }

            //case 1
            // Check if a word can be formed by adding one letter to another word
            for (int i = 0; i <= word.length(); i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    String newWord = word.substring(0, i) + c + word.substring(i);
                    if (checkWord(newWord)) {
                        boolean alreadyExists = false;
                        for (String suggestion : suggestions) {
                            if (suggestion.equals(newWord)) {
                                alreadyExists = true;
                                break;
                            }
                        }
                        if (!alreadyExists) {
                            suggestions.add(newWord);
                        }
                    }
                }
            }

            //case 2
            // Check if a word can be formed by deleting one letter from another word
            for (int i = 0; i < word.length(); i++) {
                String newWord = word.substring(0, i) + word.substring(i + 1);
                if (checkWord(newWord)) {
                    boolean alreadyExists = false;   // checking if the word is already added from the previous cases
                    for (String suggestion : suggestions) {
                        if (suggestion.equals(newWord)) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) {
                        suggestions.add(newWord);
                    }
                }
            }

            //case 3
            // Check if a word can be formed by switching adjacent letters in another word
            for (int i = 0; i < word.length() - 1; i++) {
                String newWord = word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2);
                if (checkWord(newWord)) {
                    boolean alreadyExists = false;      // checking if the word is already added from the previous cases
                    for (String suggestion : suggestions) {
                        if (suggestion.equals(newWord)) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) { // if the word is not there, then we add to our list
                        suggestions.add(newWord);
                    }
                }
            }
            return suggestions;

        }
    }






