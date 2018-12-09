package com.company.commands;

import com.company.exception.UnsupportedCommandLifecycleExecutionException;


/**
 * A common interface for all Drawing subcommands.
 *
 * @author traychev
 */
public interface Command {

  /**
   * Entrypoint of the subcommand.
   *
   * @throws UnsupportedCommandLifecycleExecutionException
   */
  void exec() throws UnsupportedCommandLifecycleExecutionException;
}