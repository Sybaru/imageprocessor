package imgprocessor.model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the ImageImpl class.
 */
public class ImageImplTest {

  private Image pigeonImg;

  @Before
  public void setUp() throws IOException {
    pigeonImg = new ImageImpl(readPPM("res/pigeon.ppm"));
  }

  @Test
  public void testArrayConstructor() throws IOException {
    int[][][] pigeon = readPPM("res/pigeon.ppm");
    Image testImg = new ImageImpl(pigeon);
    assertEquals(pigeon, testImg.getImage());
    assertEquals(147, testImg.imageHeight());
    assertEquals(220, testImg.imageWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInput() {
    Image testImg = new ImageImpl((int[][][]) null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadArraySize() {
    Image testImg = new ImageImpl(new int[10][10][5]);
  }

  @Test
  public void testFilenameConstructor() throws IOException {
    Image testImg = new ImageImpl("res/pigeon.ppm");
    assertEquals(readPPM("res/pigeon.ppm"), testImg.getImage());
    assertEquals(147, testImg.imageHeight());
    assertEquals(220, testImg.imageWidth());
  }

  @Test(expected = IOException.class)
  public void testBadFileSize() throws IOException {
    Image testImg = new ImageImpl("res/sdafjhsk.ppg");
  }

  @Test
  public void getImage() throws IOException {
    int[][][] pigeon = readPPM("res/pigeon.ppm");
    assertEquals(pigeon, pigeonImg.getImage());
  }

  @Test
  public void imageHeight() {
    assertEquals(147, pigeonImg.imageHeight());
  }

  @Test
  public void imageWidth() {
    assertEquals(220, pigeonImg.imageWidth());
  }
}