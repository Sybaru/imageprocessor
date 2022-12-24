package imgprocessor.model.flipper;

import org.junit.Test;

import java.io.IOException;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the HorizontalFlipper Class.
 */
public class HorizontalFlipperImplTest {

  @Test
  public void flipHelper() throws IOException {
    HorizontalFlipperImpl flipper = new HorizontalFlipperImpl();
    int[][][] flipped = flipper.flipHelper(220, 147, readPPM("res/pigeon.ppm"));
    assertEquals(flipped, readPPM("res/horizFlipPigeon.ppm"));
  }
}