package imgprocessor.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static int[][][] readPPM(String filename) throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IOException("file not found");
    }
    String extension = filename.substring(filename.indexOf(".") + 1);
    if (!extension.equals("ppm")) {
      return readFile(filename);
    }


    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    int[][][] image = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        image[i][j][0] = sc.nextInt();
        image[i][j][1] = sc.nextInt();
        image[i][j][2] = sc.nextInt();
      }
    }
    return image;
  }

  /**
   * Reads a file in png, jpg, or bmp format.
   * @param filename the file to be read
   * @return an int[][][] array representing the file
   * @throws IOException if the file does not exist
   */
  public static int[][][] readFile(String filename) throws IOException {
    BufferedImage image = ImageIO.read(new FileInputStream(filename));
    int[][][] imageArray = new int[image.getHeight()][image.getWidth()][3];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int color = image.getRGB(j, i);
        Color c = new Color(color);
        imageArray[i][j][0] = c.getRed();
        imageArray[i][j][1] = c.getGreen();
        imageArray[i][j][2] = c.getBlue();
      }
    }
    return imageArray;
  }

  /**
   * Saves an image to ppm format.
   * @param filename The location to save the image.
   * @param image the image to be saved
   * @throws IOException If it fails to save the image.
   */
  public static void savePPM(String filename, Image image) throws IOException {
    String extension = filename.substring(filename.indexOf(".") + 1);
    if (!extension.equals("ppm")) {
      saveFile(filename, image);
      return;
    }
    int height = image.imageHeight();
    int width = image.imageWidth();
    int[][][] imageArray = image.getImage();
    BufferedWriter outputImage = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(filename)));
    outputImage.write("P3");
    outputImage.newLine();
    outputImage.write(image.imageWidth() + " " + image.imageHeight());
    outputImage.newLine();
    outputImage.write("255");
    outputImage.newLine();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        outputImage.write(imageArray[i][j][0] + " ");
        outputImage.write(imageArray[i][j][1] + " ");
        outputImage.write(imageArray[i][j][2] + "");
        if (j < width - 1) {
          outputImage.write(" ");
        }
      }
      outputImage.newLine();
    }
    outputImage.flush();
    outputImage.close();
  }

  /**
   * Saves a file in png, bmp, or jpg format.
   * @param filename The location to save the image.
   * @param image the image to be saved
   * @throws IOException If it fails to save the image.
   */
  public static void saveFile(String filename, Image image) throws IOException {
    int height = image.imageHeight();
    int width = image.imageWidth();
    int[][][] imageArray = image.getImage();
    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int color = imageArray[i][j][0];
        color = (color << 8) + imageArray[i][j][1];
        color = (color << 8) + imageArray[i][j][2];
        outputImage.setRGB(j, i, color);
      }
    }
    String fileType = filename.substring(filename.indexOf(".") + 1);
    ImageIO.write(outputImage, fileType, new File(filename));
  }
}

