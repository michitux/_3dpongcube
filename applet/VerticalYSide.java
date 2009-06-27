abstract class VerticalYSide extends Side {
  public VerticalYSide(HardwareCube cube) {
    super(cube);
  }

  public Vector reflect(Vector v) {
   return new Vector(v.getX(), -v.getY(), v.getZ());
  }

  public int getWidth() {
    return cube.getDepth();
  }
  
  public int getHeight() {
    return cube.getHeight();
  }

  public int getVectorX(Vector v) {
    return v.getY();
  }

  public int getVectorY(Vector v) {
    return v.getZ();
  }
}
