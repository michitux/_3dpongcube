
import java.awt.Color;

import java.util.Random;

class Ball implements Element {
  Vector position;
  int hashCode;

  Vector movement;

  HardwareCube cube;

  Side lastCollision;

  Color color = Color.GREEN;

  public Ball(HardwareCube cube) {
    this.hashCode = (new Random).nextInt();
    this.cube = cube;
    Thread mover = new Thread(new Runnable() {
      public void run() {
        int i = 0;
        while (true) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          move();
          i++;
          if (i%10 == 0) {
            movement.incrementInDirection(2);
            i = 0;
          }
        }
      }
    });
    initRandom();
    mover.start();
  }

  private void move() {
    position.add(movement);
    if (position.getX() > (cube.getWidth()-1)*100) {
      position.setX((cube.getWidth()-1)*100);
    }
    if (position.getY() > (cube.getDepth()-1)*100) {
      position.setX((cube.getDepth()-1)*100);
    }
    if (position.getZ() > (cube.getHeight()-1)*100) {
      position.setZ((cube.getHeight()-1)*100);
    }
    // update pixel

    paintPixel();

    Side collisionSide = null;

    if (position.getX() == 0) {
      collisionSide = cube.getRight();
    } else if (position.getX() == (cube.getWidth()-1)*100) {
      collisionSide = cube.getLeft();
    } else if (position.getY() == 0) {
      collisionSide = cube.getFront();
    } else if (position.getY() == (cube.getDepth()-1)*100) {
      collisionSide = cube.getBack();
    } else if (position.getZ() == 0) {
      collisionSide = cube.getBottom();
    } else if (position.getZ() == (cube.getHeight()-1)*100) {
      collisionSide = cube.getTop();
    }

    if (collisionSide != null) {
      movement = collisionSide.reflect(movement);
      if (movement == null) {
        if (lastCollision != null) {
          lastCollision.won();
        }
        initRandom();
      } else if (collisionSide instanceof Racket) {
        lastCollision = collisionSide;
      }
    }
  }

  private void initRandom() {
    Random random = new Random();
    this.position = new Vector((cube.getWidth()/2)*100, (cube.getDepth()/2)*100, (cube.getHeight()/2)*100);
    this.movement = new Vector(random.nextInt(cube.getWidth()), random.nextInt(cube.getDepth()), random.nextInt(cube.getHeight()));
  }

  private void paintPixel() {
    for (int x = position.getX()/100-2; x < position.getX()/100 + 3; x++) {
      for (int y = position.getY()/100 - 2; y < position.getY()/100 + 3; y++) {
        for (int z = position.getZ()/100 - 2; z < position.getZ()/100 + 3; z++) {
          if (x >= 0 && y >= 0 && z >= 0 && x < cube.getWidth() && y < cube.getDepth() && z < cube.getHeight()) {
            int xDiff = Math.abs(x * 100 - position.getX());
            int yDiff = Math.abs(y * 100 - position.getY());
            int zDiff = Math.abs(z * 100 - position.getZ());
            double diff = Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);
            int green = (int)(100 - diff) * this.color.getGreen();
            int red = (int)(100 - diff) * this.color.getRed();
            int blue = (int)(100 - diff) * this.color.getBlue();
            if (green == 0 && red == 0 && blue == 0) {
              //cube.getPixel(x, y, z).removeElement(this);
            } else {
              //cube.getPixel(x, y, z).setElement(this, new Color(red, green, blue));
            }
          }
        }
      }
    }
  }
}
