package blockChain;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.io.PrintWriter;

public class BlockChainDriver {
  public static void main(String[] args) throws NoSuchAlgorithmException {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scanner = new Scanner(System.in);
    /*
     * Block testBlock = new Block(0, 300, new Hash(null));
     * 
     * pen.println(testBlock.getNonce());
     * 
     * Hash testHash = testBlock.getHash();
     * 
     * pen.println(testHash);
     * 
     * testBlock = new Block(1, -150, testHash);
     * 
     * pen.println(testBlock.getNonce());
     */
    BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
    pen.println(chain.toString());
    pen.println("Command?");

    String command = scanner.next();

    while (!command.equals("quit")) {
      switch (command) {
        case "mine":
          pen.println("Amount transferred?");
          int amount = Integer.parseInt(scanner.next());
          Block nB = chain.mine(amount);
          pen.println("amount = " + nB.getAmount() + ", nonce = " + nB.getNonce());
          break;

        case "append":

          break;

        case "remove":

          break;

        case "check":

          break;

        case "report":

          break;

        case "help":
          pen.println("Valid commands:");
          pen.println("    mine: discovers the nonce for a given transaction");
          pen.println("    append: appends a new block onto the end of the chain");
          pen.println("    remove: removes the last block from the end of the chain");
          pen.println("    check: checks that the block chain is valid");
          pen.println("    report: reports the balances of Alice and Bob");
          pen.println("    help: prints this list of commands");
          pen.println("    quit: quits the program");
          break;
        case "quit":
          break;

        default:
          pen.println("Command is not valid!");
          break;
      }
      command = scanner.next();
    }
    scanner.close();
  }
}
