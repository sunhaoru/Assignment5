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

    while (!((hash[0] == 0) && (hash[1] == 0) && (hash[2] == 0))) {
      this.nonce++;
      byte[] longbuffer = ByteBuffer.allocate(Long.BYTES).putLong(this.nonce).array();

      if (this.prevHash.data != null) {
        System.arraycopy(longbuffer, 0, hashtest, 2 * Integer.BYTES + this.prevHash.data.length,
            longbuffer.length);
      } else {
        System.arraycopy(longbuffer, 0, hashtest, 2 * Integer.BYTES, longbuffer.length);
      }

      md.update(hashtest);
      hash = md.digest();
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
  public int getNum() {
    return this.num;
  }

  public int getAmount() {
    return this.amount;
  }

  public long getNonce() {
    return this.nonce;
  }

  public Hash getPrevHash() {
    return this.prevHash;
  }

  public Hash getHash() {
    return this.Hash;
  }

  public String toString() {
    return "Block " + this.num + " (Amount: " + this.amount + ", Nonce: " + this.nonce
        + ", prevHash: " + this.prevHash + ", hash: " + this.Hash + ")";
  }
}
