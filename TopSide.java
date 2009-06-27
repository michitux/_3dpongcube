class TopSide extends HorizontalSide {
  public TopSide(HardwareCube cube) {
    super(cube);
  }

  public HardwarePixel getPixel(int x, int y) {
    return cube.getPixel(x, y, cube.getHeight()-1);
  }
}

