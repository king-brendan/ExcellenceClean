package assignment5;

/**
 * A class to represent the position of a shape within our model space.
 */
public class Position {
  private double x, y;

  /**
   * A public constructor to create a Position.
   *
   * @param x is the x value.
   * @param y is the y value.
   * @throws IllegalArgumentException if the arguments given are out of bounds.
   */
  public Position(double x, double y) {
    if (x < 0 || y < 0 || x > Constants.viewWidth || y > Constants.viewHeight) {
      throw new IllegalArgumentException("Position is out of bounds");
    }
    setX(x);
    setY(y);
  }

  /**
   * A copy constructor for position.
   *
   * @param p is the position to be copued.
   */
  public Position(Position p) {
    setX(p.x);
    setY(p.y);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == this) {
      return true;
    }
    if(!(obj instanceof Position)) {
      return false;
    }

    Position p = (Position) obj;

    return (p.getX() == this.x && p.getY() == this.y);
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
    return this.x;
  }

  /**
   * Returns the current y value of the position.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Sets the position as the given one.
   *
   * @param p is the new position
   */
  public void setPosition(Position p) {
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
}
