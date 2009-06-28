import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

abstract class HorizontalSide extends Side {
  public HorizontalSide(HardwareCube cube, Minim minim) {
    super(cube, minim);
  }

  public Vector reflect(Vector v, Vector p) {
   return new Vector(v.getX(), v.getY(), -v.getZ());
  }

  public int getWidth() {
    return cube.getWidth();
  }

  public int getVectorX(Vector v) {
    return v.getX();
  }

  public int getVectorY(Vector v) {
    return v.getY();
  }

  public int getHeight() {
    return cube.getDepth();
  }
}
