package imgprocessor.controller;

import org.junit.Test;

import java.io.IOException;

import imgprocessor.model.BetterProcessorModel;
import imgprocessor.model.BetterProcessorModelImpl;
import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;
import imgprocessor.view.ProcessorView;
import imgprocessor.view.ProcessorViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ViewProcessorControllerImpl class.
 */
public class ViewProcessorControllerImplTest {

  @Test
  public void parseInput() throws IOException {
    ProcessorView view = new ProcessorViewImpl();
    ProcessorView otherView = new ProcessorViewImpl();
    BetterProcessorModel model = new BetterProcessorModelImpl();
    ViewProcessorControllerImpl test = new ViewProcessorControllerImpl(view, model);
    test.accept("load res/pigeon.ppm");
    Image image = new ImageImpl("res/pigeon.ppm");
    otherView.setImage(image.getImage());
    assertEquals(otherView.getImage(), view.getImage());

  }
}