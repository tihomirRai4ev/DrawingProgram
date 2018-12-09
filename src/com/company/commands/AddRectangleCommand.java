package com.company.commands;

import com.company.model.Rectangle;

/**
 * Command to handle Rectangle rendering.
 *
 * @author traychev
 */
public class AddRectangleCommand extends CommandBase {

  private Rectangle rectangle;

  public void setOptions(int x1, int y1, int x2, int y2) {
    this.rectangle = new Rectangle(x1, y1, x2, y2);
  }

  @Override
  void run() {
    rectangle.render();
  }
}