class LeftSide extends VerticalYSide {
  public LeftSide(HardwareCube cube) {
    super(cube);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(0, x, y);
  }
}

