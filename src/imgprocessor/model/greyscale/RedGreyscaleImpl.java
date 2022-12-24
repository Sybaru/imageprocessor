package imgprocessor.model.greyscale;

/**
 * Extends the AGreyscale abstract class.
 * Converts an image into greyscale using the red-component of rgb.
 */
public class RedGreyscaleImpl extends AGreyscale {

  /**
   * Helps convert the color into greyscale.
   * @param rgb the rgb value of a given color in the image.
   * @return the red value of a color.
   */
  @Override
  public int turnGrey(int[] rgb) {
    return rgb[0];
  }
}
