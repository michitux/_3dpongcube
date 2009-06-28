import processing.visualcube1e3.simulator.*;
import processing.visualcube1e3.*;
import processing.visualcube1e3.device.*;

import ddf.minim.*;

class Racket extends Side {
  volatile int leftX;
  volatile int rightX;
  volatile int topY;
  volatile int bottomY;

  private volatile VisualCube.Color color;
  private VisualCube.Color baseColor;

  private Side side;
  private Score score = new Score();
  private AudioPlayer hit = minim.loadFile("/usr/share/sounds/pop.wav", 2048);
  private AudioPlayer miss = minim.loadFile("/usr/share/supertuxkart/data/sfx/wee.wav", 2048);

  public Racket(HardwareCube cube, final Side side, Minim minim, VisualCube.Color color) {
    super(cube, minim);
    this.side = side;
    this.leftX = side.getWidth()/3;
    this.rightX = side.getWidth()*2/3;
    this.bottomY = side.getHeight()/3;
    this.topY = side.getHeight()*2/3;
    this.color = color;
    this.baseColor = color;
    refresh();

  }

  public Score getScore() {
    return score;
  }
  public synchronized Vector reflect(Vector v, Vector p) {
    if (doesReflect(p)) {
      (new Thread(new Runnable() {
        public void run() {
          hit.rewind();
          hit.play();
        }
      })).start();
      return side.reflect(v, p);
    } else {
      (new Thread(new Runnable() {
        public void run() {
          miss.rewind();
          miss.play();
        }
      })).start();
      side.flash(new VisualCube.Color(200, 0, 0));
      return null;
    }
  }
  public boolean doesReflect(Vector v) {
    return (leftX <= side.getVectorX(v)/100 && rightX >= side.getVectorX(v)/100 && topY >= side.getVectorY(v)/100 && bottomY <= side.getVectorY(v)/100);
  }

  public synchronized void moveLeft() {
    if (leftX > 0) {
      for (int i = bottomY; i <= topY; i++) {
        side.getPixel(rightX, i).removeElement(this);
      }

      leftX--;
      rightX--;

      for (int i = bottomY; i <= topY; i++) {
        side.getPixel(leftX, i).setElement(this, color);
      }
    }
  }

  public synchronized void moveRight() {
    if (rightX < side.getWidth()-1) {
      for (int i = bottomY; i <= topY; i++) {
        side.getPixel(leftX, i).removeElement(this);
      }

      leftX++;
      rightX++;

      for (int i = bottomY; i <= topY; i++) {
        side.getPixel(rightX, i).setElement(this, color);
      }
    }
  }

  public synchronized  void moveUp() {
    if (topY < side.getHeight()-1) {
      for (int i = leftX; i <= rightX; i++) {
        side.getPixel(i, bottomY).removeElement(this);
      }

      topY++;
      bottomY++;

      for (int i = leftX; i <= rightX; i++) {
        side.getPixel(i, topY).setElement(this, color);
      }
    }
  }

  public synchronized void moveDown() {
    if (bottomY > 0) {
      for (int i = leftX; i <= rightX; i++) {
        side.getPixel(i, topY).removeElement(this);
      }

      topY--;
      bottomY--;

      for (int i = leftX; i <= rightX; i++) {
        side.getPixel(i, bottomY).setElement(this, color);
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

  public synchronized void won() {
    this.setTempColor(new VisualCube.Color(200, 200, 0), 1500);
    score.increment();
    if (score.getScore() == (side.getHeight()-1)) {
      side.flash(new VisualCube.Color(0, 200, 0));
      cube.gameFinished();
    }
    refresh();
  }
  
  public synchronized void lost() {
  }
  
  public synchronized void setColor(VisualCube.Color color) {
    this.color = color;
    refresh();
  }
  
  public void setTempColor(VisualCube.Color color, final int time) {
    this.color = color;
    refresh();
    (new Thread(new Runnable() {
      public void run() {
        try {
          Thread.sleep(time);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        reset();
      }
    })).start();
  }
  
  public void refresh() {
    for (int x = leftX; x <= rightX; x++) {
         for (int y = bottomY; y <= topY; y++) {
          side.getPixel(x, y).setElement(this, color);
         }
        }
  }
  
  public void reset() {
    super.reset();
    this.color = this.baseColor;
    refresh();
  }
  
  class Score implements Element {
    int score = 0;
    public void increment() {
      side.getPixel(0, score).setElement(this, new VisualCube.Color(150, 150, 150));
      if (score < side.getHeight()-1) {
        score++;
      }
    }
    public int getScore() {
      return score;
    }
    public void reset() {
      score = 0;
      for (int i = 0; i < side.getHeight(); i++) {
        side.getPixel(0, i).removeElement(this);
      }
    }
  }
}
