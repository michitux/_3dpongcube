
import java.awt.Color;

class Racket extends Side {
  int leftX;
  int rightX;
  int topY;
  int bottomY;

  private Color color = Color.BLUE;

  private Side side;

  public Racket(HardwareCube cube, Side side) {
    super(cube);
    this.side = side;
    this.leftX = side.getWidth()/3;
    this.rightX = side.getWidth()*2/3;
    this.bottomY = side.getHeight()/3;
    this.topY = side.getHeight()*2/3;
  }

  public Vector reflect(Vector v) {
    if (doesReflect(v)) {
      return side.reflect(v);
    } else {
      return null;
    }
  }
  public boolean doesReflect(Vector v) {
    return (leftX <= side.getVectorX(v) && rightX > side.getVectorX(v) && topY > side.getVectorY(v) && bottomY <= side.getVectorY(v));
  }

  public void moveLeft() {
    if (leftX > 0) {
      for (int i = bottomY; i < topY; i++) {
        side.getPixel(rightX, i).removeElement(this);
      }

      leftX--;
      rightX--;

      for (int i = bottomY; i < topY; i++) {
        side.getPixel(leftX, i).setElement(this, color);
      }
    }
  }

  public void moveRight() {
    if (rightX < side.getWidth()) {
      for (int i = bottomY; i < topY; i++) {
        side.getPixel(leftX, i).removeElement(this);
      }

      leftX++;
      rightX++;

      for (int i = bottomY; i < topY; i++) {
        side.getPixel(rightX, i).setElement(this, color);
      }
    }
  }

  public void moveUp() {
    if (topY < side.getHeight()) {
      for (int i = leftX; i < rightX; i++) {
        side.getPixel(i, bottomY).removeElement(this);
      }

      topY++;
      bottomY++;

      for (int i = leftX; i < rightX; i++) {
        side.getPixel(i, topY).setElement(this, color);
      }
    }
  }

  public void moveDown() {
    if (bottomY > 0) {
      for (int i = leftX; i < rightX; i++) {
        side.getPixel(i, bottomY).removeElement(this);
      }

      topY--;
      bottomY--;

      for (int i = leftX; i < rightX; i++) {
        side.getPixel(i, topY).setElement(this, color);
      }
    }
  }

  public int getVectorX(Vector v) {
    return side.getVectorX(v);
  }

  public int getVectorY(Vector v) {
    return side.getVectorY(v);
  }

  public HardwarePixel getPixel(int x, int y) {
    return side.getPixel(x, y);
  }

  public int getHeight() {
    return side.getHeight();
  }

  public int getWidth() {
    return side.getWidth();
  }

  public void won() {
    this.color = Color.RED;
  }
}
