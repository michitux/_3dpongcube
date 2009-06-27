import processing.core.*; 
import processing.xml.*; 

import java.awt.Color; 
import processing.visualcube1e3.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class _3dpongcube extends PApplet {




VisualCube cube = new VisualCube(this);

public void setup() {
  cube.open("172.0.0.1");
  cube.clear();
  cube.simulate(800, 800);
}

public void draw() {
  new HardwareCube(cube);
}

public void destroy() {
  cube.close();
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#DFDFDF", "_3dpongcube" });
  }
}
