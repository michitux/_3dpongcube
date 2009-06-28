import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

import processing.visualcube1e3.simulator.*;
import processing.visualcube1e3.*;
import processing.visualcube1e3.device.*;

import java.util.Random;
abstract class Side implements Element {
  int hashCode;
  HardwareCube cube;
  Minim minim;

  public Side(HardwareCube cube, Minim minim) {
    this.cube = cube;
    Random random = new Random();
    this.hashCode = random.nextInt();
    this.minim = minim;
  }
  
  public int hashCode() {
    return hashCode;
  }

  abstract public Vector reflect(Vector v, Vector p);
  abstract public HardwarePixel getPixel(int x, int y);
  abstract public int getWidth();
  abstract public int getHeight();
  abstract public int getVectorX(Vector v);
  abstract public int getVectorY(Vector v);

  public void won() {
  }
  public void lost() {
  }
  
  public void flash(VisualCube.Color color) {
    for(int x = 0; x < this.getWidth(); x++) {
      for (int y = 0; y < this.getHeight(); y++) {
        this.getPixel(x, y).setElement(this, color);
      }
    }
    try {
    Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.reset();
  }
 
  
  public void reset() {
    for(int x = 0; x < this.getWidth(); x++) {
      for (int y = 0; y < this.getHeight(); y++) {
        this.getPixel(x, y).removeElement(this);
      }
    }
  }
}
