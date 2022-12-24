package imgprocessor.model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import imgprocessor.model.brightness.BrightnessImpl;
import imgprocessor.model.flipper.HorizontalFlipperImpl;
import imgprocessor.model.flipper.VertFlipperImpl;
import imgprocessor.model.greyscale.Greyscale;

import static imgprocessor.model.ImageUtil.savePPM;

/**
 * Represents an implementation of an image processing model.
 * Includes functions that can alter an image.
 * These functions are greyscaling, brightness, flipping, and saving.
 */
public class ProcessorModelImpl implements ProcessorModel {

  protected Image image;

  /**
   * The default constructor of the ProcessorModel.
   * Creates a processorModel with a null image.
   */
  public ProcessorModelImpl() {
    this.image = null;
  }

  @Override
  public void imageExists() {
    if (this.image == null) {
      throw new IllegalArgumentException("Error: no image loaded yet");
    }
  }

  @Override
  public Image toGreyscale(Greyscale grey) {
    imageExists();
    return grey.makeGreyscale(this.image);
  }

  @Override
  public void loadImage(Image image) {
    this.image = image;
  }

  @Override
  public void saveImage(String filename) throws IOException {
    imageExists();
    savePPM(filename, this.image);
  }

  private BufferedWriter makeWriteFile(String filename) throws IOException {
    BufferedWriter f = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
    f.write("P3");
    f.newLine();
    f.write(this.image.imageWidth() + " " + this.image.imageHeight());
    f.newLine();
    f.write("255");
    f.newLine();
    return f;
  }

  @Override
  public Image brightenImage(int value) {
    imageExists();
    return new BrightnessImpl().changeBrightness(this.image, value);
  }

  @Override
  public Image flipHoriz() {
    imageExists();
    return new HorizontalFlipperImpl().flipImage(this.image);
  }

  @Override
  public Image flipVert() {
    imageExists();
    return new VertFlipperImpl().flipImage(this.image);
  }
}
