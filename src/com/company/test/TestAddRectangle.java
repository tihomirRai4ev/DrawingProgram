package com.company.test;


import org.junit.Test;

import com.company.commands.AddRectangleCommand;
import com.company.commands.CreateCanvasCommand;
import com.company.exception.UnsupportedModelDimentionsException;

/**
 * Test add Rectangle command.
 *
 * @author traychev
 */
public class TestAddRectangle {

  @Test(expected = Exception.class)
  public void testAddRectangleWithNoCanvas() {
    AddRectangleCommand cmd = new AddRectangleCommand();
    cmd.exec();
  }

  @Test
  public void testAddRectangleWithNoCanvasWithOkPoints() {
    AddRectangleCommand cmd = new AddRectangleCommand();
    cmd.setOptions(1, 2, 6, 2);
    cmd.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testAddRectangleWithNoCanvasWithWrongPoints() {
    AddRectangleCommand cmd = new AddRectangleCommand();
    cmd.setOptions(1, 2, 6, -3);
    cmd.exec();
  }

  @Test
  public void testAddRectangleWithCanvasWithOkPoints() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    AddRectangleCommand cmd2 = new AddRectangleCommand();
    cmd2.setOptions(14, 1, 18, 3);
    cmd2.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testAddRectangleWithCanvasWithWrongPoints() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    AddRectangleCommand cmd2 = new AddRectangleCommand();
    cmd2.setOptions(1, 2, 6, -2);
    cmd2.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testAddRectangleWithCanvasWithPointsOutsideOfCanvas() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    AddRectangleCommand cmd2 = new AddRectangleCommand();
    cmd2.setOptions(100, 100, 100, 100);
    cmd2.exec();
  }
}