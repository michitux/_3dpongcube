class RightSide extends VerticalYSide {
  public RightSide(HardwareCube cube) {
    super(cube);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(cube.getWidth() - 1, x, y);
  }
}

