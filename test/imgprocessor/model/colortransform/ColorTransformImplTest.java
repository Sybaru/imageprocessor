package imgprocessor.model.colortransform;

import org.junit.Test;

import java.io.IOException;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the ColorTransFormImpl class.
 */
public class ColorTransformImplTest {

  @Test
  public void transform() throws IOException {
    ColorTransformImpl test = new ColorTransformImpl();
    ImageImpl pigeon = new ImageImpl("res/pigeon.ppm");
    double[][] sepia = {{0.393, 0.769, 0.189},
                           {0.349, 0.686, 0.168},
                           {0.272, 0.534, 0.131}};
    Image altered = test.transform(pigeon, sepia);
    assertEquals(altered.getImage(), readPPM("res/sepia.ppm"));
  }
}