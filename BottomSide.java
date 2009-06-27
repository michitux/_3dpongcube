class BottomSide extends HorizontalSide {
  public BottomSide(HardwareCube cube) {
    super(cube);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(x, y, 0);
  }
}

