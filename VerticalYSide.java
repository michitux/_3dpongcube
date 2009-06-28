import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

abstract class VerticalYSide extends Side {
  public VerticalYSide(HardwareCube cube, Minim minim) {
    super(cube, minim);
  }

  public Vector reflect(Vector v, Vector p) {
   return new Vector(-v.getX(), v.getY(), v.getZ());
  }

  public int getWidth() {
    return cube.getDepth();
  }
  
  public int getHeight() {
    return cube.getHeight();
  }

  public int getVectorX(Vector v) {
    return v.getY();
  }

  public int getVectorY(Vector v) {
    return v.getZ();
  }
}
