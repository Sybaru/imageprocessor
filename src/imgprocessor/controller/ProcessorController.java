package imgprocessor.controller;

import java.io.IOException;
import java.util.HashMap;

import imgprocessor.model.Image;

/**
 * This interface represents a controller for a ProcessorModel.
 * The interface can be used to run a ProcessorModel from any Readable type input.
 */
public interface ProcessorController {

  /**
   * Takes the user's input from the readable and parses it.
   * After parsing, runs the user's commands through calls to the ProcessorModel.
   *
   * @throws IOException if a user command is invalid.
   */
  void parseInput() throws IOException;

  /**
   * Returns the current images stored in the controller.
   * @return A hashmap containing the names of the images and the images.
   */
  HashMap<String, Image> currentImages();

  /**
   * Accepts a command from the view.
   * @param input the command to be accepted
   */
  void accept(String input) throws IOException;

}
