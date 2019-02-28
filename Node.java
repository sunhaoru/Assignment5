package blockChain;
 // Stolen from Sam, file linear-structures-master

  public class Node<T> {
    // +--------+----------------------------------------------------------
    // | Fields |
    // +--------+

    /**
     * The value stored in the node.
     */
    T value;

    /**
     * The next node.
     */
    Node<T> next;

    // +--------------+----------------------------------------------------
    // | Constructors |
    // +--------------+

    /**
     * Create a new node that contains val and that links to next.
     */
    public Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    } // Node(T, Node<T>)
  } // class Node<T>
