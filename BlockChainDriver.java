package blockChain;

import java.security.NoSuchAlgorithmException;
import java.io.PrintWriter;

public class BlockChainDriver {
  public static void main(String[] args) throws NoSuchAlgorithmException {
   PrintWriter pen = new PrintWriter(System.out,true);
   
   byte[] data = new byte[2];
   data[0] = 1;
   data[1] = 1;
   
   Hash prevHash = new Hash(data);
   
    Block test = new Block(1, 100, prevHash);
    
    //26782655
    
    pen.println(test.Hash.toString());//000000991abe7b3b4c580fe8b44a2a2daf2bebbc3fa6d0a4165539a775322deb
  }
}
