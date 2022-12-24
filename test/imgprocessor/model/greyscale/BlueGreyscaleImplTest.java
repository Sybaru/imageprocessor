package imgprocessor.model.greyscale;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests the blueGreyscaleImpl class.
 */
public class BlueGreyscaleImplTest {
  @Test
  public void turnGreyTest() throws IOException {
    int[] test = {5,10,4};
    assertEquals(4, new BlueGreyscaleImpl().turnGrey(test));

  }

}