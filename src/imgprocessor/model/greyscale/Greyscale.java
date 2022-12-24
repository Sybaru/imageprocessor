package imgprocessor.model.greyscale;

import imgprocessor.model.Image;

/**
 * This represents an interface that can convert an image into greyscale.
 * It contains one method that converts to greyscale.
 */
public interface Greyscale {

  /**
   * Converts a given image into greyscale.
   * @param image The image to be converted into greyscale.
   * @return a new converted image.
   */
  Image makeGreyscale(Image image);
}
