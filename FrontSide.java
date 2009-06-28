import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

class FrontSide extends VerticalXSide {
  public FrontSide(HardwareCube cube, Minim minim) {
    super(cube, minim);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(x, 0, y);
  }
}

