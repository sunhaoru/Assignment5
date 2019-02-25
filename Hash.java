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
  public byte[] getData() {
    return this.data;
  }

  public boolean isValid() {
    return ((this.data[0] == 0) && (this.data[1] == 0) && (this.data[2] == 0));
  }

  public String toString() {
    StringBuilder hexString = new StringBuilder(this.data.length * 2);
    for (byte b : data) {
      hexString.append(String.format("%02x", b));
    }
    return hexString.toString();
  }//https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
  
  public boolean equals(Object other) {
    if(other instanceof Hash) {
      Hash o = (Hash) other;
      return Arrays.equals(o.data, this.data);
    }
    else
      return false;
  }

}
