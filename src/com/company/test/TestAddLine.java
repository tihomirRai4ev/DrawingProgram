package com.company.test;

import org.junit.Test;

import com.company.commands.AddLineCommand;
import com.company.commands.CreateCanvasCommand;
import com.company.exception.UnsupportedModelDimentionsException;

/**
 * Test add Line command.
 *
 * @author traychev
 */
public class TestAddLine {

  @Test(expected = Exception.class)
  public void testAddLineWithNoCanvas() {
    AddLineCommand cmd = new AddLineCommand();
    cmd.exec();
  }

  @Test
  public void testAddLineWithNoCanvasWithOkPoints() {
    AddLineCommand cmd = new AddLineCommand();
    cmd.setOptions(1, 2, 6, 2);
    cmd.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testAddLineWithNoCanvasWithWrongPoints() {
    AddLineCommand cmd = new AddLineCommand();
    cmd.setOptions(1, 2, 6, -3);
    cmd.exec();
  }

  @Test
  public void testAddLineWithCanvasWithOkPoints() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    AddLineCommand cmd2 = new AddLineCommand();
    cmd2.setOptions(1, 2, 6, 2);
    cmd2.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testAddLineWithCanvasWithWrongPoints() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    AddLineCommand cmd2 = new AddLineCommand();
    cmd2.setOptions(1, 2, 6, -2);
    cmd2.exec();
  }
}
