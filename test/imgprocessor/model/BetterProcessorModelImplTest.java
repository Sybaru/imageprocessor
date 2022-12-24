package imgprocessor.model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the BetterProcessorModelImpl class.
 */
public class BetterProcessorModelImplTest {

  private BetterProcessorModel test;
  private BetterProcessorModel pigeon;

  @Before
  public void setUp() throws IOException {
    test = new BetterProcessorModelImpl();
    pigeon = new BetterProcessorModelImpl();
    pigeon.loadImage(new ImageImpl(readPPM("res/pigeon.ppm")));
  }


  @Test
  public void blur() throws IOException {
    Image blur = pigeon.blur();
    test.loadImage(blur);
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/blur.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void sharpen() throws IOException {
    Image sharp = pigeon.sharpen();
    test.loadImage(sharp);
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/sharpPigeon.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void lumaGreyscale() throws IOException {
    Image luma = pigeon.lumaGreyscale();
    test.loadImage(luma);
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/lumaPigeon.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void sepia() throws IOException {
    Image sepia = pigeon.sepia();
    test.loadImage(sepia);
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/sepia.png"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void testNewFiles() throws IOException {
    Image test1 = new ImageImpl("res/pigeon.jpg");
    test.loadImage(test1);
    test.saveImage("res/test1.jpg");
    test.saveImage("res/test1.png");
    test.saveImage("res/test1.bmp");
    File f1 = new File("res/test1.jpg");
    File f2 = new File("res/test1.png");
    File f3 = new File("res/test1.bmp");
    assertEquals(f1.exists(), true);
    assertEquals(f2.exists(), true);
    assertEquals(f3.exists(), true);
    f1.delete();
    f2.delete();
    f3.delete();
  }


}