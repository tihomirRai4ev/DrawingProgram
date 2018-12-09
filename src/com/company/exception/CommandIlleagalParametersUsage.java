package com.company.exception;

/**
 * Exception class for handling wrong command line execution lifecycle.
 *
 * @author traychev
 */
public class CommandIlleagalParametersUsage extends RuntimeException {

  public CommandIlleagalParametersUsage(String message) {
    super(message);
  }
}
