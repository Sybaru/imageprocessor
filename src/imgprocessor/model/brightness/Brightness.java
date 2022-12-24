package imgprocessor.model.brightness;

import imgprocessor.model.Image;

/**
 * This represents an interface that can change the brightness of images.
 * It has a method that changes an image's brightness.
 */
public interface Brightness {

  /**
   * Changes the brightness of a given image by the given value.
   * @param image the image that will be altered
   * @param change the amount of change in brightness desired
   * @return a new altered image.
   */
  Image changeBrightness(Image image, int change);
}
