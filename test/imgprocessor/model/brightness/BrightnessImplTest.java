package imgprocessor.model.brightness;

import org.junit.Test;

import java.io.IOException;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the brightnessimpl class.
 */
public class BrightnessImplTest {

  @Test
  public void changeBrightness() throws IOException {
    BrightnessImpl test = new BrightnessImpl();
    ImageImpl pigeon = new ImageImpl("res/pigeon.ppm");
    Image altered = test.changeBrightness(pigeon, 10);
    assertEquals(altered.getImage()[0][0][0], pigeon.getImage()[0][0][0] + 10);
    assertEquals(altered.getImage(), readPPM("res/pigeon.ppm"));
  }
}