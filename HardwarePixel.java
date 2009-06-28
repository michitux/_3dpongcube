import processing.visualcube1e3.simulator.*;
import processing.visualcube1e3.*;
import processing.visualcube1e3.device.*;


import java.awt.Color;

import java.util.concurrent.ConcurrentHashMap;

class HardwarePixel {
  int x;
  int y;
  int z;
  VisualCube cube;

  ConcurrentHashMap<Element, VisualCube.Color> elements = new ConcurrentHashMap<Element, VisualCube.Color>();

  public HardwarePixel(int x, int y, int z, VisualCube cube) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.cube = cube;
  }

  public void setElement(Element e, VisualCube.Color c) {
    elements.put(e, c);
    updatePixel();
  }

  public void removeElement(Element e) {
    elements.remove(e);
    updatePixel();
  }

  private void updatePixel() {
    int r = 0;
    int g = 0;
    int b = 0;

    for (VisualCube.Color c : elements.values()) {
      r += c.r;
      g += c.g;
      b += c.b;
    }

    if (r > 255)
       r = 255;
    if (g > 255)
       g = 255;
    if (b > 255)
       b = 255;

    cube.set(x, y, z, new VisualCube.Color(r, g, b));
  }
 
}

