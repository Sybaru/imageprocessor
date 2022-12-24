package imgprocessor.model;

/**
 * This interface represents an Image.
 * It contains methods to return specific attributes of the image.
 */
public interface Image {

  /**
   * Returns the image as a 3d array, with x and y coordinates and a rgb value for each point.
   * @return a 3d int array.
   */
  public int[][][] getImage();

  /**
   * Gets the height of the image.
   * @return int height of the image.
   */
  public int imageHeight();

  /**
   * Gets the width of the image.
   * @return int width of the image.
   */
  public int imageWidth();
}
