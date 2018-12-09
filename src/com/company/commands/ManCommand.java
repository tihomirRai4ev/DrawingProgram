package com.company.commands;

/**
 * Man Command.
 *
 * @author traychev
 */
public class ManCommand extends CommandBase {

  @Override
  public String toString() {
    return "Command Man page:\n" +
        "'C' - create Canvas, should be invoked to initialize display.\n" +
        "'L' - Create a line in the canvas.\n" +
        "'R' - Create Rectangle in the canvas.\n" +
        "'B' - fill all whitespaces inside the shape.\n" +
        "'Q' - Quit.\n";
  }

  @Override
  void run() {
    System.out.print(this.toString());
  }
}