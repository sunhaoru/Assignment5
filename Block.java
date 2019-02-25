package blockChain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
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

    byte[] numbuffer = ByteBuffer.allocate(Integer.BYTES).putInt(this.num).array();
    byte[] databuffer = ByteBuffer.allocate(Integer.BYTES).putInt(this.amount).array();
    byte[] longbuffer = ByteBuffer.allocate(Long.BYTES).putLong(this.nonce).array();

    int size = numbuffer.length + databuffer.length + this.prevHash.data.length + longbuffer.length;

    byte[] hashtest = new byte[size];

    System.arraycopy(numbuffer, 0, hashtest, 0, numbuffer.length);
    System.arraycopy(databuffer, 0, hashtest, numbuffer.length, databuffer.length);
    System.arraycopy(this.prevHash.data, 0, hashtest, numbuffer.length + databuffer.length,
        this.prevHash.data.length);
    System.arraycopy(longbuffer, 0, hashtest,
        numbuffer.length + databuffer.length + this.prevHash.data.length, longbuffer.length);
    // https://stackoverflow.com/questions/5683486/how-to-combine-two-byte-arrays

    md.update(hashtest);
    byte[] hash = md.digest();

    while (!((hash[0] == 0) && (hash[1] == 0) && (hash[2] == 0))) {
      this.nonce++;
      longbuffer = ByteBuffer.allocate(Long.BYTES).putLong(this.nonce).array();

      System.arraycopy(longbuffer, 0, hashtest,
          numbuffer.length + databuffer.length + this.prevHash.data.length, longbuffer.length);

      md.update(hashtest);
      hash = md.digest();
    }
    this.Hash = new Hash(hash);
  }// https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java

  public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    this.num = num;
    this.amount = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    
    MessageDigest md = MessageDigest.getInstance("sha-256");

    byte[] numbuffer = ByteBuffer.allocate(Integer.BYTES).putInt(this.num).array();
    byte[] databuffer = ByteBuffer.allocate(Integer.BYTES).putInt(this.amount).array();
    byte[] longbuffer = ByteBuffer.allocate(Long.BYTES).putLong(this.nonce).array();

    int size = numbuffer.length + databuffer.length + this.prevHash.data.length + longbuffer.length;

    byte[] hashtest = new byte[size];

    System.arraycopy(numbuffer, 0, hashtest, 0, numbuffer.length);
    System.arraycopy(databuffer, 0, hashtest, numbuffer.length, databuffer.length);
    System.arraycopy(this.prevHash.data, 0, hashtest, numbuffer.length + databuffer.length,
        this.prevHash.data.length);
    System.arraycopy(longbuffer, 0, hashtest,
        numbuffer.length + databuffer.length + this.prevHash.data.length, longbuffer.length);
    // https://stackoverflow.com/questions/5683486/how-to-combine-two-byte-arrays

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
