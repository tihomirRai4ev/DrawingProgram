package com.company.commands;

import com.company.model.BucketFill;

/**
 * Fill Bucket command with similar to BucketFill in MS Paint behavior.
 *
 * @author traychev
 */
public class FillBucketCommand extends CommandBase {

  private BucketFill bucket;

  public void setOptions(int x1, int y1, String color) {
    bucket = new BucketFill(x1, y1, color);
  }

  @Override
  void run() {
    bucket.render();
  }
}
