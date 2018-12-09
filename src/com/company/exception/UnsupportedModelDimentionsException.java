package com.company.exception;

/**
 * Exception class to wrap any misconfigurations based on shape dimentions.
 *
 * @author traychev
 */
public class UnsupportedModelDimentionsException extends RuntimeException {

  public UnsupportedModelDimentionsException() {
  }

  public UnsupportedModelDimentionsException(String message) {
    super(message);
  }

  public UnsupportedModelDimentionsException(String message, Throwable cause) {
    super(message, cause);
  }
}
