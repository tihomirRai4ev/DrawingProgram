package com.company.model;

import com.company.exception.UnsupportedModelDimentionsException;

/**
 * Class to hold information details about a single Rectangle inside {@link
 * com.company.model.Canvas}. Rectangle consist of 4 (four) {@link
 * com.company.model.Line}, so we use Line's internal implementation to display
 * the Rectangle. We need to check whether the provided coordinates/points are
 * valid, only in this case we can mutate the points to create the Lines.
 *
 * @author traychev
 */
public class Rectangle extends CPointsBase {

  public Rectangle(int x1, int y1, int x2, int y2) {
    super.x1 = x1;
    super.y1 = y1;
    super.x2 = x2;
    super.y2 = y2;
  }

  @Override
  protected void run() {
    canvas.addLine(x1, y1, x2, y1);
    canvas.addLine(x1, y1, x1, y2);
    canvas.addLine(x2, y1, x2, y2);
    canvas.addLine(x1, y2, x2, y2);
  }

  @Override
  public boolean validate() {
    return (x1 >= 0 && y1 >= 0 && y2 >= 0 && x2 >= 0) && canvas != null && (x1 < 1 || x1 < canvas
        .getWidth() || y2 < 1 || y2 < canvas
        .getHeight());
  }
}
