package imgprocessor.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.OverlayLayout;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

/**
 * Implements the ProcessorView Interface.
 * It creates a gui to run a ProcessorController from.
 */
public class ProcessorViewImpl extends JFrame implements ProcessorView {

  private final JMenuBar menu = new JMenuBar();

  private JTextArea input;

  private final JLabel imageName;

  private final JLabel fileSaveLabel = new JLabel();

  private Consumer<String> currCommand;

  private JPanel histogram;

  private JPanel graph;

  private int[][][] currImage;

  /**
   * Creates a ProcessorViewImpl instance.
   * Makes the GUI with the menus and text input section.
   */
  public ProcessorViewImpl() {
    super();
    this.setBackground(Color.GRAY);
    this.setTitle("Image Processor");
    this.setSize(800, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    makeFileMenu();
    makeFuncMenu();
    this.setJMenuBar(menu);

    imageName = new JLabel();
    JScrollPane scroll = new JScrollPane(imageName);
    scroll.setPreferredSize(new Dimension(400, 500));
    this.add(scroll, BorderLayout.CENTER);

    buildTextField();
    buildHistogram();
    currCommand = null;
    this.pack();
  }

  private void makeFuncMenu() {
    JMenu funcMenu = new JMenu("Edit");
    funcMenu.add(makeItem("Blur", "blur"));
    funcMenu.add(makeItem("Sharpen", "sharpen"));
    funcMenu.add(makeItem("sepia", "sepia"));

    JMenuItem bright = new JMenuItem("Brighten");
    bright.addActionListener((ActionEvent e) -> {
      if (currCommand != null) {
        String s = (String) JOptionPane.showInputDialog(this, "Brighten: ",
                "Brighten", JOptionPane.PLAIN_MESSAGE, null, null,
                "1");
        if ((s != null) && (s.length() > 0)) {
          currCommand.accept("brighten " + s);
        }
        currCommand.accept(this.input.getText());
      }
    });
    funcMenu.add(bright);


    JMenu greyscale = new JMenu("Greyscale");
    greyscale.add(makeItem("Luma Greyscale", "greyscale"));
    greyscale.add(makeItem("Value Greyscale", "value-component"));
    greyscale.add(makeItem("Red Component", "red-component"));
    greyscale.add(makeItem("Green Component", "green-component"));
    greyscale.add(makeItem("Blue Component", "blue-component"));
    funcMenu.add(greyscale);

    JMenu flip = new JMenu("Flip");
    flip.add(makeItem("Horizontal", "horizontal-flip"));
    flip.add(makeItem("Vertical", "vertical-flip"));
    funcMenu.add(flip);

    menu.add(funcMenu);
  }

  private JMenuItem makeItem(String name, String menuItemCommand) {
    JMenuItem item = new JMenuItem(name);
    item.addActionListener((ActionEvent e) -> {
      if (currCommand != null) {
        currCommand.accept(menuItemCommand);
      }
    });
    return item;
  }

  private void makeFileMenu() {
    JMenu fileSubmenu = new JMenu("File");
    JMenuItem load = new JMenuItem("Load Image");
    final JFileChooser loading =
            new JFileChooser(FileSystemView.getFileSystemView());
    load.addActionListener((ActionEvent e) -> {
      int returnValue = loading.showOpenDialog(null);
      if (currCommand != null && returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = loading.getSelectedFile();
        currCommand.accept("load " + selectedFile.toString());
      }
    });
    fileSubmenu.add(load);

    JMenuItem save = new JMenuItem("Save Image");
    save.addActionListener((ActionEvent e) -> {
      final JFileChooser saver = new JFileChooser(".");
      int returnValue = saver.showSaveDialog(ProcessorViewImpl.this);
      if (currCommand != null && returnValue == JFileChooser.APPROVE_OPTION) {
        File imageToSave = saver.getSelectedFile();
        currCommand.accept("save " + imageToSave.toString());
        fileSaveLabel.setText(imageToSave.getAbsolutePath());
      }
    });
    fileSubmenu.add(save);
    menu.add(fileSubmenu);
  }

  private void buildTextField() {
    JPanel textBox = new JPanel();
    BoxLayout boxlayout = new BoxLayout(textBox, BoxLayout.Y_AXIS);
    textBox.setLayout(boxlayout);
    textBox.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
    this.add(textBox, BorderLayout.WEST);
    JLabel instructions = new JLabel("Enter commands here");
    textBox.add(instructions);
    input = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(input);
    scrollPane.setPreferredSize(new Dimension(20, 10));
    textBox.add(scrollPane);
    JButton runButton = new JButton("Run");
    runButton.addActionListener((ActionEvent e) -> {
      if (currCommand != null) {
        currCommand.accept(input.getText());
      }
    });
    textBox.add(runButton);
  }

  private void buildHistogram() {
    this.graph = new JPanel();
    histogram = new JPanel();
    histogram.setPreferredSize(new Dimension(400, 250));
    histogram.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    histogram.setLayout(new BorderLayout());
    histogram.add(graph, BorderLayout.CENTER);
    this.add(histogram, BorderLayout.EAST);
  }

  private void refreshHistogram(HashMap<Integer, int[]> freq) {
    int[] bounds = findBounds(freq);
    Color[] color = new Color[4];
    color[0] = new Color(255, 0, 0, 100);
    color[1] = new Color(0, 255, 0, 100);
    color[2] = new Color(0, 0, 255, 100);
    color[3] = new Color(0, 0, 0, 100);

    graph.removeAll();

    graph.setLayout(new BoxLayout(graph, 2));

    for (int i = 0; i < 256; i++) {
      JPanel line = new JPanel();
      line.setLayout(new OverlayLayout(line));

      for (int j = 0; j < 4; j++) {
        JPanel newLine = new JPanel();
        newLine.setBackground(color[j]);
        int original = freq.get(i)[j];
        int size = (int) (((original - bounds[2 * j]) /
                (double) (bounds[2 * j + 1] - bounds[2 * j])) * 250);
        newLine.setPreferredSize(new Dimension(1, size));
        newLine.setMaximumSize(new Dimension(1, size));
        newLine.setAlignmentY(1);
        newLine.setVisible(true);
        line.add(newLine);
      }
      graph.add(line);
    }

    graph.revalidate();
    graph.repaint();
    histogram.add(graph, BorderLayout.CENTER);

    JLabel xAxis = new JLabel("0                                                         " +
            "                   255");
    histogram.add(xAxis, BorderLayout.PAGE_END);

    JTextArea key = new JTextArea("Key: \nRed: Red Frequency \nBlue: Blue Frequency " +
            "\nGreen: Green Frequency \nGrey: Alpha");
    key.setEditable(false);
    histogram.add(key, BorderLayout.NORTH);

    histogram.repaint();
  }

  private int[] findBounds(HashMap<Integer, int[]> freq) {
    int[] bounds = new int[]{freq.get(1)[0], freq.get(1)[0],
            freq.get(1)[1], freq.get(1)[1],
            freq.get(1)[2], freq.get(1)[2],
            freq.get(1)[3], freq.get(1)[3]};

    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 8; j += 2) {
        bounds[j] = Math.min(bounds[j], freq.get(i)[j / 2]);
        bounds[j] = Math.min(bounds[j], freq.get(i)[j / 2]);
        bounds[j + 1] = Math.max(bounds[j + 1], freq.get(i)[j / 2]);
      }
    }
    return bounds;
  }

  private HashMap<Integer, int[]> getFreq(int[][][] image) throws IllegalArgumentException {
    HashMap<Integer, int[]> frequencies = new HashMap<>();
    for (int k = 0; k < 256; k++) {
      frequencies.put(k, new int[]{0, 0, 0, 0});
    }
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int r = image[i][j][0];
        int g = image[i][j][1];
        int b = image[i][j][2];
        int[] currValues = new int[]{r, g, b, (int) ((r + g + b) / 3.0)};
        for (int m = 0; m < 4; m++) {
          int[] currentFreq = frequencies.get(currValues[m]);
          currentFreq[m] += 1;
          frequencies.put(currValues[m], currentFreq);
        }
      }
    }
    return frequencies;
  }


  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCurrCommand(Consumer<String> command) {
    currCommand = command;
  }

  @Override
  public String getCommand() {
    String command = this.input.getText();
    this.input.setText("");
    return command;
  }

  @Override
  public void sendMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setImage(int[][][] image) {
    try {
      BufferedImage bufferedImage = makeBuffered(image);
      imageName.setIcon(new ImageIcon(bufferedImage));
      this.currImage = image;
      HashMap<Integer, int[]> freq = getFreq(this.currImage);
      refreshHistogram(freq);
    } catch (IOException e) {
      this.sendMessage(e.toString());
    }
  }

  @Override
  public int[][][] getImage() {
    return this.currImage;
  }

  private BufferedImage makeBuffered(int[][][] rgb) throws IOException {
    BufferedImage output = new BufferedImage(rgb[0].length, rgb.length, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < rgb.length; i++) {
      for (int j = 0; j < rgb[0].length; j++) {
        int r = rgb[i][j][0];
        int g = rgb[i][j][1];
        int b = rgb[i][j][2];
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    return output;
  }

  @Override
  public void refresh() {
    this.input.requestFocusInWindow();
    input.setText("");
    this.repaint();
  }
}
