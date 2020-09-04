import java.util.*;
import java.lang.*;

/**
 * This is the QueueList class.
 * @author Wei Zhong Tee
 * @since 8 May 2020
 */

public class QueueList<E> implements Queue<E> {
	private Node<E> front; // Reference to front.
	private Node<E> rear;	//Reference to rear
	private int size = 0;// Size of queue
	/** Constructors **/
	public QueueList(E it) {
		front= rear = new Node<E>(it); // Create top that stores it
		size++;
	}
	public QueueList() {
		front = rear = null;//Create an empty front & rear
		size = 0;
	}


  /**	Returns true if the stack is empty; otherwise false
    @return true is stack is empty otherwise false
  */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Returns the object at the front of the queue without removing it
    @return the object at the front of the queue
    @throws NoSuchElementException
  */
  public E front() {
    if(isEmpty()) {
      //System.out.println("Queue is empty");
      throw new NoSuchElementException();
    }

    else {
      return front.getElement();
    }
  }

  /** Returns the object at the front of the queue and removes it
    so stack is one smaller
    @return the object at the front of the queue
    @throws NoSuchElementException
  */
  public E dequeue() {
    if(isEmpty()) {
      //System.out.println("Queue is empty");
      throw new NoSuchElementException();
    }

    else {
      E frontElement = front.getElement();
      front = front.getNext();
      size--;
      return frontElement;
    }
  }


  /** Pushes an item onto the rear of the queue
    @param it The object to be inserted at the rear
  */
  public void enqueue(E it) {
    if (size == 0) {
      front= rear = new Node<E>(it); // Create top that stores it
    }

    else {
      Node<E> node = new Node <E> (it);
      rear.setNext(node);
      rear = node;
    }

    size ++;
  }


  /** Dumps the queue - clears it of its contents
  */
  public void clear() {
    front = rear = null;//Create an empty front & rear
    size = 0;
  }

  /** Returns the number of elements in the queue
    @return size - the number of elements on the queue
  */
  public int size() {
    return size;
  }

}
