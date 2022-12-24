package imgprocessor.model;

/**
 * Represents an interface for an image processing model.
 * Includes functions that can alter an image.
 * Implements all functions of ProcessorModel, but also can blur, sharpen, sepia, or lumaGreyscale.
 */
public interface BetterProcessorModel extends ProcessorModel {

  /**
   * Blurs an image.
   * @return the blurred image.
   */
  Image blur();

  /**
   * Sharpens an image.
   * @return the sharpened image.
   */
  Image sharpen();

  /**
   * Greyscales an image with luma.
   * @return the greyscaled image.
   */
  Image lumaGreyscale();

  /**
   * Applies a sepia filter to an image.
   * @return the filtered image.
   */
  Image sepia();
}
