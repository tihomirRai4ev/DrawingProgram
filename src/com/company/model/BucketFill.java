package com.company.model;

/**
 * Class to encapsulate the logic of filling the entire area connected to (x,y) with "colour" c. The
 * behaviour of this is the same as that of the "bucket fill" tool in paint
 * programs.
 */
public class BucketFill extends CPointsBase {

  private char color;

  public BucketFill(int x1, int y1, String color) {
    super.x1 = x1;
    super.y1 = y1;
    this.color = color.charAt(0);
  }

  @Override
  protected void run() {
    canvas.fillBucket(x1, y1, color);
  }

  @Override
  public boolean validate() {
    return super.x1 >= 0 && super.y1 >= 0;
  }
}
