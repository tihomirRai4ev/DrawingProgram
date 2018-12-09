package com.company.test;

import org.junit.Test;

import com.company.commands.CreateCanvasCommand;
import com.company.exception.CommandIlleagalParametersUsage;
import com.company.exception.UnsupportedModelDimentionsException;

/**
 * Test create Canvas command.
 *
 * @author traychev
 */
public class TestCreateCanvasCommand {

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testWithIncorrectParams() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(-1, -2);
    cmd.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testWithIncorrectParams2() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, -1);
    cmd.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testWithIncorrectParams3() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(-20, 4);
    cmd.exec();
  }

  @Test
  public void testWithCorrectParams() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    // No exception is thrown - success.
  }

  @Test(expected = CommandIlleagalParametersUsage.class)
  public void testWithNoParams() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.exec();
  }
}