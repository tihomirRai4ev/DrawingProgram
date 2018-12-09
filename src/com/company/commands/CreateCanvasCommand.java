package com.company.commands;

import com.company.exception.CommandIlleagalParametersUsage;
import com.company.model.Canvas;

/**
 * Simple Create Canvas implementation.
 *
 * @author traychev
 */
public class CreateCanvasCommand extends CommandBase {

  private Canvas canvas;

  public void setOptions(int w, int h) {
    this.canvas = Canvas.getCanvas(w, h);
  }

  @Override
  void run() {
    if (canvas != null) {
      canvas.render();
    } else {
      throw new CommandIlleagalParametersUsage("Canvas misconfiguration, parameters unset. Please " +
                                                   "first invoke setOptions() with the correct " +
                                                   "arguments and then try again");
    }
  }
}