/**
 * This is the TNode class.
 * @author Wei Zhong Tee
 * @since 8 May 2020
 */


public class TNode {
  private String item;
  private TNode parent;
  private int depth;

/**
 * This is the constructor to be used for the original word the user inputs
 * @param firstWord The first word the user input
 */
  public TNode(String firstWord) {
    this.item = firstWord;
    this.depth = 0;
  }

  /**
   * This is the constructor that is used for the word permutations
   * It also sets the depth of the TNode by using the value of the incremented the parent TNode depth
   * @param currentWord The current word permutations
   * @param before The parent TNode word
   */

  public TNode(String currentWord, TNode before) {
    this.item = currentWord;
    this.parent = before;
    this.depth = parent.getDepth() + 1;
  }

  /**
   * This method gets the parent node of the current node
   * @return The parent TNode
   */

  public TNode getParent() {
    return this.parent;
  }

  /**
   * Returns the current word permutation
   * @return The current word permutation as a string
   */

  public String getItem() {
    return this.item;
  }

  /**
   * The toString method for the TNode class
   * @return returns the TNode item as a string
   */

  public String toString() {
    return this.item.toString();
  }

  /**
   * Returns the depth of the TNode in the tree
   * @return The depth integer
   */

  public int getDepth() {
    return this.depth;
  }
}
