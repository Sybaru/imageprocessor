package imgprocessor.model.greyscale;

import org.junit.Test;

import java.io.IOException;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the AGreyscale class.
 */
public class AGreyscaleTest {

  @Test
  public void makeGreyScaleTest() throws IOException {
    Image greyImage = new ValueGreyscaleImpl().makeGreyscale(
            new ImageImpl("res/valueGreyPigeon.ppm"));
    assertEquals(greyImage.getImage(), readPPM("res/valueGreyPigeon.ppm"));
  }

  @Test
  public void turnGreyTest() throws IOException {
    int[] test = {5,10,4};
    assertEquals(10, new ValueGreyscaleImpl().turnGrey(test));
  }

}