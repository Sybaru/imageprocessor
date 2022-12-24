package imgprocessor.model.filter;

import imgprocessor.model.Image;

/**
 * This represents an interface that can apply filters to images.
 * It contains one method that applies the filters.
 */
public interface Filter {

  /**
   * Applies a filter to an image.
   * @param image the image to be filtered
   * @param filter the array representing the filter to be applied.
   * @return the filtered image.
   */
  Image filter(Image image, double[][] filter);

}
