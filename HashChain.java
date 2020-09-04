import java.util.*;
/**
 * This implements a Hash Table using separate chaining to resolve
  * collisions
 * @author Wei Zhong Tee
 * @since 8 May 2020
 */

public class HashChain<K, V >{

	private SLList<HashNode<K,V>>[] hashTable;//array of linked lists to store the key value pairs
	private int numberOfItems;//to determine when to rehash



	/** Constructor
	* @param size initial size of hashtable
	**/
	public HashChain(int size){
		hashTable = new SLList[size];
		//initialize hashtable with with empthy linked lists
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new SLList <HashNode<K, V>>();
		}
		numberOfItems = 0;
	}

	/** load balance - have it return a double in case you want to use
	 * use percentages later
	 */
	public double calcLoad(){
		return (numberOfItems + 0.0)/hashTable.length;
	}

	/**
	* insert the key value pair into the hash table
	* @param hn HashNode that stores key-value pair
	*/
	public void insert(HashNode<K,V> hn){
		//for seperate chaining, we want to keep load around 10 or less
		if (calcLoad() > 10) {
			rehash();
		}

		int position = hashCode(hn.getKey(), hashTable.length);
		SLList<HashNode<K, V>> l = hashTable[position];
		l.add(hn);
		numberOfItems++;
	}
	/**
	* insert the key into the given hash table - this is for rehashing!
	* @param hn HashNode that stores key-value pair
	* @param table the new hash table to store the key-value pair
	*/
	private void insert (HashNode<K,V> hn, SLList<HashNode<K,V>>[]table){
		int position = hashCode(hn.getKey(), table.length);
		SLList<HashNode<K, V>> l = table[position];
		l.add(hn);
		numberOfItems++;
	}

	/**
	* make a bigger table and rehash contents of old table
	*/
	public void rehash(){
		//System.out.println("Rehashing"); //can be commented out
		numberOfItems = 0;
		SLList<HashNode<K,V>>[] bigger = new SLList[hashTable.length*2 + 1];
		for (int i = 0; i < bigger.length; i++) {
			bigger[i] = new SLList <HashNode <K,V>>();
		}
		//take everything from the old hash table and rehash it into bigger
		for (int i = 0; i < hashTable.length; i++) {
			SLList <HashNode <K,V>> l = hashTable[i];
			Node <HashNode<K,V>> n = l.getHead();
			while (n != null) {
				insert(n.getElement(), bigger);
				n = n.getNext();
			}
		}
		//reassign reference to bigger
		hashTable = bigger;
	}

	/**
	* simple hash function from slides
	* uses Horner's rule with radix of 97
	* @param key - the thing that will be used as a key
	* @param tablesize - used to make sure position generate is valid
	* @return an integer index into the table
	*/
	public int hashCode(K key, int tableSize){
		String k = key.toString();
		int hashValue = 0;
		for (int i = 0; i< k.length(); i++){
			hashValue = 97 * hashValue +k.charAt(i);
		}
		hashValue %= tableSize;//take the mod of the tableSize
		if (hashValue < 0){
			hashValue += tableSize;
		}
		//System.out.println(key +" has a hash value of "+ hashValue);//for debugging
		return hashValue;
	}


	/**
	* @return HashNode with key value you are looking for
	* @param key - class used for key
	* @param value - class you are storing
	*/
	public HashNode<K,V> search(K key, V value){
		int position = hashCode(key, hashTable.length);
		SLList<HashNode<K, V>> l = hashTable[position];
		//System.out.println(value + " should be at position " + position);
		Node <HashNode<K,V>> n = l.getHead();
		int itemNumber = 0;
		while (n != null) {
			if (n.getElement().getValue().equals(value)) {
				//System.out.println("The number of places in the list searched has before FINDING the value of " + value + " is " + itemNumber);
				//System.out.println();
				return n.getElement();
			}
			n = n.getNext();
			itemNumber++;
		}
		//System.out.println("The number of places in the list searched has before NOT FINDING the value of " + value + " is " + itemNumber);
		//System.out.println();
		return null;
	}

	/**
	 * removing the key value pair for the table if it is there
	 * @param key - class used for key - this is the key for the value for which you are looking
	 * @param value - class that is the value you want
	 */
	public void remove(K key, V value){
		int position = hashCode(key, hashTable.length);
		SLList<HashNode<K, V>> l = hashTable[position];
		System.out.println("Removing: " + value + " should be at position " + position);
		Node <HashNode<K,V>> n = l.getHead();
		int itemNumber = 0;
		while (n != null) {
			if (n.getElement().getValue().equals(value)) {
				l.remove(itemNumber);
				return;
			}
			n = n.getNext();
			itemNumber++;
		}
		System.out.println(value + " is not in the table so we cannot remove it");
		System.out.println();
	}

	/** @return the array of linked lists
	*/
	public SLList[] getChainTable(){
		return hashTable;
	}
}
