package imgprocessor.model;

import java.io.IOException;

/**
 * Implements the image interface.
 * Represents an image and contains all the data of the image.
 */
public class ImageImpl implements Image {

  protected int[][][] inputImage;
  protected int height;
  protected int width;


  /**
   * Creates an image based on a 3d int array containing the values of the image.
   * @param inputImage an array representing the image.
   */
  public ImageImpl(int[][][] inputImage) {
    if (inputImage == null || inputImage[0][0].length != 3) {
      throw new IllegalArgumentException();
    }
    this.inputImage = inputImage;
    this.width = inputImage[0].length;
    this.height = inputImage.length;
  }

  /**
   * Creates an image from a given ppm file.
   * @param filename the filename of the file
   * @throws IOException if the file is invalid.
   */
  public ImageImpl(String filename) throws IOException {
    this(ImageUtil.readPPM(filename));
  }

  @Override
  public int[][][] getImage() {
    return this.inputImage;
  }

  @Override
  public int imageHeight() {
    return this.height;
  }

  @Override
  public int imageWidth() {
    return this.width;
  }
}
