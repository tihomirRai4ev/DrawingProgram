package com.company.commands;

import com.company.exception.UnsupportedCommandLifecycleExecutionException;
import com.company.model.Canvas;

/**
 * Provides common functionality for the various commands, like
 * common command-line arguments handling, initialization, etc.
 *
 * @author traychev
 */
public abstract class CommandBase implements Command {

  @Override
  public void exec() throws UnsupportedCommandLifecycleExecutionException {
    if (Canvas.getCanvas() == null) {
      throw new UnsupportedCommandLifecycleExecutionException("Create Canvas Command '-C' should " +
                                                                  "be executed first. Type 'H' or" +
                                                                  " 'h' for help");
    }
    run();
  }

  public Canvas getCanvas() {
    return Canvas.getCanvas();
  }

  abstract void run();
}