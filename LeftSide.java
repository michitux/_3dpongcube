import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

class LeftSide extends VerticalYSide {
  public LeftSide(HardwareCube cube, Minim minim) {
    super(cube, minim);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(0, x, y);
  }
}

