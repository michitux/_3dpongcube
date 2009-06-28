import java.awt.Color;
import processing.visualcube1e3.*;

import ddf.minim.*;



VisualCube cube = new VisualCube(this);

HardwareCube pongcube;

void setup() {
  cube.open("192.168.2.173");
  cube.clear();
  cube.simulate(800, 800);
  Minim minim = new Minim(this);
  AudioPlayer player = minim.loadFile("/home/michitux/pub/musik/suhov-symphaty_modul_ep/01_suhov-exx_fuck.mp3", 2048);
  player.loop();
  pongcube = new HardwareCube(cube, minim);
}

void draw() {
    cube.update();
}

void keyPressed() {
  if (key == 'd') {
    ((Racket)pongcube.getBottom()).moveLeft();
  } else if (key == 'a') {
    ((Racket)pongcube.getBottom()).moveRight();
  } else if (key == 'w') {
    ((Racket)pongcube.getBottom()).moveDown();
  } else if (key == 's') {
    ((Racket)pongcube.getBottom()).moveUp();
  } else if (key == 'l') {
    ((Racket)pongcube.getTop()).moveLeft();
  } else if (key == 'h') {
    ((Racket)pongcube.getTop()).moveRight();
  } else if (key == 'k') {
    ((Racket)pongcube.getTop()).moveDown();
  } else if (key == 'j') {
    ((Racket)pongcube.getTop()).moveUp();
  } else if (key == CODED && keyCode == DOWN) {
    ((Racket)pongcube.getFront()).moveDown();
  } else if (key == CODED && keyCode == UP) {
    ((Racket)pongcube.getFront()).moveUp();
  } else if (key == CODED && keyCode == RIGHT) {
    ((Racket)pongcube.getFront()).moveLeft();
  } else if (key == CODED && keyCode == LEFT) {
    ((Racket)pongcube.getFront()).moveRight();
  } else if (key == 't') {
    ((Racket)pongcube.getBack()).moveDown();
  } else if (key == '5') {
    ((Racket)pongcube.getBack()).moveUp();
  } else if (key == 'z') {
    ((Racket)pongcube.getBack()).moveLeft();
  } else if (key == 'r') {
    ((Racket)pongcube.getBack()).moveRight();
  }
}

void destroy() {
  cube.close();
}
