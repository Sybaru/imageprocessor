package imgprocessor.model;

import java.io.IOException;

import imgprocessor.model.greyscale.Greyscale;

/**
 * Represents an interface for an image processing model.
 * Includes functions that can alter an image.
 * These functions are greyscaling, brightness, flipping, and saving.
 */
public interface ProcessorModel {

  /**
   * Checks if there is a image loaded into the model.
   * @throws IllegalArgumentException if the image in the model is null
   */
  void imageExists();

  /**
   * Converts an image into a given greyscale type.
   * @param grey the type of greyscaling to be done.
   * @return the greyscaled image.
   */
  Image toGreyscale(Greyscale grey);

  /**
   * Loads an image into the processor.
   * This image is the one that will be edited.
   * @param image the image to be edited.
   */
  void loadImage(Image image);

  /**
   * Saves an image to a file.
   * @param imagePath the path to save the image to.
   * @throws IOException if it fails to write the file.
   */
  void saveImage(String imagePath) throws IOException;

  /**
   * Alters the brightness of an image.
   * @param value the amount to change the brightness by
   * @return the altered image.
   */
  Image brightenImage(int value);

  /**
   * Flips an image horizontally.
   * @return the flipped image
   */
  Image flipHoriz();

  /**
   * Flips an image vertically.
   * @return the flipped image
   */
  Image flipVert();

}
