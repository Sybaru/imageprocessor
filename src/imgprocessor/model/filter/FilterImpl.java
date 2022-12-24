package imgprocessor.model.filter;

import imgprocessor.model.Image;
import imgprocessor.model.ImageImpl;

/**
 * Implements the Filter interface.
 * Used to apply filters to images.
 */
public class FilterImpl implements Filter {

  @Override
  public Image filter(Image image, double[][] filter) {
    if (filter.length % 2 == 0 || filter.length != filter[0].length) {
      throw new IllegalArgumentException();
    }
    int width = image.imageWidth();
    int height = image.imageHeight();
    int[][][] original = image.getImage();
    int[][][] filterImage = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int[][][] kernel = findKernel(i, j, original, filter.length);
        filterImage[i][j] = filterColor(kernel, filter);
      }
    }
    return new ImageImpl(filterImage);
  }

  private int[] filterColor(int[][][] kernel, double[][] filter) {
    int[] filteredColor = new int[3];
    for (int i = 0; i < filter.length; i++) {
      for (int j = 0; j < filter.length; j++) {
        filteredColor[0] += (int) Math.round((kernel[i][j][0] * filter[i][j]));
        filteredColor[1] += (int) Math.round((kernel[i][j][1] * filter[i][j]));
        filteredColor[2] += (int) Math.round((kernel[i][j][2] * filter[i][j]));
      }
    }
    filteredColor[0] = constrain(filteredColor[0]);
    filteredColor[1] = constrain(filteredColor[1]);
    filteredColor[2] = constrain(filteredColor[2]);
    return filteredColor;
  }

  private int[][][] findKernel(int x, int y, int[][][] image, int length) {
    int[][][] kernel = new int[length][length][3];
    int margin = (int) Math.floor(length / 2.0);
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        if (withinMargin(i + (x - margin), j + (y - margin), image)) {
          kernel[i][j][0] = image[i + (x - margin)][j + (y - margin)][0];
          kernel[i][j][1] = image[i + (x - margin)][j + (y - margin)][1];
          kernel[i][j][2] = image[i + (x - margin)][j + (y - margin)][2];
        } else {
          kernel[i][j][0] = 0;
          kernel[i][j][1] = 0;
          kernel[i][j][2] = 0;
        }
      }
    }
    return kernel;
  }

  private boolean withinMargin(int x, int y, int[][][] image) {
    return (x >= 0 && x < image.length) && (y >= 0 && y < image[0].length);
  }

  private int constrain(int value) {
    if (value > 255) {
      value = 255;
    } else if (value < 0) {
      value = 0;
    }
    return value;
  }
}
