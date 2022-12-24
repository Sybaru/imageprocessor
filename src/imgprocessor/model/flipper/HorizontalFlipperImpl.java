package imgprocessor.model.flipper;

/**
 * Extends the AFlipper abstract class.
 * Changes the flipHelper function to flip the image horizontally instead.
 */
public class HorizontalFlipperImpl extends AFlipper {

  @Override
  public int[][][] flipHelper(int width, int height, int[][][] original) {
    int[][][] flipImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width / 2; j++) {
        flipImage[i][j] =  original[i][width - 1 - j];
        flipImage[i][width - 1 - j] = original[i][j];
      }
    }
    return flipImage;
  }
}
