import java.util.*;
import java.io.*;
import java.lang.Math;

/**
 * This is the driver class for the the program. The logic that runs based on user input are all in this class.
 * The Breadth First Search is in the main method in a while loop that uses a queue to check the conditions.
 * @author Wei Zhong Tee
 * @since 8 May 2020
 */


public class Game{
	private static int hops;
	private static String oriWord;
	private static String goalWord;
	private static int depth;
	private static QueueList <TNode> queue = new QueueList <TNode>();
	private static TNode ori;
	private static TNode current;
	private static TNode parent;
	private static HashChain<String, String> usedWords = new HashChain<String, String>(13);
	private static ArrayList <String> steps = new ArrayList<String>();

	/**
	 * This is the main method
	 * @param args [description]
	 */

	public static void main(String[] args) {
		HashChain<String, String> hash = new HashChain<String, String>(13);

		if (args.length == 3) {
			if (args[0].length() != args[1].length()) {
				System.out.println("Words are not the same length or not in dictionary.");
				System.exit(0);	// End gracefully.
			}

			try {
				hops = Integer.valueOf(args[2]);
			} catch(Exception e) {
				System.out.println("The third argument passed was not an integer.");
				System.exit(0);	// End gracefully.
			}
		}
		else{
			System.out.println("Invalid number of arguments passed. Ending.");
			System.exit(0);	// End gracefully.
		}
		openAndReadFile("dictionaryWords.txt", hash);
		SLList<HashNode>[] table = hash.getChainTable();
		Game run = new Game();
    oriWord = args[0].toLowerCase().trim();
		usedWords.insert(new HashNode(oriWord, oriWord));
		goalWord = args[1].toLowerCase().trim();
		run.checkWordsExist(oriWord, goalWord, hash);
		String tempWord = "";
    char[] ch = run.toCharArray(oriWord);
		depth = 0;
		ori = new TNode(oriWord);
		queue.enqueue(ori);
		boolean correct = false;
		ori = parent;
		while (correct == false ) {
			try {
				current = queue.dequeue();
			} catch(Exception e) {
				System.out.println("Solution may be beyond given depth. \nThere is no solution.");
				System.exit(0);
			}
			//System.out.println(current.getDepth());
			tempWord = current.toString();
			//System.out.println("QUEUE: " + tempWord);
			run.checkSolvable();
			if (run.isGoal(tempWord)) {
				correct = true;
				//System.out.println(current.toString());
				steps.add(current.toString());
				depth = current.getDepth();
				while (!current.toString().equals(oriWord)) {
					//System.out.println(current.getParent().toString());
					//System.out.println(current.getDepth());
					steps.add(current.getParent().toString());
					current = current.getParent();
				}
			}
			else {
				//System.out.println("BASE WORD: " + tempWord);
				ch = run.toCharArray(tempWord);
 				run.makeNewWord(ch, hash, current);
			}
		}
		if (depth <= hops) {
			System.out.println("Can make it in " + depth + " hops.");
			run.printSteps();
		}

		else {
			System.out.println("Solution may be beyond given depth.\nThere is no solution.");
		}

  }

	public void checkSolvable() {
		if (current.getDepth() > hops) {
			System.out.println("Solution may be beyond given depth. \nThere is no solution.");
			System.exit(0);
		}
	}

	public void printSteps() {
		for (int x = steps.size() - 1; x >= 0; x--) {
			System.out.print(steps.get(x) + "\t");
		}
	}

	public void makeNewWord(char[] ori, HashChain h, TNode word) {
		String newWord ="";
		char c;
		int y = 0;
		char[] ch = new char[ori.length];
		parent = current;
		for (int x = 0; x < ori.length; x++) {
			ch[x] = ori[x];
		}

		for (int x = 0; x < ch.length; x++) {
			for(c = 'a'; c <= 'z'; ++c) {
				newWord = "";
				ch[x] = c;
				y = 0;
				while (y < ch.length) {
					//System.out.print(ch[y]);
					newWord += ch[y];
					y++;
				}
				//System.out.println(newWord);
				HashNode hn = h.search(newWord, newWord);
				if (hn != null) {
					//System.out.println(newWord + " exists");
					HashNode check = usedWords.search(newWord, newWord);
					if (check == null) {
						if (current.getParent() == null) {
							current = new TNode(newWord, word);
						}
						else {
							//System.out.print(current.toString());
							current = new TNode(newWord, parent);
						}
						//System.out.println(current.toString());
						//System.out.println(newWord + " is new");
						queue.enqueue(current);
						usedWords.insert(new HashNode(newWord, newWord));
					}
				}
			}
			//System.out.println();
			for (int i = 0; i < ori.length; i++) {
				ch[i] = ori[i];
			}
		}
	}

	public char[] toCharArray(String word) {
		char[] ch = new char[word.length()];
    for (int x = 0; x < word.length(); x++) {
    	ch[x] = word.charAt(x);
			//System.out.println(ch[x]);
    }
		return ch;
	}

	public boolean isGoal(String word) {
		if (word.equals(goalWord)) {
			return true;
		}
		// HashNode check = usedWords.search(goalWord, goalWord);
		// if (check != null) {
		// 	return true;
		// }
		return false;
	}

	public void checkWordsExist(String a, String b, HashChain h) {
		HashNode hn1 = h.search(a, a);
		HashNode hn2 = h.search(b, b);
		if (hn1 == null || hn2 == null) {
			System.out.println("Words are not the same length or not in dictionary. \nThere is no solution.");
			System.exit(0);	// End gracefully.
		}
	}


	//using the sting as the key and value
	private static void openAndReadFile(String fileName, HashChain h) {
		String line = null;
		int count = 0;
		try{
			FileReader fileReader = new FileReader(fileName);

			//wrap it in a buffered reader
			BufferedReader bufferedReader= new BufferedReader(fileReader);
			while((line=bufferedReader.readLine()) != null) {

				//split the string into tokens based on space
				String[] splitStr = line.split(" ");
				for(int i = 0; i<splitStr.length; i++){
					//System.out.println(splitStr[i]);
					h.insert(new HashNode(splitStr[i], splitStr[i]));
					count++;
				}

			}
			bufferedReader.close();

		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		//System.out.println(count);
	}
}
