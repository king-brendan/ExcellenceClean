package cs3500.animator.provider.view;

import java.util.Objects;

/**
 * Represents a 2D Cartesian position.
 */
public class Position2D {
  private final int x;
  private final int y;

  /**
   * Constructs a Position.
   *
   * @param x The x coordinate.
   * @param y The y coordinate.
   */
  public Position2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x value.
   *
   * @return The x value.
   */
  public int getX() {
    return x;
  }

  /**
   * Gets the y value.
   *
   * @return The y value.
   */
  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position2D position = (Position2D) o;
    return x == position.x
        && y == position.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "\tX: " + this.x + "\tY: " + this.y;
  }
}
