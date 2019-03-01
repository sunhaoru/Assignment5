package blockChain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

public class Block {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  int num;
  int amount;
  Hash prevHash;
  long nonce;
  Hash Hash;

  // +--------------+-----------------------------------------------------
  // | Constructors |
  // +--------------+
  public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = 0;

    // build a MessageDigest object to update later so we can calculate the hash
    MessageDigest md = MessageDigest.getInstance("sha-256");

    byte[] hashtest = null;

    // if there is a previous block/hash in the blockchain..
    if (this.prevHash.data != null) {
      // ...include the size and content of the previous hash when building the byte array, hashtest
      hashtest = ByteBuffer.allocate(2 * Integer.BYTES + Long.BYTES + this.prevHash.data.length)
          .putInt(this.num).putInt(this.amount).put(this.prevHash.data).putLong(this.nonce).array();
    } else {
      // otherwise, disregard the size and content of the previous hash when building the arra
      hashtest = ByteBuffer.allocate(2 * Integer.BYTES + Long.BYTES).putInt(this.num)
          .putInt(this.amount).putLong(this.nonce).array();
    }

    md.update(hashtest);

    byte[] hash = md.digest();// calculate the hash

    // while the generated is not valid (does not begin with 000)..
    while (!((hash[0] == 0) && (hash[1] == 0) && (hash[2] == 0))) {
      // ...increment the nonce in order to test a new one
      this.nonce++;
      byte[] longbuffer = ByteBuffer.allocate(Long.BYTES).putLong(this.nonce).array();

      if (this.prevHash.data != null) {
        System.arraycopy(longbuffer, 0, hashtest, 2 * Integer.BYTES + this.prevHash.data.length,
            longbuffer.length);
      } else {
        System.arraycopy(longbuffer, 0, hashtest, 2 * Integer.BYTES, longbuffer.length);
      }

      md.update(hashtest);// update the MessageDigest object
      hash = md.digest(); // calculate a hash with the incremented nonce
    }

    this.Hash = new Hash(hash);
  }
  // https://stackoverflow.com/questions/5683486/how-to-combine-two-byte-arrays
  // https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java

  public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;

    MessageDigest md = MessageDigest.getInstance("sha-256");

    byte[] hashtest = null;

    if (this.prevHash.data != null) {
      hashtest = ByteBuffer.allocate(2 * Integer.BYTES + Long.BYTES + this.prevHash.data.length)
          .putInt(this.num).putInt(this.amount).put(this.prevHash.data).putLong(this.nonce).array();
    } else {
      hashtest = ByteBuffer.allocate(2 * Integer.BYTES + Long.BYTES).putInt(this.num)
          .putInt(this.amount).putLong(this.nonce).array();
    }

    md.update(hashtest);

    byte[] hash = md.digest();

    this.Hash = new Hash(hash);
  }

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * @return this.num. the number, num, of the block
   * @post: num is an integer >= 0
   */
  public int getNum() {
    return this.num;
  }

  /**
   * @return this.amount. the amount transferred that is recorded in the block
   */
  public int getAmount() {
    return this.amount;
  }

  /**
   * @return this.nonce. the nonce of the block
   */
  public long getNonce() {
    return this.nonce;
  }

  /**
   * @return this.prevHash. the hash of the previous hash in the blockchain
   */
  public Hash getPrevHash() {
    return this.prevHash;
  }

  /**
   * @return this.Hash. the hash of the current block in the blockchain
   */
  public Hash getHash() {
    return this.Hash;
  }

  /**
   * @return the string representation of the block
   * @post the string is formatted as "Block <num> (Amount: <amt>, Nonce: <nonce>, prevHash:
   *       <prevHash>, hash: <hash>)"
   */
  public String toString() {
    return "Block " + this.num + " (Amount: " + this.amount + ", Nonce: " + this.nonce
        + ", prevHash: " + this.prevHash + ", hash: " + this.Hash + ")";
  }
}
