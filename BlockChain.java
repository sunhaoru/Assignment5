package blockChain;

import java.security.NoSuchAlgorithmException;

public class BlockChain extends Block{

  public BlockChain(int num, int amount, blockChain.Hash prevHash) throws NoSuchAlgorithmException {
    super(num, amount, prevHash);
    // TODO Auto-generated constructor stub
  }
}
