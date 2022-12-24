package imgprocessor.model.greyscale;

/**
 * Extends the AGreyscale abstract class.
 * Converts an image into greyscale using the green-component of rgb.
 */
public class GreenGreyscaleImpl extends AGreyscale {

  /**
   * Helps convert the color into greyscale.
   * @param rgb the rgb value of a given color in the image.
   * @return the green value of a color.
   */
  @Override
  public int turnGrey(int[] rgb) {
    return rgb[1];
  }
}
