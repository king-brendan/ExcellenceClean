package assignment5;

/**
 * A class to represent the position of a shape within our model space.
 */
public final class Position {
  private double x;
  private double y;

  /**
   * A public constructor to create a Position.
   *
   * @param x is the x value.
   * @param y is the y value.
   * @throws IllegalArgumentException if the arguments given are out of bounds.
   */
  public Position(double x, double y) {
    if (x < 0 || y < 0 || x > Constants.VIEW_WIDTH || y > Constants.VIEW_HEIGHT) {
      throw new IllegalArgumentException("Position is out of bounds");
    }
    setX(x);
    setY(y);
  }

  /**
   * A copy constructor for position.
   *
   * @param p is the position to be copied.
   */
  public Position(Position p) {
    setX(p.x);
    setY(p.y);
  }


  /**
   * Returns a copy of the current position.
   */
  public Position getPosition() {
    return new Position(this);
  }

  /**
   * Returns the current x value of the position.
   */
  public double getX() {
    double r = this.x * 1;
    return r;
  }

  /**
   * Returns the current y value of the position.
   */
  public double getY() {
    double r = this.y * 1;
    return r;
  }

  /**
   * Sets the position as the given one. Set as default for similar reasons as the setDimension
   * method.
   *
   * @param p is the new position
   */
  void setPosition(Position p) {
    setY(p.y);
    setX(p.x);
  }

  /**
   * Sets the x parameter of the position.
   *
   * @param x is the x parameter
   */
  private void setX(double x) {
    this.x = x;
  }

  /**
   * Similar to setX but for the Y parameter.
   *
   * @param y is the Y parameter
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
    if (!(obj instanceof Position)) {
      return false;
    }

    Position p = (Position) obj;

    return (isWithin(p.getX(), this.getX()) && isWithin(p.getY(), this.getY()));
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
