package imgprocessor.model.greyscale;

/**
 * Extends the AGreyscale abstract class.
 * Converts an image into greyscale using the blue-component of rgb.
 */
public class BlueGreyscaleImpl extends AGreyscale {

  /**
   * Helps convert the color into greyscale.
   * @param rgb the rgb value of a given color in the image.
   * @return the blue value of a color.
   */
  @Override
  public int turnGrey(int[] rgb) {
    return rgb[2];
  }
}
