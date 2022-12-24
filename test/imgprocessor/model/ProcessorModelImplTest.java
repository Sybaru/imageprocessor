package imgprocessor.model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import imgprocessor.model.greyscale.BlueGreyscaleImpl;
import imgprocessor.model.greyscale.GreenGreyscaleImpl;
import imgprocessor.model.greyscale.RedGreyscaleImpl;
import imgprocessor.model.greyscale.ValueGreyscaleImpl;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;

/**
 * Tests the ProcessorModelImpl class.
 */
public class ProcessorModelImplTest {

  private ProcessorModel test;
  private ProcessorModel pigeon;

  private Image pigeonImg;

  @Before
  public void setUp() throws IOException {
    test = new ProcessorModelImpl();
    pigeon = new ProcessorModelImpl();
    pigeonImg = new ImageImpl(readPPM("res/pigeon.ppm"));
    pigeon.loadImage(pigeonImg);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNotLoaded() {
    test.flipHoriz();
  }

  @Test
  public void testSave() throws IOException {
    pigeon.saveImage("res/verticalFlipPigeon.ppm");
    int [][][] resTest1 = readPPM("res/verticalFlipPigeon.ppm");
    assertEquals(resTest1, readPPM("res/pigeon.ppm"));
    File file = new File("res/verticalFlipPigeon.ppm");
    file.delete();
  }

  @Test
  public void testLoad() throws IOException {
    test.loadImage(pigeonImg);
    test.saveImage("res/verticalFlipPigeon.ppm");
    int [][][] resTest1 = readPPM("res/verticalFlipPigeon.ppm");
    assertEquals(resTest1, readPPM("res/pigeon.ppm"));
    File file = new File("res/verticalFlipPigeon.ppm");
    file.delete();
  }

  @Test
  public void brightnessTest() throws IOException {
    Image brightPigeon = pigeon.brightenImage(10);
    test.loadImage(brightPigeon);
    test.saveImage("res/testBright.ppm");
    int [][][] resTest1 = readPPM("res/testBright.ppm");
    assertEquals(resTest1[0][0][0], readPPM("res/pigeon.ppm")[0][0][0] + 10);
    File file = new File("res/testBright.ppm");
    file.delete();
  }

  @Test
  public void extremeBrightnessTest() throws IOException {
    Image brightPigeon = pigeon.brightenImage(500);
    test.loadImage(brightPigeon);
    test.saveImage("res/testBright.ppm");
    int [][][] resTest1 = readPPM("res/testBright.ppm");
    assertEquals(resTest1[0][0][0], 255);
    File file = new File("res/testBright.ppm");
    file.delete();
  }

  @Test
  public void darkenTest() throws IOException {
    Image brightPigeon = pigeon.brightenImage(-10);
    test.loadImage(brightPigeon);
    test.saveImage("res/testBright.ppm");
    int [][][] resTest1 = readPPM("res/testBright.ppm");
    assertEquals(resTest1[0][0][0], readPPM("res/pigeon.ppm")[0][0][0] - 10);
    File file = new File("res/testBright.ppm");
    file.delete();
  }

  @Test
  public void extremeDarkenTest() throws IOException {
    Image brightPigeon = pigeon.brightenImage(-500);
    test.loadImage(brightPigeon);
    test.saveImage("res/testBright.ppm");
    int [][][] resTest1 = readPPM("res/testBright.ppm");
    assertEquals(resTest1[0][0][0], 0);
    File file = new File("res/testBright.ppm");
    file.delete();
  }

  @Test
  public void flipVertTest() throws IOException {
    Image flipVert = pigeon.flipVert();
    test.loadImage(flipVert);
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/vertFlipPigeon.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void flipHorizTest() throws IOException {
    Image flipHoriz = pigeon.flipHoriz();
    test.loadImage((flipHoriz));
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/horizFlipPigeon.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void valueGreyTest() throws IOException {
    Image valueGrey = pigeon.toGreyscale(new ValueGreyscaleImpl());
    test.loadImage((valueGrey));
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/valueGreyPigeon.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void redGreyTest() throws IOException {
    Image valueGrey = pigeon.toGreyscale(new RedGreyscaleImpl());
    test.loadImage((valueGrey));
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/redGreyPigeon.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void greenGreyTest() throws IOException {
    Image valueGrey = pigeon.toGreyscale(new GreenGreyscaleImpl());
    test.loadImage((valueGrey));
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/greenGreyPigeon.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }

  @Test
  public void blueGreyTest() throws IOException {
    Image valueGrey = pigeon.toGreyscale(new BlueGreyscaleImpl());
    test.loadImage((valueGrey));
    test.saveImage("res/test1.ppm");
    int[][][] resTest1 = readPPM("res/test1.ppm");
    assertEquals(resTest1, readPPM("res/blueGreyPigeon.ppm"));
    File file = new File("res/test1.ppm");
    file.delete();
  }
}