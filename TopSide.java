import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;

class TopSide extends HorizontalSide {
  public TopSide(HardwareCube cube, Minim minim) {
    super(cube, minim);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(x, y, cube.getHeight()-1);
  }
}

