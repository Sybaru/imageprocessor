package imgprocessor.model.filter;

import org.junit.Test;

import java.io.IOException;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the filter class.
 */
public class FilterImplTest {

  @Test
  public void filter() throws IOException {
    FilterImpl test = new FilterImpl();
    ImageImpl pigeon = new ImageImpl("res/pigeon.ppm");
    double[][] sharpenFilter = {{-0.125, -0.125, -0.125, -0.125, -0.125},
                                   {-0.125, 0.25, 0.25, 0.25, -0.125},
                                   {-0.125, 0.25, 1, 0.25, -0.125},
                                   {-0.125, 0.25, 0.25, 0.25, -0.125},
                                   {-0.125, -0.125, -0.125, -0.125, -0.125}};
    Image altered = test.filter(pigeon, sharpenFilter);
    assertEquals(altered.getImage(), readPPM("res/sharpen.ppm"));
  }
}