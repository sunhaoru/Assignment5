package blockChain;

import java.security.NoSuchAlgorithmException;
import java.io.PrintWriter;

public class BlockChain {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  Node<Block> first;
  Node<Block> last;
  Node<Block> current;
  int num = 0;// help track the size of the chain

  // +--------------+-----------------------------------------------------
  // | Constructors |
  // +--------------+
  public BlockChain(int initial) throws NoSuchAlgorithmException {
    Node<Block> initialBlock = new Node<Block>(new Block(num, initial, new Hash(null)), null);
    this.first = initialBlock;
    this.last = initialBlock;
    this.current = initialBlock;
    this.num++;
  }


  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * 
   * @param amount
   * @return nextBlock a block that is created based on input amount and previous hash
   * @throws NoSuchAlgorithmException
   */
  public Block mine(int amount) throws NoSuchAlgorithmException {
    Block nextBlock = new Block(this.num, amount, this.last.value.Hash);
    // Jonathan Sadun helped us troubleshoot using super (before we had better ideas)
    return nextBlock;
  }

  /**
   * 
   * @return this.num the size of the block chain
   */
  public int getSize() {
    return this.num;
  }

  /**
   * append a block to an existed block chain
   * 
   * @param blk
   * @throws IllegalArgumentException
   */
  public void append(Block blk) throws IllegalArgumentException {
    if (!this.last.value.getHash().equals(blk.prevHash)) {
      throw new IllegalArgumentException("Invalid previous Hash!");
    }
    Node<Block> nB = new Node<Block>(blk, null);
    this.last.next = nB;
    this.last = nB;
    this.num++;
  }

  /**
   * remove the last element in the block chain, which is the node pointed by this.last
   * 
   * @return boolean value
   * @post return false when this is only one or no element left in the block chain remove the last
   *       element and return true
   */
  public boolean removeLast() {
    if (this.first == this.last) {
      return false;
    } else {
      Node<Block> iterator = this.first;
      while (iterator.next.next != null) {
        iterator = iterator.next;
      }
      iterator.next = null;
      this.last = iterator;
      this.num--;

      return true;
    }
  }

  /**
   * 
   * @return this.last.value.getHash the hash of the block stored in the last position
   */
  public Hash getHash() {
    return this.last.value.getHash();
  }

  /**
   * 
   * @return boolean value
   * @post return false if the balances are smaller than 0 return true otherwise
   */
  public boolean isValidBlockChain() {
    Node<Block> iterator = this.first;

    int amtAlice = this.first.value.getAmount();
    int amtBob = 0;

    if (this.first == this.last) {
      return true;
    } else {
      iterator = iterator.next;
      while (iterator != null) {

        amtBob = amtBob - iterator.value.getAmount();
        amtAlice = amtAlice + iterator.value.getAmount();

        if (amtBob < 0 || amtAlice < 0) {
          return false;
        }
        iterator = iterator.next;
      }
      return true;
    }
  }

  /**
   * print the balances
   */
  public void printBalances() {
    PrintWriter pen = new PrintWriter(System.out, true);
    Node<Block> iterator = this.first.next;
    int amtBob = 0;
    int amtAlice = this.first.value.getAmount();

    while (iterator != null) {
      amtBob = amtBob - iterator.value.getAmount();
      amtAlice = amtAlice + iterator.value.getAmount();
      iterator = iterator.next;
    }
    pen.println("Alice: " + amtAlice + ", Bob: " + amtBob);
  } // printBalances

  /**
   * convert the whole block chain into String
   * 
   * @return str.toString
   */
  public String toString() {
    Node<Block> iterator = this.first;
    StringBuilder str = new StringBuilder();
    while (iterator != null) {
      str.append(iterator.value.toString()).append(System.getProperty("line.separator"));
      iterator = iterator.next;
    }
    return str.toString();
  }// https://stackoverflow.com/questions/14534767/how-to-append-a-newline-to-stringbuilder
}


