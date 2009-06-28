import processing.visualcube1e3.simulator.*;
import processing.visualcube1e3.*;
import processing.visualcube1e3.device.*;

import ddf.minim.*;

class HardwareCube {
  HardwarePixel[][][] pixels = new HardwarePixel[VisualCube.width][VisualCube.depth][VisualCube.height];

  Side top;
  Side left;
  Side right;
  Side bottom;
  Side front;
  Side back;

  public HardwareCube(VisualCube cube, Minim minim) {
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[0].length; j++) {
        for (int k = 0; k < pixels[0][0].length; k++) {
          pixels[i][j][k] = new HardwarePixel(i, j, k, cube);
        }
      }
    }

    this.top = new Racket(this, new TopSide(this, minim), minim, new VisualCube.Color(100, 230, 30));
    this.left = new LeftSide(this, minim);
    this.right = new RightSide(this, minim);
    this.bottom = new Racket(this, new BottomSide(this, minim), minim, new VisualCube.Color(240, 100, 20));
    this.front = new Racket(this, new FrontSide(this, minim), minim, new VisualCube.Color(50, 150, 200));
    this.back = new Racket(this, new BackSide(this, minim), minim, new VisualCube.Color(100, 20, 200));
    Ball ball = new Ball(this);
  }
  
  public void gameFinished() {
    ((Racket)this.top).getScore().reset();
    ((Racket)this.bottom).getScore().reset();
    ((Racket)this.front).getScore().reset();
    ((Racket)this.back).getScore().reset();
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
