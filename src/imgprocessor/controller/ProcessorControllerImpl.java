package imgprocessor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import imgprocessor.model.BetterProcessorModel;
import imgprocessor.model.BetterProcessorModelImpl;
import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;
import imgprocessor.model.ProcessorModel;
import imgprocessor.model.ProcessorModelImpl;
import imgprocessor.model.greyscale.BlueGreyscaleImpl;
import imgprocessor.model.greyscale.GreenGreyscaleImpl;
import imgprocessor.model.greyscale.Greyscale;
import imgprocessor.model.greyscale.RedGreyscaleImpl;
import imgprocessor.model.greyscale.ValueGreyscaleImpl;

/**
 * This represents the Controller for a ProcessorModel.
 * It uses private classes to run methods inside a ProcessorModel.
 */
public class ProcessorControllerImpl implements ProcessorController {

  protected Readable read;

  protected ArrayList<String> currentCommand;

  protected ProcessorModel processor;

  protected BetterProcessorModel betterProcessor;

  protected HashMap<String, Runnable> exec;

  protected HashMap<String, Image> images;

  /**
   * Default constructor for the controller.
   * Creates a ProcessorControllerImpl with an input from System.in and a blank ProcessorModel.
   */
  public ProcessorControllerImpl() throws IOException {
    this(new InputStreamReader(System.in), new ProcessorModelImpl());
  }

  /**
   * Creates a controller with a specified readable and processor.
   * @param read the readable that contains the user input.
   * @param processor the ProcessorModel that the user wants to use.
   */
  public ProcessorControllerImpl(Readable read, ProcessorModel processor) {
    this.currentCommand = new ArrayList<>();
    this.read = read;
    this.processor = processor;
    this.betterProcessor = new BetterProcessorModelImpl();
    this.images = new HashMap<>();
    this.exec = new HashMap<>();
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

  /**
   * Takes the user's input from the readable and parses it.
   * After parsing, runs the user's commands through helper methods that call the ProcessorModel.
   *
   * @throws IOException if a user command is invalid.
   */
  @Override
  public void parseInput() throws IOException {
    Scanner scan = new Scanner(read);
    while (scan.hasNextLine()) {
      this.currentCommand = new ArrayList<>();
      Scanner currLine = new Scanner(scan.nextLine());
      while (currLine.hasNext()) {
        this.currentCommand.add(currLine.next());
      }
      if (exec.containsKey(this.currentCommand.get(0))) {
        this.exec.get(this.currentCommand.get(0)).run();
      } else if (this.currentCommand.get(0).equals("-file")) {
        File file = new File(this.currentCommand.get(1));
        BufferedReader readFile = new BufferedReader(new FileReader(file));
        this.read = readFile;
        this.parseInput();
        break;
      } else {
        throw new IOException("invalid command");
      }
    }
  }

  @Override
  public HashMap<String, Image> currentImages() {
    return this.images;
  }

  @Override
  public void accept(String input) throws IOException {
    this.currentCommand.add(input);
    this.parseInput();
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's loadImage method.
   * Loads an image into the processor.
   */
  protected class LoadImage implements Runnable {

    @Override
    public void run() {
      if (currentCommand.size() < 2) {
        throw new IllegalArgumentException("failed to load image");
      }
      try {
        ImageImpl image = new ImageImpl(currentCommand.get(1));
        processor.loadImage(image);
        if (currentCommand.size() < 3) {
          images.put(Integer.toString(images.size()), image);
        } else {
          images.put(currentCommand.get(2), image);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's saveImage method.
   * Saves a image to a file.
   */
  protected class SaveImage implements Runnable {

    @Override
    public void run() {
      if (currentCommand.size() < 2) {
        throw new IllegalArgumentException("failed to save image");
      }
      try {
        processor.loadImage(images.get(currentCommand.get(2)));
        processor.saveImage(currentCommand.get(1));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's toGreyscale method.
   * Can preform 4 different kinds of greyscale based on the constructor.
   * Converts an image to greyscale.
   */
  protected class GreyImage implements Runnable {

    Greyscale grey;

    /**
     * Creates a GreyImage instance with a specified type of greyscaling.
     * @param grey A Greyscale class representing the type of Greyscaling to be done
     *             Can be red, green, blue, or highest value greyscaling.
     */
    public GreyImage(Greyscale grey) {
      this.grey = grey;
    }

    @Override
    public void run() {
      processor.loadImage(images.get(currentCommand.get(1)));
      if (currentCommand.size() < 3) {
        images.put(Integer.toString(images.size()), processor.toGreyscale(grey));
      } else {
        images.put(currentCommand.get(2), processor.toGreyscale(grey));
      }
    }
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's brightenImage method.
   * brightens/darkens an image based on input.
   */
  protected class BrightenImage implements Runnable {

    @Override
    public void run() {
      processor.loadImage(images.get(currentCommand.get(2)));
      Image image = processor.brightenImage(Integer.parseInt(currentCommand.get(1)));
      if (currentCommand.size() < 4) {
        images.put(Integer.toString(images.size()), image);
      } else {
        images.put(currentCommand.get(3), image);
      }
    }
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's flipHoriz method.
   * Flips an image horizontally.
   */
  protected class HorizFlipImage implements Runnable {

    @Override
    public void run() {
      processor.loadImage(images.get(currentCommand.get(1)));
      if (currentCommand.size() < 3) {
        images.put(Integer.toString(images.size()), processor.flipHoriz());
      } else {
        images.put(currentCommand.get(2), processor.flipHoriz());
      }
    }
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's flipVert method.
   * Flips an image Vertically.
   */
  protected class VertFlipImage implements Runnable {

    @Override
    public void run() {
      processor.loadImage(images.get(currentCommand.get(1)));
      if (currentCommand.size() < 3) {
        images.put(Integer.toString(images.size()), processor.flipVert());
      } else {
        images.put(currentCommand.get(2), processor.flipVert());
      }
    }
  }


  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's LumaGreyscale method.
   * Greyscales an image based on luma.
   */
  protected class LumaGreyImage implements Runnable {

    @Override
    public void run() {
      betterProcessor.loadImage(images.get(currentCommand.get(1)));
      if (currentCommand.size() < 3) {
        images.put(Integer.toString(images.size()), betterProcessor.lumaGreyscale());
      } else {
        images.put(currentCommand.get(2), betterProcessor.lumaGreyscale());
      }
    }
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's sepia method.
   * Applies a sepia filter to an image.
   */
  protected class SepiaImage implements Runnable {

    @Override
    public void run() {
      betterProcessor.loadImage(images.get(currentCommand.get(1)));
      if (currentCommand.size() < 3) {
        images.put(Integer.toString(images.size()), betterProcessor.sepia());
      } else {
        images.put(currentCommand.get(2), betterProcessor.sepia());
      }
    }
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's flipVert method.
   * Blurs an image.
   */
  protected class BlurImage implements Runnable {

    @Override
    public void run() {
      betterProcessor.loadImage(images.get(currentCommand.get(1)));
      if (currentCommand.size() < 3) {
        images.put(Integer.toString(images.size()), betterProcessor.blur());
      } else {
        images.put(currentCommand.get(2), betterProcessor.blur());
      }
    }
  }

  /**
   * Implements the Runnable Interface.
   * Contains a method, run, that calls the ProcessorModel's flipVert method.
   * Sharpens an image.
   */
  protected class SharpImage implements Runnable {

    @Override
    public void run() {
      betterProcessor.loadImage(images.get(currentCommand.get(1)));
      if (currentCommand.size() < 3) {
        images.put(Integer.toString(images.size()), betterProcessor.sharpen());
      } else {
        images.put(currentCommand.get(2), betterProcessor.sharpen());
      }
    }
  }


}
