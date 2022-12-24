package imgprocessor.model;

import imgprocessor.model.colortransform.ColorTransformImpl;
import imgprocessor.model.filter.FilterImpl;

/**
 * Implements the BetterProcessorModel interface.
 * Extends the ProcessorModelImpl class.
 * Carries out all the functions of the ProcessorModel with additional functions such as
 * blur, sharpen, lumaGreyscale, and sepia.
 */
public class BetterProcessorModelImpl extends ProcessorModelImpl implements BetterProcessorModel {

  /**
   * Creates a BetterProcessorModelImpl instance with a null image.
   */
  public BetterProcessorModelImpl() {
    this.image = null;
  }

  @Override
  public Image blur() {
    imageExists();
    double[][] blur = {{0.0625, 0.125, 0.0625},
                          {0.125, 0.25, 0.125},
                          {0.0625, 0.125, 0.0625}};
    return new FilterImpl().filter(this.image, blur);
  }

  @Override
  public Image sharpen() {
    imageExists();
    double[][] sharpenFilter = {{-0.125, -0.125, -0.125, -0.125, -0.125},
                                   {-0.125, 0.25, 0.25, 0.25, -0.125},
                                   {-0.125, 0.25, 1, 0.25, -0.125},
                                   {-0.125, 0.25, 0.25, 0.25, -0.125},
                                   {-0.125, -0.125, -0.125, -0.125, -0.125}};
    return new FilterImpl().filter(this.image, sharpenFilter);
  }

  @Override
  public Image lumaGreyscale() {
    imageExists();
    double[][] greyscale = {{0.2126, 0.7152, 0.0722},
                               {0.2126, 0.7152, 0.0722},
                               {0.2126, 0.7152, 0.0722}};
    return new ColorTransformImpl().transform(this.image, greyscale);
  }

  @Override
  public Image sepia() {
    imageExists();
    double[][] sepia = {{0.393, 0.769, 0.189},
                           {0.349, 0.686, 0.168},
                           {0.272, 0.534, 0.131}};
    return new ColorTransformImpl().transform(this.image, sepia);
  }

}
