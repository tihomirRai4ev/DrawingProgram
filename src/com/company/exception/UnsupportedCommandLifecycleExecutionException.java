package com.company.exception;

/**
 * Exception class for handling wrong command line execution lifecycle.
 *
 * @author traychev
 */
public class UnsupportedCommandLifecycleExecutionException extends RuntimeException {

  public UnsupportedCommandLifecycleExecutionException(String message) {
    super(message);
  }
}
