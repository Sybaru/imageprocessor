package imgprocessor.view;

import java.util.function.Consumer;

/**
 * This interface represents a GUI for a ProcessorController.
 * It allows the user to interact with the ProcessorController through a GUI instead of the
 * command line.
 */
public interface ProcessorView {

  void makeVisible();

  void setCurrCommand(Consumer<String> callback);

  String getCommand();

  void sendMessage(String error);

  void setImage(int[][][] image);

  int[][][] getImage();

  void refresh();

}
