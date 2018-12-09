package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.company.commands.AddLineCommand;
import com.company.commands.AddRectangleCommand;
import com.company.commands.Command;
import com.company.commands.CreateCanvasCommand;
import com.company.commands.FillBucketCommand;
import com.company.commands.ManCommand;

/**
 * Jar Entry point. Class handles system.in and system.out as well as
 * commands execution lifecycle.
 *
 * @author traychev
 */
public class App {

  private final static int SUCCESS_EXIT_CODE = 0;

  /**
   * Helper factory to facilitate command execution lifecycle.
   */
  private static class CommandFactory {

    Map<String, Command> commands = new HashMap<>();
    private boolean initialized = false;

    private void init() {
      commands.put("c", new CreateCanvasCommand());
      commands.put("l", new AddLineCommand());
      commands.put("r", new AddRectangleCommand());
      commands.put("b", new FillBucketCommand());
      commands.put("h", new ManCommand());
      initialized = true;
    }

    Command getCmd(String command) {
      if (command == null) {
        //Shouldn't happen.
        throw new RuntimeException("Null Command provided");
      }
      if ("Q".equalsIgnoreCase(command)) {
        System.exit(SUCCESS_EXIT_CODE);
      }
      if (!initialized) {
        init();
      }
      return commands.get(command.toLowerCase());
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    CommandFactory cmdFactory = new CommandFactory();
    //Example usage:
    //C 20 4 -> L 1 2 6 2 ->  L 6 3 6 4 -> R 14 1 18 3 -> B 10 3 o -> Q
    while (true) {
      System.out.println("Enter a command:");
      String cmd = scanner.nextLine();
      String[] opts = cmd.split("\\s+");
      Command command;
      try {
        command = cmdFactory.getCmd(opts[0]);
        if (command == null) {
          System.out.print("Not a valid command, please try again or type 'h' or 'H' to display " +
                               "manual page.");
          continue;
        }
        // Determine command to run:
        if (command instanceof CreateCanvasCommand) {
          Integer w = Integer.parseInt(opts[1]);
          Integer h = Integer.parseInt(opts[2]);
          ((CreateCanvasCommand) command).setOptions(w, h);
        }
        if (command instanceof AddLineCommand) {
          Integer x1 = Integer.parseInt(opts[1]);
          Integer y1 = Integer.parseInt(opts[2]);
          Integer x2 = Integer.parseInt(opts[3]);
          Integer y2 = Integer.parseInt(opts[4]);
          ((AddLineCommand) command).setOptions(x1, y1, x2, y2);
        }
        if (command instanceof AddRectangleCommand) {
          Integer x1 = Integer.parseInt(opts[1]);
          Integer y1 = Integer.parseInt(opts[2]);
          Integer x2 = Integer.parseInt(opts[3]);
          Integer y2 = Integer.parseInt(opts[4]);
          ((AddRectangleCommand) command).setOptions(x1, y1, x2, y2);
        }
        if (command instanceof FillBucketCommand) {
          Integer x1 = Integer.parseInt(opts[1]);
          Integer y1 = Integer.parseInt(opts[2]);
          ((FillBucketCommand) command).setOptions(x1, y1, opts[3]);
        }
        command.exec();
      } catch (Exception e) {
        System.out.print("Unexpected error occured. Please try again or see manual page for more " +
                             "information.");
        e.printStackTrace();
      }
      System.out.print("\n");
    }
  }
}