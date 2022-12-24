package imgprocessor.model.colortransform;

import imgprocessor.model.Image;

/**
 * This represents an interface that can preform transform operations on images.
 * It contains one method that transforms the images.
 */
public interface ColorTransform {

  /**
   * Applies a transform to the image.
   * @param image the image to be transformed
   * @param transform the array to transform the values by
   * @return the transformed image.
   */
  Image transform(Image image, double[][] transform);
}
