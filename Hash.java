package blockChain;

import java.util.Arrays;

public class Hash {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  byte[] data;

  // +--------------+-----------------------------------------------------
  // | Constructors |
  // +--------------+
  public Hash(byte[] data) {
    this.data = data;
  }

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * @return this.data. a byte array that hash contained in 'this' object
   */
  public byte[] getData() {
    return this.data;
  }

  /**
   * @post: returns true if the hash begins with 000 returns false otherwise
   */
  public boolean isValid() {
    return ((this.data[0] == 0) && (this.data[1] == 0) && (this.data[2] == 0));
  }

  /**
   * converts the hash byte array into a string of hexadecimal digits
   * 
   * @post: the hexadecimal string will contain twice as many digits as there are bytes in the hash
   */
  public String toString() {
    if (this.data == null) {
      return null;
    } else {
      StringBuilder hexString = new StringBuilder(this.data.length * 2);
      for (byte b : data) {
        hexString.append(String.format("%02x", b));
      }
      return hexString.toString();
    }
  }// https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java

  /**
   * @param Object other an object that is going to be compared with hash
   * @post: returns true if other is structurally equal to hash returns false otherwise
   */
  public boolean equals(Object other) {
    if (other instanceof Hash) {
      Hash o = (Hash) other;
      return Arrays.equals(o.data, this.data);
    } else
      return false;
  }

}
