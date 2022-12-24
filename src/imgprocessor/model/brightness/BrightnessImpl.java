package imgprocessor.model.brightness;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

/**
 * Implements the Brightness interface.
 * Used to change the brightness of an image by each color value on the image.
 */
public class BrightnessImpl implements Brightness {

  @Override
  public Image changeBrightness(Image image, int change) {
    int width = image.imageWidth();
    int height = image.imageHeight();
    int[][][] original = image.getImage();
    int[][][] brightImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          int newValue =  constrain(original[i][j][k] + change);
          brightImage[i][j][k] = newValue;
        }
      }
    }
    return new ImageImpl(brightImage);
  }

  /**
   * Constrains a rgb value to be in between 0 and 255.
   * @param value The value being constrained.
   * @return the constrained value
   */
  private int constrain(int value) {
    if (value > 255) {
      value = 255;
    } else if (value < 0) {
      value = 0;
    }
    return value;
  }
}
