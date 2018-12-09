package com.company.model;

import java.util.LinkedList;

import com.company.exception.UnsupportedModelDimentionsException;

/**
 * <p>
 * Class to hold information about size of Canvas display. As all geometric
 * objects will be inside our Canvas, we do not need to have more than one
 * Canvas at that time. As a result we make this class Singleton and reuse the
 * instance for drawing purposes.
 * </p>
 * <p>
 * Supported Features:
 * </p>
 * <li>
 * Canvas Horizontal border use <b> '-' </b> as a delimiter.
 * </li>
 * <li>
 * Canvas Vertical border use <b><i> '|' </i></b> as a delimiter.
 * </li>
 * <li>
 * Canvas line use <b><i> 'x' </i></b> as a line character.
 * </li>
 * <li>
 * No thread safety support for this version.
 * </li>
 * <li>
 * Lazy initialization Singleton implementation used to satisfy requirements.
 * </li>
 *
 * @author traychev
 * @since 08.12.2018 @ 14:00 EET.
 */
public final class Canvas implements ShapeBase {

  private static final char LINE_SYMBOL = 'x';
  private final int width;
  private final int height;
  private static Canvas instance;
  private static String HORIZONTAL_BORDER;
  private static char[][] canvasMatrix;

  private Canvas(int w, int h) {
    width = w;
    height = h;
    if (!validate()) {
      throw new UnsupportedModelDimentionsException("Width or Height provided are not valid.");
    }
    canvasMatrix = new char[this.height][this.width];
    HORIZONTAL_BORDER = getHorizontalBorder();
    for (int row = 0; row < canvasMatrix.length; row++) {
      for (int col = 0; col < canvasMatrix[row].length; col++) {
        canvasMatrix[row][col] = ' ';
      }
    }
  }

  /**
   * Get Canvas instance. Note this is not thread safe, multiple threads can create more than one
   * instance.
   *
   * @param w width.
   * @param h height.
   *
   * @return Canvas.
   */
  public static Canvas getCanvas(int w, int h) {
    if (w < 0 || h < 0) {
      throw new UnsupportedModelDimentionsException("Negative canvas coordinates!");
    }
    if (instance == null) {
      instance = new Canvas(w, h);
    }
    return instance;
  }

  /**
   * Get instance of the Canvas. This method should be invoked when instance is already initialized.
   *
   * @return Canvas instance, already created.
   */
  public static Canvas getCanvas() {
    return instance;
  }

  /**
   * Calculate and store in memory the horizontal border as a java.lang.String.
   *
   * @return horizontal border as a String
   */
  private String getHorizontalBorder() {
    StringBuilder border = new StringBuilder();
    for (int i = 0; i < width + 2; i++) {
      border.append('-');
    }
    return border.toString();
  }

  /**
   * Method to add a single Line in the model. Line consist of two Points by their four coordinates.
   *
   * @param x1 x1 coordinate
   * @param y1 y1 coordinate
   * @param x2 x2 coordinate
   * @param y2 y2 coordinate
   */
  void addLine(int x1, int y1, int x2, int y2) {
    for (int row = y1 - 1; row <= y2 - 1 && row < canvasMatrix.length; row++) {
      for (int col = x1 - 1; col <= x2 - 1 && col < canvasMatrix[row].length; col++) {
        canvasMatrix[row][col] = LINE_SYMBOL;
      }
    }
  }

  /**
   * Very simple wrapper of one element in the matrix.
   */
  public class Index {
    public int x;
    public int y;

    public Index(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  /**
   * Should fill the entire area connected to (x,y) with "colour" c. The
   * behaviour of this is the same as that of the "bucket fill" tool in paint
   * programs.
   *
   * @param x1    x coordinate
   * @param y1    y coordinate
   * @param color color to be used
   */
  void fillBucket(int x1, int y1, char color) {
    if (!validate() || x1 >= width || y1 >= height) {
      throw new UnsupportedModelDimentionsException("Invalid arguments provided");
    }
    boolean[][] dp = new boolean[this.height][this.width]; //false by default.
    Index first = new Index(x1, y1);
    LinkedList<Index> layer = new LinkedList<>();
    layer.add(first);
    while (!layer.isEmpty()) {
      LinkedList<Index> next = new LinkedList<>();
      while (!layer.isEmpty()) {
        Index idx = layer.remove();
        Index idx1 = new Index(idx.x + 1, idx.y);
        Index idx2 = new Index(idx.x, idx.y + 1);
        Index idx3 = new Index(idx.x - 1, idx.y);
        Index idx4 = new Index(idx.x, idx.y - 1);
        if (idx1.x >= 0 && idx1.x < this.width && idx1.y >= 0 && idx1.y < this.height &&
            !dp[idx1.y][idx1.x] &&
            canvasMatrix[idx1.y][idx1.x] != LINE_SYMBOL) {
          canvasMatrix[idx1.y][idx1.x] = color;
          dp[idx1.y][idx1.x] = true;
          next.add(idx1);
        }
        if (idx2.x >= 0 && idx2.x < this.width && idx2.y >= 0 && idx2.y < this.height &&
            !dp[idx2.y][idx2.x] &&
            canvasMatrix[idx2.y][idx2.x] != LINE_SYMBOL) {
          canvasMatrix[idx2.y][idx2.x] = color;
          dp[idx2.y][idx2.x] = true;
          next.add(idx2);
        }
        if (idx3.x >= 0 && idx3.x < this.width && idx3.y >= 0 && idx3.y < this.height &&
            !dp[idx3.y][idx3.x] &&
            canvasMatrix[idx3.y][idx3.x] != LINE_SYMBOL) {
          canvasMatrix[idx3.y][idx3.x] = color;
          dp[idx3.y][idx3.x] = true;
          next.add(idx3);
        }
        if (idx4.x >= 0 && idx4.x < this.width && idx4.y >= 0 && idx4.y < this.height &&
            !dp[idx4.y][idx4.x] &&
            canvasMatrix[idx4.y][idx4.x] != LINE_SYMBOL) {
          canvasMatrix[idx4.y][idx4.x] = color;
          dp[idx4.y][idx4.x] = true;
          next.add(idx4);
        }
      }
      layer.addAll(next);
    }
  }

  @Override
  public void render() {
    System.out.print("\n");
    StringBuilder builder = new StringBuilder();
    builder.append(HORIZONTAL_BORDER).append("\n");
    for (int i = 0; i < this.height; i++) {
      builder.append('|');
      for (int j = 0; j < this.width; j++) {
        builder.append(canvasMatrix[i][j]);
      }
      builder.append('|');
      builder.append("\n");
    }
    builder.append(HORIZONTAL_BORDER);
    System.out.print(builder.toString());
  }

  @Override
  public boolean validate() {
    return (width >= 0 && height >= 0);
  }

  int getWidth() {
    return width;
  }

  int getHeight() {
    return height;
  }
}