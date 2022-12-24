package imgprocessor.model.flipper;

import org.junit.Test;

import java.io.IOException;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the AFlipper class.
 */
public class AFlipperTest {

  @Test
  public void flipImage() throws IOException {
    Image flipped = new VertFlipperImpl().flipImage(new ImageImpl("res/pigeon.ppm"));
    assertEquals(flipped.getImage(), readPPM("res/vertFlipPigeon.ppm"));
  }

  @Test
  public void flipHelper() throws IOException {
    VertFlipperImpl flipper = new VertFlipperImpl();
    int[][][] flipped = flipper.flipHelper(220, 147, readPPM("res/pigeon.ppm"));
    assertEquals(flipped, readPPM("res/vertFlipPigeon.ppm"));
  }
}