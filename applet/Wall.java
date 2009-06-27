abstract class Wall extends Side {
  /**
   * {@inheritDoc}
   * @see Side#doesReflect(int,int,int)
   */
  public boolean doesReflect(int x, int y) {
    return true;
  }
}
