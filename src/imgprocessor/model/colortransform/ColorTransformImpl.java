package imgprocessor.model.colortransform;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

/**
 * Implements the ColorTransform interface.
 * Used to change transform an image based on a given array of values.
 */
public class ColorTransformImpl implements ColorTransform {

  @Override
  public Image transform(Image image, double[][] transform) {
    int width = image.imageWidth();
    int height = image.imageHeight();
    int[][][] original = image.getImage();
    int[][][] transformImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          transformImage[i][j][k] = transformColor(original[i][j], transform[k]);
        }
      }
    }
    return new ImageImpl(transformImage);
  }

  private int transformColor(int[] color, double[] transform) {
    double newValue = 0;
    for (int i = 0; i < transform.length; i++) {
      newValue += color[i] * transform[i];
    }
    return constrain((int) Math.round(newValue));
  }

  private int constrain(int value) {
    if (value > 255) {
      value = 255;
    } else if (value < 0) {
      value = 0;
    }
    return value;
  }
}
