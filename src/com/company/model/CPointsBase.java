package com.company.model;

import com.company.exception.UnsupportedCommandLifecycleExecutionException;
import com.company.exception.UnsupportedModelDimentionsException;

/**
 * <p>
 * Coordinates Points Base is to handle any new Object's rendering details from
 * 'outside world'. Any class which extends this should handle Canvas matrix change
 * on its own and validate coordinates.
 * </p>
 *
 * @author traychev
 */
public abstract class CPointsBase implements ShapeBase {

  Canvas canvas = Canvas.getCanvas();
  int x1;
  int y1;
  int x2;
  int y2;

  @Override
  public void render() {
    if (canvas != null) {
      if (validate()) {
        run();
        canvas.render();
      } else {
        throw new UnsupportedModelDimentionsException();
      }
    } else {
      throw new UnsupportedCommandLifecycleExecutionException("Display is not loaded, please type" +
                                                                  " 'h' for more details and try " +
                                                                  "again");
    }
  }

  protected abstract void run();
}
