package imgprocessor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import imgprocessor.model.BetterProcessorModelImpl;
import imgprocessor.model.Image;
import imgprocessor.model.ProcessorModel;
import imgprocessor.model.greyscale.BlueGreyscaleImpl;
import imgprocessor.model.greyscale.GreenGreyscaleImpl;
import imgprocessor.model.greyscale.RedGreyscaleImpl;
import imgprocessor.model.greyscale.ValueGreyscaleImpl;
import imgprocessor.view.ProcessorView;

/**
 * A ProcessorController Instance that is used to Control a Processor with a GUI.
 */
public class ViewProcessorControllerImpl extends ProcessorControllerImpl {

  private ProcessorView view;

  private int currImage;

  /**
   * Creates a controller with a specified view and processor.
   * @param view The view to use the controller with
   * @param model The model the controller will control
   * @throws IOException if it fails to create itself
   */
  public ViewProcessorControllerImpl(ProcessorView view, ProcessorModel model) throws IOException {
    this.currentCommand = new ArrayList<>();
    this.view = view;
    this.processor = model;
    this.betterProcessor = new BetterProcessorModelImpl();
    this.images = new HashMap<>();
    this.exec = new HashMap<>();
    this.currImage = 0;

    exec.put("load", new LoadImage());
    exec.put("save", new SaveImage());
    exec.put("value-component", new GreyImage(new ValueGreyscaleImpl()));
    exec.put("red-component", new GreyImage(new RedGreyscaleImpl()));
    exec.put("green-component", new GreyImage(new GreenGreyscaleImpl()));
    exec.put("blue-component", new GreyImage(new BlueGreyscaleImpl()));
    exec.put("brighten", new BrightenImage());
    exec.put("horizontal-flip", new HorizFlipImage());
    exec.put("vertical-flip", new VertFlipImage());
    if (processor.getClass() == this.betterProcessor.getClass()) {
      exec.put("greyscale", new LumaGreyImage());
      exec.put("sepia", new SepiaImage());
      exec.put("blur", new BlurImage());
      exec.put("sharpen", new SharpImage());
    }
  }

  @Override
  public void parseInput() {
    this.view.setCurrCommand(this::accept);
    this.view.makeVisible();
  }

  private void parseCommand(String input) throws IOException {
    Scanner scan = new Scanner(input);
    while (scan.hasNextLine()) {
      this.currentCommand = new ArrayList<>();
      Scanner currLine = new Scanner(scan.nextLine());
      while (currLine.hasNext()) {
        this.currentCommand.add(currLine.next());
      }
      if (exec.containsKey(this.currentCommand.get(0))) {
        this.currentCommand.add(Integer.toString(currImage));
        this.exec.get(this.currentCommand.get(0)).run();
        if (images.size() != 1) {
          currImage++;
        }
      } else if (this.currentCommand.get(0).equals("-file")) {
        File file = new File(this.currentCommand.get(1));
        BufferedReader readFile = new BufferedReader(new FileReader(file));
        parseCommand(readFile.toString());
        break;
      } else {
        throw new IOException("invalid command");
      }
    }
  }

  @Override
  public void accept(String input) {
    try {
      parseCommand(input);
    } catch (Exception exception) {
      view.sendMessage(exception.getMessage());
    }
    view.setImage(images.get(Integer.toString(currImage)).getImage());
    view.refresh();
  }


  @Override
  public HashMap<String, Image> currentImages() {
    return null;
  }
}
