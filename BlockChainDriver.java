package blockChain;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.io.PrintWriter;

public class BlockChainDriver {
  public static void main(String[] args) throws NoSuchAlgorithmException {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scanner = new Scanner(System.in);// create a scanner to scan inputs

    BlockChain chain = new BlockChain(Integer.parseInt(args[0]));
    pen.println(chain.toString());
    pen.println("Command?");

    String command = scanner.next();

    // continue execute until user input "quit"
    while (!command.equals("quit")) {

      // the switch cases which is implemented by different input command
      switch (command) {
        case "mine":
          pen.println("Amount transferred?");
          int amount = Integer.parseInt(scanner.next());
          Block nB = chain.mine(amount);
          pen.println("amount = " + nB.getAmount() + ", nonce = " + nB.getNonce());
          pen.println();
          pen.println(chain.toString());
          break;

        case "append":
          pen.println("Amount transferred?");
          int amount_append = Integer.parseInt(scanner.next());
          pen.println("Nonce");
          long nonce_append = Long.parseLong(scanner.next());
          chain.append(new Block(chain.getSize(), amount_append, chain.getHash(), nonce_append));
          pen.println();
          pen.println(chain.toString());
          break;

        case "remove":
          if (!chain.removeLast()) {
            pen.println("Can't be removed!");
          }
          pen.println();
          pen.println(chain.toString());
          break;

        case "check":
          if (chain.isValidBlockChain()) {
            pen.println("Chain is valid!");
          } else {
            pen.println("Chain is invalid!");
          }
          pen.println();
          pen.println(chain.toString());
          break;

        case "report":
          chain.printBalances();
          pen.println();
          pen.println(chain.toString());
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

        // case for invalid command
        default:
          pen.println("Command is invalid!");
          break;
      }
      pen.println("Command?");
      command = scanner.next();
    }
    scanner.close();// close the scanner

  }
}
