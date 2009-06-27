class BackSide extends VerticalXSide {
  public BackSide(HardwareCube cube) {
    super(cube);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(x, cube.getDepth() - 1, y);
  }
}

