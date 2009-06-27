import java.util.Random;
abstract class Side implements Element {
  int hashCode;
  HardwareCube cube;

  public Side(HardwareCube cube) {
    this.cube = cube;
    this.hashCode = (new Random).nextInt();
  }
  
  public int hashCode() {
    return hashCode;
  }

  abstract public Vector reflect(Vector v);
  abstract public HardwarePixel getPixel(int x, int y);
  abstract public int getWidth();
  abstract public int getHeight();
  abstract public int getVectorX(Vector v);
  abstract public int getVectorY(Vector v);

  public void won() {
  }
}
