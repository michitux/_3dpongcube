import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

class RightSide extends VerticalYSide {
  public RightSide(HardwareCube cube, Minim minim) {
    super(cube, minim);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(cube.getWidth() - 1, x, y);
  }
}

