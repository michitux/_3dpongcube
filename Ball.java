import processing.visualcube1e3.simulator.*;
import processing.visualcube1e3.*;
import processing.visualcube1e3.device.*;


import java.util.Random;

class Ball implements Element {
  Vector position;
  int hashCode;

  Vector movement;

  HardwareCube cube;

  Side lastCollision;

  VisualCube.Color color = new VisualCube.Color(200, 0, 0);

  public Ball(HardwareCube cube) {
    this.hashCode = (new Random()).nextInt();
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
             movement.incrementInDirection();
             int red = (color.r + 14)%VisualCube.colors;
             int green = (color.r + 1)%VisualCube.colors;
             int blue = (color.r +8)%VisualCube.colors;
             color = new VisualCube.Color(red, green, blue);
            i = 0;
          }
        }
      }
    });
    initRandom();
    mover.start();
  }

  private void move() {
    removePixel();
    position.add(movement);
    if (position.getX() > (cube.getWidth()-1)*100) {
      position.setX((cube.getWidth()-1)*100);
    }
    if (position.getY() > (cube.getDepth()-1)*100) {
      position.setY((cube.getDepth()-1)*100);
    }
    if (position.getZ() > (cube.getHeight()-1)*100) {
      position.setZ((cube.getHeight()-1)*100);
    }
    
    if (position.getX() < 0) {
      position.setX(0);
    }
    
    if (position.getY() < 0) {
      position.setY(0);
    }
    
    if (position.getZ() < 0) {
      position.setZ(0);
    }
    
    // update pixel

    paintPixel();

    Side collisionSide = null;

    if (position.getX() == 0) {
      collisionSide = cube.getLeft();
    } else if (position.getX() == (cube.getWidth()-1)*100) {
      collisionSide = cube.getRight();
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
      movement = collisionSide.reflect(movement, position);
      if (movement == null) {
        collisionSide.lost();
        if (lastCollision != null) {
          lastCollision.won();
          lastCollision = null;
        }
        removePixel();
        initRandom();
      } else if (collisionSide instanceof Racket) {
        ((Racket)collisionSide).setTempColor(this.color, 700);
        lastCollision = collisionSide;
      }
    }
  }

  private void initRandom() {
    Random random = new Random();
    this.position = new Vector((cube.getWidth()/2)*100, (cube.getDepth()/2)*100, (cube.getHeight()/2)*100);
    this.movement = new Vector(random.nextInt(20)-10, random.nextInt(20)-10, random.nextInt(20)-10);
    //this.movement = new Vector(0, 8, 10);
  }

  private void paintPixel() {
    for (int x = position.getX()/100-1; x < position.getX()/100 + 2; x++) {
      for (int y = position.getY()/100 - 1; y < position.getY()/100 + 2; y++) {
        for (int z = position.getZ()/100 - 1; z < position.getZ()/100 + 2; z++) {
          if (x >= 0 && y >= 0 && z >= 0 && x < cube.getWidth() && y < cube.getDepth() && z < cube.getHeight()) {
            int xDiff = Math.abs(x * 100 - position.getX());
            int yDiff = Math.abs(y * 100 - position.getY());
            int zDiff = Math.abs(z * 100 - position.getZ());
            double diff = Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);
            int green = (int)(100 - diff) * this.color.g;
            int red = (int)(100 - diff) * this.color.r;
            int blue = (int)(100 - diff) * this.color.b;
            if (green < 0) green = 0;
            if (red < 0) red = 0;
            if (blue < 0) blue = 0;
            if (green == 0 && red == 0 && blue == 0) {
              cube.getPixel(x, y, z).removeElement(this);
            } else {
              cube.getPixel(x, y, z).setElement(this, new VisualCube.Color(red, green, blue));
            }
          }
        }
      }
    }
  }
  
  private void removePixel() {
    for (int x = position.getX()/100-1; x < position.getX()/100 + 2; x++) {
      for (int y = position.getY()/100 - 1; y < position.getY()/100 + 2; y++) {
        for (int z = position.getZ()/100 - 1; z < position.getZ()/100 + 2; z++) {
          if (x >= 0 && y >= 0 && z >= 0 && x < cube.getWidth() && y < cube.getDepth() && z < cube.getHeight()) {
            cube.getPixel(x, y, z).removeElement(this);
          }
        }
      }
    }
  }
}
