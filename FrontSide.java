class FrontSide extends VerticalXSide {
  public FrontSide(HardwareCube cube) {
    super(cube);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(x, 0, y);
  }
}

