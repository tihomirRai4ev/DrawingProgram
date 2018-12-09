package com.company.test;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import com.company.commands.AddRectangleCommand;
import com.company.commands.CreateCanvasCommand;
import com.company.commands.FillBucketCommand;
import com.company.exception.UnsupportedCommandLifecycleExecutionException;
import com.company.exception.UnsupportedModelDimentionsException;
import com.company.model.Canvas;

/**
 * Test Fill Bucket command.
 *
 * @author traychev
 */
public class TestFIllBucketCommand {

  /**
   * Most important test here. We check that when the point provided is outside of the
   * Rectangle the Indexes inside it are not filled with the 'color'. If this breaks the
   * 'bfs with dp' algorithm has been regressed or obfuscated.
   *
   * @throws NoSuchFieldException   won't happen, this also check whether we add or rename the field
   * @throws IllegalAccessException won't happen, this also check whether we add or rename the field
   */
  @Test
  public void testFillBucketCorrectnessOverOverlappingAreas() throws NoSuchFieldException,
      IllegalAccessException {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    AddRectangleCommand cmd2 = new AddRectangleCommand();
    //14 1 18 3
    cmd2.setOptions(14, 1, 18, 3);
    cmd2.exec();
    FillBucketCommand cmd3 = new FillBucketCommand();
    cmd3.setOptions(10, 3, "o");
    cmd3.exec();
    Canvas canvas = cmd3.getCanvas();
    // As we don't want to expose the canvas matrix we get it here with reflection.
    // Can be done also with simple Mocking of the Canvas class making the field protected
    // and passing the reference here.
    Field f = canvas.getClass().getDeclaredField("canvasMatrix");
    f.setAccessible(true);
    char[][] canvasMatrix = (char[][]) f.get(canvas);
    boolean isUnset1 = ' ' == canvasMatrix[1][14];
    boolean isUnset2 = ' ' == canvasMatrix[1][15];
    boolean isUnset3 = ' ' == canvasMatrix[1][16];
    Assert.assertTrue((isUnset1 && isUnset2 && isUnset3));
  }

  @Test(expected = Exception.class)
  public void testFillBucketWithNoCanvas() {
    FillBucketCommand cmd = new FillBucketCommand();
    cmd.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testFillBucketWithNoCanvasWithWrongPoints() {
    FillBucketCommand cmd = new FillBucketCommand();
    cmd.setOptions(1, -1, "o");
    cmd.exec();
  }

  @Test
  public void testFillBucketWithCanvasWithOkPoints() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    FillBucketCommand cmd2 = new FillBucketCommand();
    // Expect to fill the whole canvas.
    cmd2.setOptions(1, 2, "o");
    cmd2.exec();
  }

  @Test(expected = UnsupportedModelDimentionsException.class)
  public void testFillBucketWithCanvasWithWrongPoints() {
    CreateCanvasCommand cmd = new CreateCanvasCommand();
    cmd.setOptions(20, 4);
    cmd.exec();
    FillBucketCommand cmd2 = new FillBucketCommand();
    cmd2.setOptions(1, -1, "o");
    cmd2.exec();
  }
}