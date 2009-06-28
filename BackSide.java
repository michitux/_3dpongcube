import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

class BackSide extends VerticalXSide {
  public BackSide(HardwareCube cube, Minim minim) {
    super(cube, minim);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(x, cube.getDepth() - 1, y);
  }
}

