import processing.visualcube1e3.simulator.*;
import processing.visualcube1e3.*;
import processing.visualcube1e3.device.*;

class HardwareCube {
  HardwarePixel[][][] pixels = new HardwarePixel[VisualCube.width][VisualCube.depth][VisualCube.height];

  Side top;
  Side left;
  Side right;
  Side bottom;
  Side front;
  Side back;

  public HardwareCube(VisualCube cube) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        for (int k = 0; k < pixels[0][0].length; k++) {
          System.out.println(i + " " + j + " " + k);
          pixels[i][j][k] = new HardwarePixel(i, j, k, cube);
        }
      }
    }

    this.top = new TopSide(this);
    this.left = new LeftSide(this);
    this.right = new RightSide(this);
    this.bottom = new BottomSide(this);
    this.front = new FrontSide(this);
    this.back = new BackSide(this);
  }

  public HardwarePixel getPixel(int x, int y, int z) {
    return pixels[x][y][z];
  }

  public int getWidth() {
    return VisualCube.width;
  }

  public int getHeight() {
    return VisualCube.height;
  }

  public int getDepth() {
    return VisualCube.depth;
  }

  /**
   * Gets the top for this instance.
   *
   * @return The top.
   */
  public Side getTop() {
    return this.top;
  }

  /**
   * Gets the left for this instance.
   *
   * @return The left.
   */
  public Side getLeft() {
    return this.left;
  }

  /**
   * Gets the right for this instance.
   *
   * @return The right.
   */
  public Side getRight() {
    return this.right;
  }

  /**
   * Gets the bottom for this instance.
   *
   * @return The bottom.
   */
  public Side getBottom() {
    return this.bottom;
  }

  /**
   * Gets the front for this instance.
   *
   * @return The front.
   */
  public Side getFront() {
    return this.front;
  }

  /**
   * Gets the back for this instance.
   *
   * @return The back.
   */
  public Side getBack() {
    return this.back;
  }
}
