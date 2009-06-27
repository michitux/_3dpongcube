import processing.visualcube1e3.simulator.*;
import processing.visualcube1e3.*;
import processing.visualcube1e3.device.*;


import java.awt.Color;

import java.util.HashMap;

class HardwarePixel {
  int x;
  int y;
  int z;
  VisualCube cube;

  HashMap<Element, Color> elements = new HashMap<Element, Color>();

  public HardwarePixel(int x, int y, int z, VisualCube cube) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.cube = cube;
  }

  public void setElement(Element e, Color c) {
    //elements.put(e, c);
    updatePixel();
  }

  public void removeElement(Element e) {
    //elements.remove(e);
    updatePixel();
  }

  private void updatePixel() {
    int r = 0;
    int g = 0;
    int b = 0;

    for (Color c : elements.values()) {
      r += c.getRed();
      g += c.getGreen();
      b += c.getBlue();
    }

    r /= elements.size();
    g /= elements.size();
    b /= elements.size();
    //cube.set(x, y, z, new VisualCube.Color(r, g, b));
    //cube.update();
  }
}

