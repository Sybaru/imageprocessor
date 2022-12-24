package imgprocessor.model.greyscale;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

/**
 * An abstract class implementing the Greyscale interface.
 * Converts an image into greyscale.
 */
public abstract class AGreyscale implements Greyscale {
  @Override
  public Image makeGreyscale(Image image) {
    int width = image.imageWidth();
    int height = image.imageHeight();
    int[][][] original = image.getImage();
    int[][][] greyImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          greyImage[i][j][k] = turnGrey(original[i][j]);
        }
      }
    }
    return new ImageImpl(greyImage);
  }

  /**
   * Helps the makeGreyscale function convert into greyscale.
   * By default, converts using the highest rgb value.
   * @param rgb the rgb value of a given color in the image.
   * @return the highest value between red green and blue.
   */
  public int turnGrey(int[] rgb) {
    int highest = 0;
    for (int i = 0; i < 3; i++) {
      if (rgb[i] > highest) {
        highest = rgb[i];
      }
    }
    return highest;
  }

}
