package cs3500.animator.model;

/**
 * Represents the x and y dimensions of a Shape. This will be used to draw the shape with the
 * correct dimensions. While every shape is different, an X and Y value are enough represent how it
 * will be shown/drawn. There were restrictions on this class's constructor, but were taken down as
 * a negative dimension can be dealt with in the view.
 */
public final class Dimension {
  private double x;
  private double y;


  /**
   * A public constructor to create a Dimension.
   *
   * @param x is the x value.
   * @param y is the y value.
   */
  public Dimension(double x, double y) {
    setX(x);
    setY(y);
  }

  /**
   * A copy constructor for Dimension.
   *
   * @param d is the dimension to be copied.
   */
  public Dimension(Dimension d) {
    setX(d.x);
    setY(d.y);
  }


  /**
   * Sets the dimension as the given one. Set as default since it is only accessed within this
   * package. Outside users should not be able to change a Dimension.
   *
   * @param d is the new position
   */
  void setDimension(Dimension d) {
    setY(d.y);
    setX(d.x);
  }

  /**
   * Returns the x value.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Returns they value.
   */
  public double getY() {
    return this.y;
  }


  /**
   * Sets the x parameter of the dimension.
   *
   * @param x is the x parameter
   */
  private void setX(double x) {
    this.x = x;
  }

  /**
   * Similar to setX but for the y parameter.
   *
   * @param y is the y parameter
   */
  private void setY(double y) {
    this.y = y;
  }

  @Override
  public int hashCode() {
    return (int) (getX() * 100000 + getY());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Dimension)) {
      return false;
    }

    Dimension d = (Dimension) obj;

    return isWithin(d.getX(), this.getX()) && isWithin(d.getY(), this.getY());

  }

  /**
   * To check if two double are within a 0.001 threshold.
   *
   * @param d1 the first double
   * @param d2 the second double
   * @return boolean
   */
  private boolean isWithin(double d1, double d2) {
    return Math.abs(d1 - d2) < 0.001;
  }
}
