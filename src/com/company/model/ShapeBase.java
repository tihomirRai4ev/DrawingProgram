package com.company.model;

/**
 * Base class of all supported objects inside the {@link com.company.model.Canvas}.
 *
 * @author traychev
 */
public interface ShapeBase {

  /**
   * Method to draw any shape in the Canvas.
   */
  void render();

  /**
   * Method to verify whether the points of the object are inside the canvas.
   *
   * @return true, if the points are inside the canvas, false otherwise.
   */
  boolean validate();
}
