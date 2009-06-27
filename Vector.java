class Vector {
  int x;
  int y;
  int z;

  public Vector(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Gets the x for this instance.
   *
   * @return The x.
   */
  public int getX() {
    return this.x;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public void setZ(int z) {
    this.z = z;
  }

  /**
   * Gets the y for this instance.
   *
   * @return The y.
   */
  public int getY() {
    return this.y;
  }

  /**
   * Gets the z for this instance.
   *
   * @return The z.
   */
  public int getZ() {
    return this.z;
  }

  public void incrementInDirection(int count) {
    if (this.x < 0) {
      this.x -= count;
    } else {
      this.x += count;
    }
    if (this.y < 0) {
      this.y -= count;
    } else {
      this.y += count;
    }
    if (this.z < 0) {
      this.z -= count;
    } else {
      this.z += count;
    }
  }

  public void add(Vector v) {
    this.x += v.x;
    this.y += v.y;
    this.z += v.z;
  }
}
