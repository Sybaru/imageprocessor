package imgprocessor.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import imgprocessor.model.BetterProcessorModelImpl;
import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;
import imgprocessor.model.ProcessorModel;

import static imgprocessor.model.ImageUtil.readPPM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the processorControllerImpl class.
 */
public class ProcessorControllerImplTest {
  private ProcessorModel pigeon;

  Image pigeonImg;

  @Before
  public void begin() throws IOException {
    pigeonImg = new ImageImpl(readPPM("res/pigeon.ppm"));
    pigeon = new BetterProcessorModelImpl();
  }

  @Test(expected = IOException.class)
  public void commandExceptionTest() throws IOException {
    Readable read = new StringReader("abc");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();
    fail("Throws exception");
  }

  @Test(expected = IllegalArgumentException.class)
  public void loadExceptionTest() throws IOException {
    Readable read = new StringReader("load");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();
    fail("Throws exception");
  }

  @Test(expected = IllegalArgumentException.class)
  public void saveExceptionTest() throws IOException {
    Readable read = new StringReader("save");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();
    fail("Throws exception");
  }


  @Test
  public void brightnessTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "brighten 10 pigeon pigeonBrighter");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("pigeonBrighter"));

    Image brighter = imgs.get("pigeonBrighter");
    assertEquals(brighter.getImage(), readPPM("res/brightenedPigeon.ppm"));
  }

  @Test
  public void flippingVertTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "vertical-flip pigeon vertFlipPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("vertFlipPigeon"));

    Image vertFlip = imgs.get("vertFlipPigeon");
    assertEquals(vertFlip.getImage(), readPPM("res/vertFlipPigeon.ppm"));
  }

  @Test
  public void flippingHorizTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "horizontal-flip pigeon horizFlipPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("horizFlipPigeon"));

    Image horizFlip = imgs.get("horizFlipPigeon");
    assertEquals(horizFlip.getImage(), readPPM("res/horizFlipPigeon.ppm"));
  }

  @Test
  public void greyScaleTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "value-component pigeon valueGreyPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("valueGreyPigeon"));

    Image greyValue = imgs.get("valueGreyPigeon");
    assertEquals(greyValue.getImage(), readPPM("res/valueGreyPigeon.ppm"));
  }

  @Test
  public void redGreyTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "red-component pigeon redGreyPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("redGreyPigeon"));

    Image red = imgs.get("redGreyPigeon");
    assertEquals(red.getImage(), readPPM("res/redGreyPigeon.ppm"));
  }

  @Test
  public void greenGreyTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "green-component pigeon greenGreyPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("greenGreyPigeon"));

    Image green = imgs.get("greenGreyPigeon");
    assertEquals(green.getImage(), readPPM("res/greenGreyPigeon.ppm"));
  }

  @Test
  public void blueGreyTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "blue-component pigeon blueGreyPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("blueGreyPigeon"));

    Image blue = imgs.get("blueGreyPigeon");
    assertEquals(blue.getImage(), readPPM("res/blueGreyPigeon.ppm"));
  }

  @Test
  public void blurTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "blur pigeon blurPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("blurPigeon"));

    Image blue = imgs.get("blurPigeon");
    assertEquals(blue.getImage(), readPPM("res/blur.ppm"));
  }

  @Test
  public void sepiaTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "sepia pigeon sepiaPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("sepiaPigeon"));

    Image blue = imgs.get("sepiaPigeon");
    assertEquals(blue.getImage(), readPPM("res/sepia.ppm"));
  }

  @Test
  public void lumaTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "greyscale pigeon lumaPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("lumaPigeon"));

    Image blue = imgs.get("lumaPigeon");
    assertEquals(blue.getImage(), readPPM("res/lumaPigeon.ppm"));
  }

  @Test
  public void sharpenTest() throws IOException {
    Readable read = new StringReader("load res/pigeon.ppm pigeon \n" +
            "sharpen pigeon sharpPigeon");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();

    HashMap<String, Image> imgs = test.currentImages();

    assertTrue(imgs.containsKey("sharpPigeon"));

    Image blue = imgs.get("sharpPigeon");
    assertEquals(blue.getImage(), readPPM("res/sharpPigeon.ppm"));
  }

  @Test
  public void runFromFile() throws IOException {
    Readable read = new StringReader("-file test/imgprocessor/controller/testRunFromFile.txt");
    ProcessorController test = new ProcessorControllerImpl(read, pigeon);
    test.parseInput();
    File f1 = new File("resultImages/testFile.ppm");
    assertEquals(f1.exists(), true);
    f1.delete();
  }
}