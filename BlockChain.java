package blockChain;

import java.security.NoSuchAlgorithmException;
import java.io.PrintWriter;

public class BlockChain extends Block {
  Node<Block> first;
  Node<Block> last;

  public BlockChain(int initial) throws NoSuchAlgorithmException {
    super(0, initial, new Hash(null));
    Node<Block> initialBlock = new Node<Block>(this, null);
    this.first = initialBlock;
    this.last = initialBlock;
  }

  public Block mine(int amount) throws NoSuchAlgorithmException {
    Block nextBlock = new Block(this.num++, amount, this.last.value.Hash);
    // Jonathan Sadun helped us troubleshoot using super
    return nextBlock;
  }

  int getSize() {
    return this.num;
  }

  void append(Block blk) throws IllegalArgumentException {
    Node<Block> nB = new Node<Block>(blk, null);
    this.last.next = nB;
    this.last = nB; // needs more (identifying invalid wrt rest of blocks)
  }

  boolean removeLast() {
    if (this.first == this.last) {
      return false;
    } else {
      Node<Block> currentBlock = this.first;
      while (currentBlock.next.next != null) {
        currentBlock = currentBlock.next;
      }
      currentBlock.next = null;
      this.last = currentBlock;

      return true;
    }
  }

  public Hash getHash() {
    return this.last.value.getHash();
  }

  public boolean isValidBlockChain() {
    Node<Block> currentBlock = this.first.next;
    Node<Block> prevBlock = this.first;

    int amtAlice = this.first.value.getAmount();
    int amtBob = 0;

    if (this.first == this.last) {
      return true;
    } else {
      while (currentBlock.next != null) {

        amtBob = amtBob - currentBlock.value.getAmount();
        amtAlice = amtAlice + currentBlock.value.getAmount();

        if (currentBlock.value.prevHash != prevBlock.value.getHash()) {
          return false;
        } else if (amtBob < 0 || amtAlice < 0) {
          return false;
        }
        currentBlock = currentBlock.next;
      }
      return true;
    }
  }

  public void printBalances() {
    PrintWriter pen = new PrintWriter(System.out, true);
    Node<Block> currentBlock = this.first.next;
    int amtBob = 0;
    int amtAlice = this.first.value.getAmount();

    while (currentBlock.next != null) {
      amtBob = amtBob - currentBlock.value.getAmount();
      amtAlice = amtAlice + currentBlock.value.getAmount();
    }
    pen.println("Alice: " + amtAlice + ", Bob: " + amtBob);
  } // printBalances

  public String toString() {
    Node<Block> currentBlock = this.first;
    StringBuilder str = new StringBuilder();
    while (currentBlock.next != null) {
      str.append(currentBlock.value.toString()).append(System.getProperty("line.separator"));
      currentBlock = currentBlock.next;
    }
    return str.toString();
  }// https://stackoverflow.com/questions/14534767/how-to-append-a-newline-to-stringbuilder
}


