package imgprocessor.model.greyscale;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests the RedGreyscaleImpl class.
 */
public class RedGreyscaleImplTest {
  @Test
  public void turnGreyTest() throws IOException {
    int[] test = {5,10,4};
    assertEquals(5, new RedGreyscaleImpl().turnGrey(test));

  }
}