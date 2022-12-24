package imgprocessor.model.flipper;

import imgprocessor.model.Image;

/**
 * This represents an interface that can flip the orientation of an image.
 * It has a method that can flip an image.
 */
public interface Flipper {

  /**
   * Flips a given image in a orientation.
   * @param image the image to be flipped
   * @return a flipped image
   */
  Image flipImage(Image image);
}
