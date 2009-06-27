abstract class VerticalXSide extends Side {
  public VerticalXSide(HardwareCube cube) {
    super(cube);
  }

  public Vector reflect(Vector v) {
   return new Vector(-v.getX(), v.getY(), v.getZ());
  }

  public int getWidth() {
    return cube.getWidth();
  }

  public int getVectorX(Vector v) {
    return v.getX();
  }

  public int getVectorY(Vector v) {
    return v.getZ();
  }

  public int getHeight() {
    return cube.getHeight();
  }
}
