package imgprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import imgprocessor.controller.ProcessorController;
import imgprocessor.controller.ProcessorControllerImpl;
import imgprocessor.controller.ViewProcessorControllerImpl;
import imgprocessor.model.BetterProcessorModelImpl;
import imgprocessor.model.ProcessorModel;
import imgprocessor.view.ProcessorView;
import imgprocessor.view.ProcessorViewImpl;

/**
 * Runs scriptFile.txt in ProcessorController.
 */
public class RunScript {

  /**
   * Runs scriptFile.txt in ProcessorController.
   * @param args unused
   * @throws IOException if file cant be read
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      ProcessorModel model = new BetterProcessorModelImpl();
      ProcessorView view = new ProcessorViewImpl();
      ProcessorController control = new ViewProcessorControllerImpl(view, model);
      control.parseInput();
    } else if (args[0].equals("-file")) {
      BufferedReader reader = new BufferedReader( new StringReader(args[0] + " " + args[1]));
      ProcessorController controller = new ProcessorControllerImpl(reader,
              new BetterProcessorModelImpl());
      controller.parseInput();
    } else {
      BufferedReader reader = new BufferedReader(
              new InputStreamReader(System.in));
      ProcessorController controller = new ProcessorControllerImpl(reader,
              new BetterProcessorModelImpl());
      controller.parseInput();
    }
  }
}
