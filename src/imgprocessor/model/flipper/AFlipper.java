package imgprocessor.model.flipper;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

/**
 * Abstract class for a Flipper implementation.
 * By default, flips an image vertically.
 */
public abstract class AFlipper implements Flipper {

  @Override
  public Image flipImage(Image image) {
    int width = image.imageWidth();
    int height = image.imageHeight();
    int[][][] original = image.getImage();
    int [][][] flipImage = flipHelper(width, height, original);
    return new ImageImpl(flipImage);
  }

  /**
   * Helps the flipImage class flip an image into a new array.
   * @param width width of the image.
   * @param height height of the image.
   * @param original the original image array.
   * @return a new flipped image array
   */
  public int[][][] flipHelper(int width, int height, int[][][] original) {
    int[][][] flipImage = new int[height][width][3];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height / 2; j++) {
        flipImage[j][i] =  original[height - 1 - j][i];
        flipImage[height - 1 - j][i] = original[j][i];
      }
    }
    return flipImage;
  }
}
