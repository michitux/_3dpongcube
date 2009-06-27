import java.awt.Color;
import processing.visualcube1e3.*;

VisualCube cube = new VisualCube(this);

void setup() {
  cube.open("172.0.0.1");
  cube.clear();
  cube.simulate(800, 800);
  new HardwareCube(cube);
}

void draw() {
  //new HardwareCube(cube);
}

void destroy() {
  cube.close();
}
