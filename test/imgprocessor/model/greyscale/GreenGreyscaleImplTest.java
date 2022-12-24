package imgprocessor.model.greyscale;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests the GreenGreyscaleImpl class.
 */
public class GreenGreyscaleImplTest {
  @Test
  public void turnGreyTest() throws IOException {
    int[] test = {5,10,4};
    assertEquals(10, new GreenGreyscaleImpl().turnGrey(test));

  }
}