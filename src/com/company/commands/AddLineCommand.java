package com.company.commands;

import com.company.model.Line;

/**
 * Command to handle Line rendering.
 *
 * @author traychev
 */
public class AddLineCommand extends CommandBase {

  private Line line;

  public void setOptions(int x1, int y1, int x2, int y2) {
    line = new Line(x1, y1, x2, y2);
  }

  @Override
  void run() {
    line.render();
  }
}