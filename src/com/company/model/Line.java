package com.company.model;

import com.company.exception.UnsupportedModelDimentionsException;

/**
 * Class to hold information about a single <b>Line</b>.
 *
 * @author traychev
 */
public class Line extends CPointsBase {

  public Line(int x1, int y1, int x2, int y2) {
    swapIfNeeded(x1, y1, x2, y2);
  }

  private void swapIfNeeded(int x1, int y1, int x2, int y2) {
    if ((x1 == x2 && y1 > y2) || (y1 == y2 && x1 > x2)) {
      super.x1 = x2;
      super.y1 = y2;
      super.x2 = x1;
      super.y2 = y1;
    } else {
      super.x1 = x1;
      super.y1 = y1;
      super.x2 = x2;
      super.y2 = y2;
    }
  }

  @Override
  protected void run() {
    canvas.addLine(x1, y1, x2, y2);
  }

  @Override
  public boolean validate() {
    return (x1 >= 0 && y1 >= 0 && y2 >= 0 && x2 >= 0) && (x1 != x2 && y1 != y2) || (x1 >= 0 && y1
        >= 0 && x2 >= 0 && y2
        >= 0);
  }
}