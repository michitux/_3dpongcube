abstract class HorizontalSide extends Side {
  public HorizontalSide(HardwareCube cube) {
    super(cube);
  }

  public Vector reflect(Vector v) {
   return new Vector(v.getX(), v.getY(), -v.getZ());
  }

  public int getWidth() {
    return cube.getWidth();
  }

  public int getVectorX(Vector v) {
    return v.getX();
  }

  public int getVectorY(Vector v) {
    return v.getY();
  }

  public int getHeight() {
    return cube.getDepth();
  }
}
